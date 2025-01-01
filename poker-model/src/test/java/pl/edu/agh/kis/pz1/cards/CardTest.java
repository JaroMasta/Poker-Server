package pl.edu.agh.kis.pz1.cards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void testCardConstructorAndGetters() {

        Card.Suit suit = Card.Suit.HEARTS;
        Card.Rank rank = Card.Rank.ACE;


        Card card = new Card(suit, rank);


        assertEquals(suit, card.getSuit());
        assertEquals(rank, card.getRank());
    }

    @Test
    void testToString() {

        Card card = new Card(Card.Suit.SPADES, Card.Rank.KING);


        String result = card.toString();


        assertEquals("KING of SPADES", result);
    }

    @Test
    void testEqualsAndHashCode() {

        Card card1 = new Card(Card.Suit.CLUBS, Card.Rank.TEN);
        Card card2 = new Card(Card.Suit.CLUBS, Card.Rank.TEN);
        Card card3 = new Card(Card.Suit.DIAMONDS, Card.Rank.TEN);

        assertEquals(card1, card2);
        assertNotEquals(card1, card3);

        assertEquals(card1.hashCode(), card2.hashCode());
        assertNotEquals(card1.hashCode(), card3.hashCode());
    }
}
