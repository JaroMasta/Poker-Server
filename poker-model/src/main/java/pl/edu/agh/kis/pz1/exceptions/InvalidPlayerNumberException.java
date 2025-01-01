package pl.edu.agh.kis.pz1.exceptions;

import lombok.Getter;

/**
 * Class throws exception if number of players isn't more than 3
 */
public class InvalidPlayerNumberException extends Exception{
    @Getter private final int numberOfPlayers;
    public InvalidPlayerNumberException(String message, int numberOfPlayers) {
        super(message);
        this.numberOfPlayers = numberOfPlayers;
    }
}
