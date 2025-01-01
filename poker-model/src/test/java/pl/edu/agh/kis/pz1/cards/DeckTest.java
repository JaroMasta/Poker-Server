package pl.edu.agh.kis.pz1.cards;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa DeckTest testuje klase Deck w module poker-model
 */
public class DeckTest {
    @Test
    public void testDeckShuffle() {
        Deck deck = Deck.fabric();
        List <Card> org = new ArrayList<>(deck.getCards());

        deck.shuffle();
        List <Card> shuffled = deck.getCards();

        assertNotEquals(org, shuffled);
    }

    @Test
    public void testDeckShuffleToPerson() {
        Deck deck = Deck.fabric();
        deck.shuffle();

        List<List<Card>> cards = deck.shuffleToPerson(4, 4);
        for (List<Card> person : cards) {
            assertEquals(4, person.size());
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeckShuffleTooManyCards() {
        Deck deck = Deck.fabric();
        deck.shuffle();

        deck.shuffleToPerson(10, 10);
    }
}
