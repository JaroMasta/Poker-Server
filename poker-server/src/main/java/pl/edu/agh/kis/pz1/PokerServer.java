package pl.edu.agh.kis.pz1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages client's connection and game
 */
public class PokerServer {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private final Map<SelectionKey, ClientHandler> clients = new HashMap<>();
    // lista aktywnych sesji gier
    private List<GameSession> activeGames = new ArrayList<>();
    //każda sesja to jeden wątek

    @Deprecated private final List<Thread> gameThreads = new ArrayList<>();
    //kolejka kluczy czekajacych graczy queue of waiting players (will be used (in future versions) only if
    //server is full
    Queue <SelectionKey> waitingKeys = new LinkedList<>();
    ByteBuffer buffer = ByteBuffer.allocate(256);


    /**
     * Starts the server and handles client connections.
     */
    public void start() {
        try {
            // Initialize the selector
            selector = Selector.open();

            // Initialize the server socket channel
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8080));
            serverSocketChannel.configureBlocking(false);

            // Register the server channel with the selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server is listening on port 8080...");

            while (true) {
                // Wait for events (e.g., new connections, data to read)
                selector.select();

                // Get the keys for channels that are ready for I/O operations
                //selected keys are a set of selected keys for processing
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();

                    try {
                        if (key.isAcceptable()) {
                            // Accept a new client connection
                            acceptClientConnection(key);
                        } else if (key.isReadable()) {
                            // Read data from a client
                            readClientData(key);
                        }
                    } catch (IOException e) {
                        System.err.println("Error handling client: " + e.getMessage());
                        key.channel().close();
                        key.cancel();
                    }

                }

            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    private void acceptClientConnection(SelectionKey key) throws IOException {
        SocketChannel clientChannel = serverSocketChannel.accept();
        clientChannel.configureBlocking(false);

        // Register the client channel with the selector
        SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

        // Add the client to the clients map
        ClientHandler clientHandler = new ClientHandler(clientChannel);
        clients.put(clientKey, clientHandler);
        System.out.println("New client connected: " + clientChannel.getRemoteAddress());

        // Add client to waiting list if server is full
        //TODO manage logic of waiting clients and add capacity value to PokerServer
        //waitingKeys.add(clientKey);
        try {
            Optional<GameSession> gameSessionOptional = clientHandler.manageStartOfClient(activeGames.size());
            if (gameSessionOptional.isPresent()) {
                //add game session to active games list
                activeGames.add(gameSessionOptional.get());
            } else {
                // Jeśli klient chce dołączyć do istniejącej gry
                clientHandler.sendData(formatGameSessions(activeGames));
                Optional<Integer> chosenGameId = clientHandler.chooseGameSessionId();

                if (chosenGameId.isPresent()) {
                    assignClientToGameSession(clientHandler, chosenGameId.get());
                } else {
                    clientHandler.sendData("Failed to join any game session. Please try again.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage() + " Disconnecting client...");
            clientChannel.close();
            key.channel().close();
            key.cancel();
            clients.remove(clientKey);
        }


    }

    private void readClientData(SelectionKey key) throws IOException {
        // Get the ClientHandler associated with this key
        ClientHandler client = clients.get(key);
        if (client != null) {
            client.readDataWithResponse("Hello Client");
        } else {
            System.err.println("ClientHandler not found for key: " + key);
            key.channel().close();
            key.cancel();
        }
    }
    /**
     * Sends a message to the Client.
     *
     * @param message The message to send.
     */
    public void sendMessageToClient(String message, SelectionKey key)  throws IOException {
        // Get the ClientHandler associated with this key
        ClientHandler client = clients.get(key);
        if (client != null) {
            client.sendData(message);
        } else {
            System.err.println("ClientHandler not found for key: " + key);
            key.channel().close();
            key.cancel();
        }
    }

    /**
     * Creates a new thread with game session and starts this thread
     * @param gameSession
     */
    private void checkAndStartGame(GameSession gameSession) {

            Thread gameThread = new Thread(gameSession);
            gameThreads.add(gameThread);
            // Uruchom grę w nowym wątku
            gameThread.start();
    }

    /**
     * Assign client to game session that he chose
     * Przypisuje klienta do sesji gry na podstawie gameId.
     *If game has enough players it immedietly starts
     * Uses checkAndStartGame
     * @param clientHandler Handler klienta próbującego dołączyć do gry.
     *                      handles client reponse when he wants to join gamesession
     * @param gameId Identyfikator gry wybrany przez klienta.
     *               gameId that player chose
     * @throws IOException Jeśli wystąpi błąd komunikacji z klientem.
     *
     */
    private void assignClientToGameSession(ClientHandler clientHandler, int gameId) throws IOException {
        Optional<GameSession> selectedSession = activeGames.stream()
                .filter(session -> session.getGameId() == gameId)
                .findFirst();

        if (selectedSession.isPresent()) {
            GameSession gameSession = selectedSession.get();

            // Sprawdź, czy jest wolne miejsce w sesji
            if (gameSession.getClients().size() < gameSession.getRequiredNumberOfPlayers()) {
                gameSession.addClient(clientHandler);
                clientHandler.sendData("You have successfully joined the game session: " + gameId);
                System.out.println("Client joined session: " + gameId);
                if(gameSession.canGameStart())
                {
                    //jesli gra moze sie zaczac to dodaj ja do watku i zacznij ten watek
                    checkAndStartGame(gameSession);


                }
            } else {
                clientHandler.sendData("The selected game session is full. Please choose another one.");
            }
        } else {
            clientHandler.sendData("No game session found with ID: " + gameId);
        }
    }


    /**
     * Formatuje listę sesji gier w czytelny string.
     *
     * @param gameSessions Lista obiektów GameSession
     * @return Sformatowany string z informacjami o grach
     */
    private String formatGameSessions(List<GameSession> gameSessions) {
        if (gameSessions == null || gameSessions.isEmpty()) {
            return "No active game sessions available.";
        }

        return gameSessions.stream()
                .map(session -> String.format(
                        "%s %d %d",
                        session.getGameId(),
                        session.getClients().size(),
                        session.getRequiredNumberOfPlayers()
                ))
                .collect(Collectors.joining("\n"));
    }

}


