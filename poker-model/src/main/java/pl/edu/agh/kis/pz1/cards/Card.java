package pl.edu.agh.kis.pz1.cards;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Card class that generates an object with 2 attributes: Suit and Rank
 */
public final class Card implements Comparable<Card> {

    private final Suit suit;
    private final Rank rank;
    public enum Suit {
        HEARTS(1), DIAMONDS(2), CLUBS(3), SPADES(4);

        private final int value;
        Suit(int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }
    }

    public enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
        EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);

        private final int value;
        Rank(int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }
    }


    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit(){
        return suit;
    }

    public Rank getRank(){
        return rank;
    }

    public String toString(){
        return rank+" of "+suit;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }

    @Override
    public int compareTo(@NotNull Card o) {
       int compareRankValue = Integer.compare(this.rank.getValue(), o.rank.getValue());
       if (compareRankValue != 0) return compareRankValue;
       return Integer.compare(this.suit.getValue(), o.suit.getValue());
    }
}
