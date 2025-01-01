package pl.edu.agh.kis.pz1.game;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.edu.agh.kis.pz1.cards.Card;
import pl.edu.agh.kis.pz1.cards.Hand;
import pl.edu.agh.kis.pz1.exceptions.NoChipsException;

import java.util.ArrayList;
import java.util.List;

/**
 * Player class is managing his status, cards, and chips
 */
public class Player {
    private static int playerCounter = 0;
    @Getter private final int PlayerId;
    @Getter(AccessLevel.PROTECTED) @Setter(AccessLevel.PROTECTED) private String name;
    @Getter(AccessLevel.PROTECTED) @Setter(AccessLevel.PROTECTED) private Hand hand;
    @Getter(AccessLevel.PROTECTED) @Setter(AccessLevel.PROTECTED) private int chips;
    @Getter @Setter private boolean isActive = true;
    public Player(String name, int chips, Hand hand) {
        this.name = name;
        this.chips = chips;
        this.hand = hand;
        this.PlayerId = ++playerCounter;
    }

    /**
     *
     * @param newChips
     * @return amount of chips given to pot (lost by the player)
     * @throws NoChipsException
     */
    public int adjustChips(int newChips) throws NoChipsException {
        if(chips <= 0 && newChips < 0) {
            throw new NoChipsException("Player" + name + " has already " + chips + " chips", PlayerId);
        }
        chips += newChips;
        return -1 * newChips;
    }

    public void deactivate() {
        isActive = false;
    }
}
