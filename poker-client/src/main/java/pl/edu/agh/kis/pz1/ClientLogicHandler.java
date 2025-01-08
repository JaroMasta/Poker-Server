package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.util.CommandType;

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

        while (true) {
            Optional<String> optionalMessage = client.receiveMessage();
            if (optionalMessage.isEmpty()){
                continue;
            }
            String message = optionalMessage.get();
            CommandType commandType = ClientInputHandler.mapMessageToCommand(message);
            switch (commandType) {
                case CHOOSE_JOIN_OR_START_NEW_GAME:
                    //Woła funkcje po stronie servera która wysyła rozne komunikaty konieczna oddzielna funkcja

                    break;
                case CHOOSE_GAME_TYPE:
                    //Woła funkcje po stronie servera która wysyła rozne komunikaty konieczna oddzielna funkcja
                    //NIE WIEM CZY BEDZIE POTRZEBNA BO CHOOSEJOINORSTARTGAME CHYBA SIE TYM WSZYSTKIM ZAJMUJE
                    break;

                case PLACE_BET:
                    // jest to  komunikat ze gracz moze dac bet ale nie musi
                    //TODO client informuje konsole ze ma byc wpisany numer kwoty
                    // ALE MUSZE WIEDZIEC ILE MA KASY I JAKIE MA KARTY I ILE JEST W POT JAK PRZESLAC TAKA INFORMACJE MOZE MUSI WYKONAC KOLEJNY KOMUNIKAT Z INTEM
                    // Odbiera info o zakresie numerow i gracz moze wpisac bet albo zrobic FOLD i dawany jest komunikat do serwera o poprawnym ilosci bet
                    // lub o FOLD lub jak jest niepoprawny to petla jeszcze raz idzie
                    break;
                case RAISE_OR_CALL:
                    //TODO client informuje konsole ze ma byc wpisany numer kwoty
                    // ALE MUSZE WIEDZIEC ILE MA KASY JAK PRZESLAC TAKA INFORMACJE MOZE MUSI WYKONAC KOLEJNY KOMUNIKAT Z INTEM
                    // Odbiera info o zakresie numerow i gracz ma wpisac bet i dawany jest komunikat do serwera o poprawnym ilosci raise
                    // lub jak jest niepoprawny to petla jeszcze raz
                    break;
                case OTHER_PLAYER_ACTION:
                    //TODO wyslij na konsole info ktore dostaniesz
                    break;
                default:
                    System.out.println("Unknown command. Error occured.");
                    throw new RuntimeException("Unknown command. Error occured.");
            }
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
