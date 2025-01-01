package pl.edu.agh.kis.pz1.game;

import lombok.Data;
import pl.edu.agh.kis.pz1.cards.Card;
import pl.edu.agh.kis.pz1.cards.Deck;
import pl.edu.agh.kis.pz1.rules.Rules;

import java.util.List;

/**
 * Game abstract class for implementing different kinds of games
 */
@Data

public abstract class Game {
    protected final Rules rules;
    protected List<Player> players;
    protected Deck deck;
    protected int pot = 0;
    protected int roundNumber = 0;
    protected HandEvaluator handEvaluator;
    public Game(List<Player> players, Rules rules) {
        this.players = players;
        this.deck = Deck.fabric();
        this.roundNumber = 0;
        this.rules = rules;
    }


    public abstract void startGame();
    public void dealCards(){
        List<List<Card>> hands = deck.shuffleToPerson(players.size(), 5);
        for (int i = 0; i < players.size(); i++) {
            // players.get(i).setHand(hands.get(i));
        }

    }
    public void addToPot(int amount){
        pot += amount;
    }
    protected abstract boolean isGameOver();
    public abstract void playRound();
    public abstract void endGame();
}
