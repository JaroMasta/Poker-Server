package pl.edu.agh.kis.pz1;

import java.util.Optional;
import java.util.Scanner;

/**
 * Manages corelation between what user receives and what is his current state as far as connection with server
 * goes meaning
 * choosing game to join and starting new game
 * logic inside of game
 * and cooperates with user input
 *
 */
//TODO pas wyrownaj i daj wiecej to sie przechowa w enumie jakims i skomunikuje z klasa player ktora bedzie w PokerClient
public class ClientLogicHandler {
    protected PokerClient client;
    Scanner scanner;
    public ClientLogicHandler(PokerClient client, Scanner scanner) {
        this.client = client;
        this.scanner = scanner;
    }
    public void start(){
        Optional<String> firstMessage = client.receiveMessage();
        if (firstMessage.isEmpty()){
            return;
        }
        String message = firstMessage.get();
        ClientInputHandler.CommandType commandType = ClientInputHandler.mapMessageToCommand(message);
        switch (commandType) {
            case CHOOSE_JOIN_OR_START_NEW_GAME:


                break;
            case CHOOSE_GAME_TYPE:

                break;
            default:
                System.out.println("Unknown command. Error occured.");
                throw new RuntimeException("Unknown command. Error occured.");
        }

    }
    public void firstClientLogic() {

    }

    /**
     * Manages logic of a client that is NOT the first one to join the server
     */
    public void clientLogic() {

    }

    /**
     * Manages the signals from ClientInputHandler during the game
     */
    public void gameLogicManager() {

    }
    public void gameCreatorLogic() {}

    public void gameTypeChoice() {

    }
    public void joinGameChoice() {

    }


}
