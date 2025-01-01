package pl.edu.agh.kis.pz1.exceptions;

import lombok.Getter;

/**
 * Klasa rzuca wyjatek jezeli podany został nipoprawny numer
 */
public class WrongNumberException extends Exception{
    @Getter
    private final int number;
    public WrongNumberException(String message, int number) {
        super(message);
        this.number = number;
    }

}