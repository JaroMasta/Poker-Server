package pl.edu.agh.kis.pz1.exceptions;

import lombok.Getter;

/**
 * Klasa rzuca wyjatek jezeli gracz probuje dac zetony, ktorych nie ma
 */
public class NoChipsException extends Exception{
    @Getter private final int playerId;
    public NoChipsException(String message, int playerId) {
        super(message);
        this.playerId = playerId;
    }

}
