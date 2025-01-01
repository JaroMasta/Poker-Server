package pl.edu.agh.kis.pz1.game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.edu.agh.kis.pz1.cards.Card;
import pl.edu.agh.kis.pz1.cards.Hand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FiveCardDrawHandEvalutorTest {


    @Test
    void pair() {
        HandEvaluator handEvaluator = new FiveCardDrawHandEvalutor();
        Hand hand = new Hand(new ArrayList<>(Arrays.asList((new Card(Card.Suit.CLUBS, Card.Rank.KING)),new Card(Card.Suit.HEARTS, Card.Rank.KING),
                new Card(Card.Suit.CLUBS, Card.Rank.SEVEN), new Card(Card.Suit.SPADES, Card.Rank.TWO),
                new Card(Card.Suit.DIAMONDS, Card.Rank.SIX))));
        assertEquals( FiveCardDrawHandEvalutor.PokerHand.ONE_PAIR, handEvaluator.evaluateHand(hand));

    }

    @Test
    void twoPair() {
        HandEvaluator handEvaluator = new FiveCardDrawHandEvalutor();
        Hand hand = new Hand(new ArrayList<>(Arrays.asList((new Card(Card.Suit.CLUBS, Card.Rank.KING)),new Card(Card.Suit.HEARTS, Card.Rank.KING),
                new Card(Card.Suit.CLUBS, Card.Rank.SEVEN), new Card(Card.Suit.SPADES, Card.Rank.SEVEN),
                new Card(Card.Suit.DIAMONDS, Card.Rank.SIX))));
        assertEquals( FiveCardDrawHandEvalutor.PokerHand.TWO_PAIRS, handEvaluator.evaluateHand(hand));
    }

    @Test
    void threeOfAKind() {
        HandEvaluator handEvaluator = new FiveCardDrawHandEvalutor();
        Hand hand = new Hand(new ArrayList<>(Arrays.asList((new Card(Card.Suit.CLUBS, Card.Rank.KING)),new Card(Card.Suit.HEARTS, Card.Rank.KING),
                new Card(Card.Suit.DIAMONDS, Card.Rank.KING), new Card(Card.Suit.SPADES, Card.Rank.SEVEN),
                new Card(Card.Suit.DIAMONDS, Card.Rank.SIX))));
        assertEquals( FiveCardDrawHandEvalutor.PokerHand.THREE_OF_A_KIND, handEvaluator.evaluateHand(hand));
    }

    @Test
    void straight() {
        HandEvaluator handEvaluator = new FiveCardDrawHandEvalutor();
        Hand hand = new Hand(new ArrayList<>(Arrays.asList((new Card(Card.Suit.CLUBS, Card.Rank.THREE)),new Card(Card.Suit.HEARTS, Card.Rank.FIVE),
                new Card(Card.Suit.DIAMONDS, Card.Rank.FOUR), new Card(Card.Suit.SPADES, Card.Rank.SEVEN),
                new Card(Card.Suit.DIAMONDS, Card.Rank.SIX))));
        assertEquals( FiveCardDrawHandEvalutor.PokerHand.STRAIGHT, handEvaluator.evaluateHand(hand));
    }
    @Test
    void hasStraight() {
        HandEvaluator handEvaluator = new FiveCardDrawHandEvalutor();
        Hand hand = new Hand(new ArrayList<>(Arrays.asList((new Card(Card.Suit.CLUBS, Card.Rank.THREE)),new Card(Card.Suit.HEARTS, Card.Rank.FIVE),
                new Card(Card.Suit.DIAMONDS, Card.Rank.FOUR), new Card(Card.Suit.SPADES, Card.Rank.SEVEN),
                new Card(Card.Suit.DIAMONDS, Card.Rank.SIX))));
        hand.sortCardsInHand();

        assertTrue(handEvaluator.hasStraight(hand.getCards()));
    }


    @Test
    void flush() {
        HandEvaluator handEvaluator = new FiveCardDrawHandEvalutor();
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.TWO),
                new Card(Card.Suit.HEARTS, Card.Rank.FIVE),
                new Card(Card.Suit.HEARTS, Card.Rank.SEVEN),
                new Card(Card.Suit.HEARTS, Card.Rank.KING),
                new Card(Card.Suit.HEARTS, Card.Rank.SIX)
        )));
        assertEquals(FiveCardDrawHandEvalutor.PokerHand.FLUSH, handEvaluator.evaluateHand(hand));
    }

    @Test
    void fullHouse() {
        HandEvaluator handEvaluator = new FiveCardDrawHandEvalutor();
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(
                new Card(Card.Suit.CLUBS, Card.Rank.KING),
                new Card(Card.Suit.HEARTS, Card.Rank.KING),
                new Card(Card.Suit.DIAMONDS, Card.Rank.KING),
                new Card(Card.Suit.SPADES, Card.Rank.SEVEN),
                new Card(Card.Suit.DIAMONDS, Card.Rank.SEVEN)
        )));
        assertEquals(FiveCardDrawHandEvalutor.PokerHand.FULL_HOUSE, handEvaluator.evaluateHand(hand));
    }


    @Test
    void fourOfKind() {
        HandEvaluator handEvaluator = new FiveCardDrawHandEvalutor();
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(
                new Card(Card.Suit.CLUBS, Card.Rank.KING),
                new Card(Card.Suit.HEARTS, Card.Rank.KING),
                new Card(Card.Suit.DIAMONDS, Card.Rank.KING),
                new Card(Card.Suit.SPADES, Card.Rank.KING),
                new Card(Card.Suit.DIAMONDS, Card.Rank.SEVEN)
        )));
        assertEquals(FiveCardDrawHandEvalutor.PokerHand.FOUR_OF_A_KIND, handEvaluator.evaluateHand(hand));
    }

    @Test
    void straightFlush() {
        HandEvaluator handEvaluator = new FiveCardDrawHandEvalutor();
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.TWO),
                new Card(Card.Suit.HEARTS, Card.Rank.THREE),
                new Card(Card.Suit.HEARTS, Card.Rank.FOUR),
                new Card(Card.Suit.HEARTS, Card.Rank.FIVE),
                new Card(Card.Suit.HEARTS, Card.Rank.SIX)
        )));
        assertEquals(FiveCardDrawHandEvalutor.PokerHand.STRAIGHT_FLUSH, handEvaluator.evaluateHand(hand));
    }


    @Test
    void royalStraightFlush() {
        HandEvaluator handEvaluator = new FiveCardDrawHandEvalutor();
        Hand hand = new Hand(new ArrayList<>(Arrays.asList(
                new Card(Card.Suit.HEARTS, Card.Rank.TEN),
                new Card(Card.Suit.HEARTS, Card.Rank.JACK),
                new Card(Card.Suit.HEARTS, Card.Rank.QUEEN),
                new Card(Card.Suit.HEARTS, Card.Rank.KING),
                new Card(Card.Suit.HEARTS, Card.Rank.ACE)
        )));
        assertEquals(FiveCardDrawHandEvalutor.PokerHand.ROYAL_STRAIGHT_FLUSH, handEvaluator.evaluateHand(hand));
    }

}