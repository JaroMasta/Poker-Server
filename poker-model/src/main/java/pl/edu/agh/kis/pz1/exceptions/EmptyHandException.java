package pl.edu.agh.kis.pz1.exceptions;

import lombok.Getter;

/**
 * Klasa rzuca wyjatek jezeli gracz probuje grac, nie majac kart
 */
public class EmptyHandException extends Exception{
    @Getter private final int playerid;
    public EmptyHandException(String message, int playerId){
        super(message);
        this.playerid = playerId;
    }

}
