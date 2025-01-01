package pl.edu.agh.kis.pz1.game;

import pl.edu.agh.kis.pz1.cards.Card;
import pl.edu.agh.kis.pz1.cards.Hand;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class HandEvaluator {
    public abstract FiveCardDrawHandEvalutor.PokerHand evaluateHand(Hand hand);
    protected Map<Card.Rank, Long> groupAndCountByRank(List<Card> cards){
        return cards.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
    }
    protected Map<Card.Suit, Long> groupAndCountBySuit(List<Card> cards){
        return cards.stream()
                .collect(Collectors.groupingBy(Card::getSuit, Collectors.counting()));
    }
    protected boolean hasPair(List<Long> longRankList) {
        return longRankList.get(longRankList.size()-1) == 2;
    }
    protected boolean hasTwoPair(List<Long> longRankList) {
        return longRankList.get(longRankList.size()-1) == 2 && longRankList.get(longRankList.size()-2) == 2;
    }
    protected boolean hasThreeOfAKind(List<Long> longRankList) {
        return longRankList.get(longRankList.size()-1) == 3;
    }
    protected boolean hasStraight(List<Card> cards) {
        for (int i = 1; i < cards.size(); ++i){
            if (  cards.get(i).getRank().getValue() - cards.get(i-1).getRank().getValue()  != 1){
                return false;
            }
        }
        return true;
    }
    protected boolean hasFlush(long maxSuitNumber) {
        return maxSuitNumber == 5;
    }

    protected boolean hasFullHouse(List<Long> longRankList) {
        return longRankList.size() == 2;
    }

    protected boolean hasFourOfKind(long maxNumber) {
        return maxNumber == 4;
    }

    protected boolean hasStraightFlush(boolean isStraight, long maxSuitNumber) {
        return isStraight && maxSuitNumber == 5;
    }
    protected boolean hasRoyalStraightFlush(boolean isStraight, Card highCard, long maxSuitNumber) {
        return isStraight && highCard.getRank() == Card.Rank.ACE && maxSuitNumber == 5;
    }
}
