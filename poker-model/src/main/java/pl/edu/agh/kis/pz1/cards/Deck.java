package pl.edu.agh.kis.pz1.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages a deck, contains shuffling methods, and giving cards.
 */
public class Deck {
    private List<Card> cards;

    /**
     * creates an empty deck
     * @param cards id an empty deck
     */
    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * fabric method for creating a deck
     * @return a whole deck of cards
     */
    public static Deck fabric(){
        List <Card> cards = new ArrayList<>();
        for (Card.Suit suit: Card.Suit.values()) {
            for (Card.Rank rank: Card.Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        return new Deck(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Method gives cards to player's hands.
     * @param n number of players
     * @param cpp cards per person
     * @return zwraca listę z osobami i posortowanymi, odpowiadającymi im kartami
     * @return list of players and sorted cards
     * cardIndex dba o to, aby dodawane karty sie nie powtarzaly
     * cardIndex is for ensuring that cards are distinct
     */
    public List<List<Card>> shuffleToPerson(int n, int cpp){
        List<List<Card>> shuffled = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Card> subList = new ArrayList<>();
            shuffled.add(subList);
        }
        int cardIndex = 0;
        for (int i = 0; i < cpp; i++) {
            for (int j = 0; j < n; j++){
                shuffled.get(j).add(cards.get(cardIndex));
                cardIndex++;
            }
        }
        return shuffled;
    }

    /**
     * Wygenerowany przez Intellija
     */
    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                '}';
    }
}
