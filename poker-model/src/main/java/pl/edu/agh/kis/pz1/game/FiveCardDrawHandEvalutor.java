package pl.edu.agh.kis.pz1.game;

import org.jetbrains.annotations.NotNull;
import pl.edu.agh.kis.pz1.cards.Card;
import pl.edu.agh.kis.pz1.cards.Hand;
import pl.edu.agh.kis.pz1.rules.FiveCardDrawRules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class for evalutaing player's hand.
 */
public class FiveCardDrawHandEvalutor extends HandEvaluator{

    public enum PokerHand{
        ROYAL_STRAIGHT_FLUSH(10),
        STRAIGHT_FLUSH(9),
        FOUR_OF_A_KIND(8),
        FULL_HOUSE(7),
        FLUSH(6),
        STRAIGHT(5),
        THREE_OF_A_KIND(4),
        TWO_PAIRS(3),
        ONE_PAIR(2),
        HIGH_CARD(1);

        private final int value;
        PokerHand(int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }
    }

    public PokerHand evaluateHand(@NotNull Hand hand){
        if (hand.getHandSize() != FiveCardDrawRules.getAllowedHandSize()){
            throw new IllegalArgumentException("Hand must have exactly 5 Cards");
        }
        if (!hand.isSorted()) {hand.sortCardsInHand();}
        List<Card> cards = hand.getCards();
        boolean isStraight = hasStraight(cards);
        List<Long> longSuitList = new ArrayList<Long>(groupAndCountBySuit(cards).values());
        Collections.sort(longSuitList);
        Card highCard = cards.get(cards.size() - 1);;
        Long maxSuitNumber = longSuitList.get(longSuitList.size() - 1);
        if (hasRoyalStraightFlush(isStraight, highCard, maxSuitNumber)){ return PokerHand.ROYAL_STRAIGHT_FLUSH; }
        if(hasStraightFlush(isStraight, maxSuitNumber)){ return PokerHand.STRAIGHT_FLUSH; }
        List<Long> longRankList = new ArrayList<Long>(groupAndCountByRank(cards).values());
        Collections.sort(longRankList);
        if(hasFourOfKind(longRankList.get(longRankList.size()-1))) { return PokerHand.FOUR_OF_A_KIND; }
        if(hasFullHouse(longRankList)) { return PokerHand.FULL_HOUSE; }
        if (hasFlush(maxSuitNumber)) { return PokerHand.FLUSH; }
        if(isStraight) { return PokerHand.STRAIGHT; }
        if(hasThreeOfAKind(longRankList)) { return PokerHand.THREE_OF_A_KIND; }
        if (hasTwoPair(longRankList)) { return PokerHand.TWO_PAIRS; }
        if(hasPair(longRankList)) { return PokerHand.ONE_PAIR; }
        return PokerHand.HIGH_CARD;

    }


}
