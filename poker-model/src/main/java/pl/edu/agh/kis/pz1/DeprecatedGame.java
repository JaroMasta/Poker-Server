package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.cards.Card;
import pl.edu.agh.kis.pz1.cards.Deck;
import pl.edu.agh.kis.pz1.game.Player;
import pl.edu.agh.kis.pz1.game.FiveCardDrawHandEvalutor;

import java.util.List;

/**
 * Klasa Game zarzadza rozrgrywka, czyli rozdawaniem kart, monitorowaniem stanu gry oraz decyduje o jej zakonczeniu
 */
public class DeprecatedGame {
    private List<Player> players;
    private Deck deck;
    private FiveCardDrawHandEvalutor evalutor;
    int pot;

    public DeprecatedGame(List<Player> players) {
        this.players = players;
        this.deck = Deck.fabric();
        this.evalutor = new FiveCardDrawHandEvalutor();
        this.pot = 0;
    }

    public void dealCards(){
        List<List<Card>> hands = deck.shuffleToPerson(players.size(), 5);
        for (int i = 0; i < players.size(); i++) {
           // players.get(i).setHand(hands.get(i));
        }
    }

    public void start(){
        deck.shuffle();
        dealCards();
    }

    public void addToPot(int amount){
        pot += amount;
    }

    public void resetPot() {
        pot = 0;
    }

//    public Player getWinner(){
//        Player winner = null;
//        int highestHandValue = 0;
//        for (Player player : players) {
//            PokerHandEvalutor.PokerHand handValue = evalutor.evaluateHand(player.getHand());
//            if (handValue > highestHandValue) {
//                highestHandValue = handValue;
//                winner = player;
//            }
//        }
//        return winner;
//    }
}
