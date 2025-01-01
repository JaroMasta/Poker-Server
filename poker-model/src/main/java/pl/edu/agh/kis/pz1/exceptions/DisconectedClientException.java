package pl.edu.agh.kis.pz1.exceptions;

import lombok.Getter;

/**
 * Klasa rzuca wyjatek jezeli client is
 */
public class DisconectedClientException extends Exception{
    @Getter
    private final int playerid;
    public DisconectedClientException(String message, int playerId){
        super(message);
        this.playerid = playerId;
    }

}