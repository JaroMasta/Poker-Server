package pl.edu.agh.kis.pz1.cards;

import lombok.*;
import pl.edu.agh.kis.pz1.rules.FiveCardDrawRules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class representing cards that player has.
 * Zamienic @data zeby nie bylo setterow dla karty zeby zwiekszyc bezpieczenstwo
 */

@AllArgsConstructor
@RequiredArgsConstructor
public class Hand {
    @NonNull
    private List<Card> cards;
    @Getter @Setter(AccessLevel.PROTECTED)
    public boolean isSorted = false;
    public Card showCard(int number){
        try{
            return cards.get(number);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("No such card");
            return null;
        }
    }
    public void drawCard(Card card){
        if (cards.size() >= FiveCardDrawRules.getAllowedHandSize()){
            //throw new IllegalMoveException("A hand shouldn't be allowed to draw a card.")
        }
        if (card != null){
            cards.add(card);
            isSorted = false;
        }
        else{
            throw new NullPointerException("Cannot draw a null card.");
        }
    }
    public Card discardCard(int number){
        return cards.remove(number);
    }
    public void sortCardsInHand() {
        Collections.sort(cards);
        isSorted = true;
    }

    public int getHandSize(){return cards.size();}
    public List<Card> getCards(){return new ArrayList<>(cards);}

}
