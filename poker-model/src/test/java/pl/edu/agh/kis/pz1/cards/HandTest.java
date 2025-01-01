package pl.edu.agh.kis.pz1.cards;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.kis.pz1.exceptions.IllegalMoveException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {
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
    void handCanShowCards(){

      Hand hand = new Hand(setUpListOfFiveCards());
      List<Card> cards = setUpListOfFiveCards();

      for (int i = 0; i < 5; i++) {
          assertNotNull(hand.showCard(i), "Card of number" + i + " should not be null");
          assertEquals(hand.showCard(i), cards.get(i), "Card" + cards.get(i).toString() + "does not equal" + hand.showCard(i));
      }
  }
    @Test
    void handCanDrawCards(){
        Hand hand = new Hand(setUpListOfFiveCards());
        hand.drawCard(new Card(Card.Suit.HEARTS, Card.Rank.THREE));
        assertEquals(hand.showCard(hand.getCards().size()-1), new Card(Card.Suit.HEARTS, Card.Rank.THREE));
    }
    @Test
    void handThrowsExceptionWhenCardIsNull(){
        Hand hand = new Hand(setUpListOfFiveCards());
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> hand.drawCard(null)
        );

        assertEquals("Cannot draw a null card.", exception.getMessage());
    }
    @Test
    void handCanDiscardCard(){
        Hand hand = new Hand(setUpListOfFiveCards());
        assertEquals(new Card(Card.Suit.HEARTS, Card.Rank.ACE), hand.discardCard(0), "The discarded card should be hearts of ace");
    }
}