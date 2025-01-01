package pl.edu.agh.kis.pz1.exceptions;

/**
 * Klasa rzuca wyjatek jezeli gracz probuje wykonac ruch niezgodny z regulami
 * Throws an exception when there's soemrthing breaking the rules
 */
public class IllegalMoveException extends Exception{
    public IllegalMoveException(String message) {
        super(message);
    }
}
