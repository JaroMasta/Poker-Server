package pl.edu.agh.kis.pz1.game;

import lombok.NoArgsConstructor;
import pl.edu.agh.kis.pz1.cards.Card;
import pl.edu.agh.kis.pz1.cards.Deck;
import pl.edu.agh.kis.pz1.cards.Hand;
import pl.edu.agh.kis.pz1.exceptions.NoChipsException;
import pl.edu.agh.kis.pz1.rules.FiveCardDrawRules;
import pl.edu.agh.kis.pz1.rules.Rules;

import java.util.List;

/**
 * Manages logic of game, giving cards, hand comparison.
 */
public class FiveCardDrawGame extends Game{
    public static final int BIG_BLANK_VALUE = 20;
    public static final int SMALL_BLANK_VALUE = 10;
    protected Player currentDealer;
    protected Player bigBlank;
    protected Player smallBlank;
    public FiveCardDrawGame(List<Player> players, int chips) {
        super(players, new FiveCardDrawRules());
    }
    public void startGame() {
        System.out.println("Starting the game...");
        deck.shuffle();
        var cardsToGive = deck.shuffleToPerson(players.size(), 5);
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setHand(new Hand(cardsToGive.get(i)));
        }
        System.out.println("Cards have been dealt. Let the game begin!");
        currentDealer = players.get(0);
        System.out.println("Current dealer is " + currentDealer);
        bigBlank = players.get(1);
        System.out.println("Big blank is " + bigBlank);
        smallBlank = players.get(2);
        System.out.println("Small blank is " + smallBlank);
        //zbierz od graczy kwoty
        try{
           pot += bigBlank.adjustChips(-20);
           pot += smallBlank.adjustChips(-20);
        }
        catch(NoChipsException e){
            System.err.println("Invalid chips operation: " + e.getMessage());
        } catch (Exception e){
            System.err.println("Unexpected exception " + e.getMessage());
            e.printStackTrace();
        }
        while (!isGameOver()){
            //graj kolejne rundy czyli playround
        }

    }

    @Override
    protected boolean isGameOver() {
        //TODO
        return false;
    }


    public void evaluteStartGame() {};
    public void playRound() {
        //czekaj na komunikat od dealera co robi

        //czekaj na komunikat co robia pozostali
    };
    public void playFirstRound() {
        //czekaj na komunikat od dealera co robi

        //czekaj na komunikat co robi small blank

        //czekaj na komunikat co robia pozostali

        //czekaj na komunikat co robi bigblank

        //czekaj na komunikat co robia pozostali jesli bigblank podbil
    };
    public void endGame() {};

}
