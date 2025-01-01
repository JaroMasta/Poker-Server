package pl.edu.agh.kis.pz1.game;

import org.junit.jupiter.api.Test;
import pl.edu.agh.kis.pz1.cards.Card;
import pl.edu.agh.kis.pz1.cards.Hand;
import pl.edu.agh.kis.pz1.exceptions.NoChipsException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    List<Card> setUpListOfFiveCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
        cards.add(new Card(Card.Suit.SPADES, Card.Rank.KING));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
        cards.add(new Card(Card.Suit.CLUBS, Card.Rank.TEN));
        cards.add(new Card(Card.Suit.HEARTS, Card.Rank.TWO));
        return cards;
    }
    @Test
    public void testPlayerInitialization() {
        Hand hand = new Hand(setUpListOfFiveCards());
        Player player = new Player("Alice", 100, hand);

        assertEquals("Alice", player.getName());
        assertEquals(100, player.getChips());
        assertEquals(hand, player.getHand());
        assertTrue(player.isActive());
    }

    @Test
    public void testAdjustChipsPositive() throws NoChipsException {
        Player player = new Player("Bob", 100, new Hand(setUpListOfFiveCards()));
        player.adjustChips(50);

        assertEquals(150, player.getChips());
    }

    @Test
    public void testAdjustChipsNegative() throws NoChipsException{
        Player player = new Player("Bob", 100, new Hand(setUpListOfFiveCards()));
        player.adjustChips(-50);
        assertEquals(50, player.getChips());
    }

    @Test
    public void testAdjustChipsThrowsException() {
        Player player = new Player("Charlie", 0, new Hand(setUpListOfFiveCards()));
        //pamietaj ze jest tu lambda albo klasa ktora ma metode execute()
        NoChipsException exception = assertThrows(NoChipsException.class, () -> {
            player.adjustChips(-10);
        });

        assertTrue(exception.getMessage().contains("Charlie"));
    }

    @Test
    public void testDeactivatePlayer() {
        Player player = new Player("Dave", 50, new Hand(setUpListOfFiveCards()));
        player.deactivate();

        assertFalse(player.isActive());
    }
}
