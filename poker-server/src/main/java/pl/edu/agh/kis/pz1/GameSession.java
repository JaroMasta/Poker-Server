package pl.edu.agh.kis.pz1;

import lombok.AccessLevel;
import lombok.Getter;
import pl.edu.agh.kis.pz1.game.Game;
import pl.edu.agh.kis.pz1.rules.Rules;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.util.List;

/**
 * Class responsible for one game session e.g. managing connected clients and running their game
 */
class GameSession implements Runnable {
    public static int gameCount = 1;
    @Getter(AccessLevel.PROTECTED) private List<ClientHandler> clients;
    @Getter(AccessLevel.PROTECTED) private final int gameId;
    public final Rules rules;
    @Getter(AccessLevel.PROTECTED) public final int requiredNumberOfPlayers;
    private Game game;
    /**
     * Constructor is giving a unique id that is the number of game sessions that were already run
     * @param clients
     */
    protected GameSession(List<ClientHandler> clients, Rules rules, int requiredNumberOfPlayers) {
        this.clients = clients;
        this.gameId = gameCount++;
        this.rules = rules;
        this.requiredNumberOfPlayers = requiredNumberOfPlayers;

    }

    /**
     * Run method is comunnicating with clients
     */
    @Override
    public void run() {
        System.out.println("Starting game with gameId  " + gameId + " with number of players:  " + clients.size());
        try{
            for (ClientHandler client : clients) {
                try {client.sendData("Welcome to game" + gameId);}
                catch (ClosedChannelException e){
                    client = null;
                }
            }

            //TODO
            //tu sie dzieje gra
            simulateGame();
            //potem zmodyfikować client handler i pokerserver
            //idea jest taka ze gamesession ma byc uruchamiane przez watek (lista wątkow jest w
            // pokerserver, do tego watku jest przekazywane tyle graczy ile najpierw bedzie
            // wpisane w konsole servera
            // jak gamesession zakonczy rozgrywke to poinforumje o tym serwer
            //generalnie chodzi o to zeby moznabylo miec kilka sesji w jednym serverze wiec przydalaby sie komenda
            // ktora mowi ze utworz sesje dla graczy czy cos w tym stylu albo jak graczy bedzie wystarczajaca duzo to
            //mozna odpalac nowa gre

        }
        catch (IOException e){
            System.out.println("Input/Output Error in gameSession of id: " + gameId + " " + e.getMessage());
        }
        catch (Exception e) {
            System.err.println("Error in gameSession of id: " + gameId + " " + e.getMessage());
        }

    }
    private void simulateGame() {
        try {
            Thread.sleep(15000); // Symulacja czasu trwania gry
        } catch (InterruptedException e) {
            System.err.println("Symulacja przerwana: " + e.getMessage());
        }
    }

    public void addClient(ClientHandler clientHandler) {
        if (clients.size() <= requiredNumberOfPlayers)
        {
            clients.add(clientHandler);
        }
        else{
            throw new IllegalArgumentException("Take more than required number of players");
        }

    }
    public void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }
    public boolean canGameStart() {
        return clients.size() == requiredNumberOfPlayers;
    }
}
