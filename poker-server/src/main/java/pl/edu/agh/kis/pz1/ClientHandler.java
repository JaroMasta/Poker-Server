package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.exceptions.WrongNumberException;
import pl.edu.agh.kis.pz1.game.GameVariants;
import pl.edu.agh.kis.pz1.rules.FiveCardDrawRules;
import pl.edu.agh.kis.pz1.rules.Rules;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Optional;

/**
 * Handles communication with a single client.
 */
class ClientHandler {
    private final SocketChannel clientChannel;
    private final ByteBuffer buffer;

    public ClientHandler(SocketChannel clientChannel) {
        this.clientChannel = clientChannel;
        this.buffer = ByteBuffer.allocate(256); // Buffer size for client data
    }

    /**
     * Manages a client that has just joined the server.
     * Calls startNewGame() if the player chooses to do so or if they are the first one on the server.
     *
     * @param numberOfActiveGames The number of currently active game sessions.
     * @return An Optional containing a GameSession if a new game is started, or an empty Optional if the client
     *         wants to join an existing game.
     * @throws IOException When the client disconnects or another input/output error occurs.
     */
    public Optional<GameSession> manageStartOfClient(int numberOfActiveGames) throws IOException {
        if (numberOfActiveGames > 0) {
            sendData("Please choose if you want to start a new game or join an existing one.\n" +
                    "Type 1 if you want to see the list of current games or 2 if you want to start a new game.");
            Optional<String> optionalMessage;
            while (true) {
                optionalMessage = readData();
                if (optionalMessage.isEmpty()) {
                    throw new IOException("Client has disconnected");
                }
                String messageFromClient = optionalMessage.get();
                if (messageFromClient.strip().equals("1")) {
                    // Player needs to see the list of active games in PokerServer.
                    // Returning an empty Optional indicates the client wants to see the list of active games.
                    return Optional.empty();
                } else if (messageFromClient.strip().equals("2")) {
                    // The client chose to start a new game.
                    return Optional.of(startNewGame());
                } else {
                    sendData("Please select a valid option.");
                }
            }
        }

        sendData("You are the first one to create a game session. Please choose the type of poker game you want to play.");
        // The client doesn't have a choice and must start a new game.
        return Optional.of(startNewGame());
    }

    /**
     * Displays a list of game variants, then asks the client to select a game type and validates the number of players.
     * Continues prompting until the client provides a valid response.
     * Creates an instance of Rules and a GameSession with one (this) client included.
     *
     * @return A new GameSession configured based on the client's input.
     * @throws IOException If an input/output error occurs.
     */
    public GameSession startNewGame() throws IOException {
        // Send game variants to the client.
        sendData(new GameVariants().toString());
        Optional<String> messageOptional = readData();
        if (messageOptional.isEmpty()) {
            throw new IOException("Client has disconnected");
        }
        String messageFromClient = messageOptional.get();
        while (true) {
            try {
                int numberOfGameType = Integer.parseInt(messageFromClient);

                switch (numberOfGameType) {
                    case 1:
                        Rules rules = new FiveCardDrawRules();
                        int numberOfPlayers = askClientAndValidateNumberOfPlayers(rules);
                        // If the client chooses FiveCardDraw, create a game session with the specified rules.
                        // The list of active sessions must be updated on the server.
                        return new GameSession(List.of(this), rules, numberOfPlayers);

                    default:
                        sendData("This version is nonexistent");
                        throw new WrongNumberException("Client sent an invalid number in manageStartOfClient", numberOfGameType);
                }

            } catch (NumberFormatException e) {
                sendData("Please enter a number.");
            } catch (WrongNumberException e) {
                sendData("Please enter a number between 1 and 1.");
            }
        }
    }

    /**
     * Asks the client for the desired number of players for their new game session
     * and validates the input.
     *
     * @param rules The rules for the selected game variant.
     * @return The exact number of players chosen by the client for the game session.
     * @throws WrongNumberException If the number provided is out of the valid range.
     * @throws IOException If a communication error occurs with the client.
     */
    int askClientAndValidateNumberOfPlayers(Rules rules) throws WrongNumberException, IOException {
        int maxPlayers = rules.getMaxPlayers();
        int minPlayers = 2;

        while (true) {
            try {
                // Send a question to the client.
                sendData("How many players do you want in your game? (Min: " + minPlayers + ", Max: " + maxPlayers + ")");
                Optional<String> response = readData();

                if (response.isEmpty()) {
                    sendData("Number of players cannot be empty. Please try again.");
                    continue; // Retry asking the question.
                }

                // Try parsing the number.
                int chosenNumberOfPlayers = Integer.parseInt(response.get());

                if (chosenNumberOfPlayers < minPlayers || chosenNumberOfPlayers > maxPlayers) {
                    sendData("Number of players must be between " + minPlayers + " and " + maxPlayers + ". Please try again.");
                    continue; // Retry asking the question.
                }

                // If the number is valid.
                sendData("You have selected " + chosenNumberOfPlayers + " players for your game.");
                return chosenNumberOfPlayers;

            } catch (NumberFormatException e) {
                sendData("Invalid input. Please enter a valid number.");
            } catch (IOException e) {
                clientChannel.close();
                System.out.println("Client disconnected: " + clientChannel.getRemoteAddress());
            }
        }
    }

    /**
     * Prompts the client to provide a Game ID and validates it.
     *
     * @return An Optional containing a valid game ID or an empty Optional if validation fails.
     * @throws IOException If a communication error occurs with the client.
     */
    public Optional<Integer> chooseGameSessionId() throws IOException {
        sendData("Please enter the Game ID (as a number) of the session you want to join:");

        Optional<String> optionalGameId = readData();
        while (optionalGameId.isEmpty()) {
            optionalGameId = readData();
            if (optionalGameId.isEmpty()) {
                sendData("No Game ID provided. Please try again.");
                continue;
            }

            String gameIdString = optionalGameId.get().strip();

            try {
            int gameId = Integer.parseInt(gameIdString);
            return Optional.of(gameId);
            } catch (NumberFormatException e) {
                sendData("Invalid Game ID. Please enter a valid number.");

            }
        }
        return Optional.empty();
    }

    public void readDataWithResponse(String response) throws IOException {
        int bytesRead = clientChannel.read(buffer);
        if (bytesRead == -1) {
            clientChannel.close();
            System.out.println("Client disconnected: " + clientChannel.getRemoteAddress());
        } else {
            buffer.flip();
            String message = new String(buffer.array(), 0, bytesRead);
            System.out.println("Received: " + message);

            buffer.clear();
            buffer.put(response.getBytes());
            buffer.flip();
            clientChannel.write(buffer);
        }
    }

    public Optional<String> readData() throws IOException {
        int bytesRead = clientChannel.read(buffer);
        String message;
        if (bytesRead == -1) {
            clientChannel.close();
            System.out.println("Client disconnected: " + clientChannel.getRemoteAddress());
            message = null;
        } else {
            buffer.flip();
            message = new String(buffer.array(), 0, bytesRead);
            System.out.println("Received: " + message);
        }
        return Optional.ofNullable(message);
    }

    public void sendData(String message) throws IOException {
        buffer.clear();
        buffer.put(message.getBytes());
        buffer.flip();
        clientChannel.write(buffer);
    }
}
