package pl.edu.agh.kis.pz1.util;

/**
 * Enum representing different types of commands that can be processed.
 */
//TODO logika laczenia enuma z liczba np BET:50 mozna zrobic albo w switchu w client logic handler tylko
    //TODO jak maja sie komunikowac przez enumy to nagle danie inta by robilo dodatkowa logike w przypadku bet raise i place bet
public enum CommandType {

    CALL,                    // Command to match the current bet
    FOLD,                    // Command to fold the hand
    CHECK,                   // Command to check without placing a bet
    RAISE,                   // Command to increase the bet
    ALL_IN,                  // Command to bet all remaining chips
    BET,                     // Command to bet if player was not required to do so earlier

    CHOOSE_GAME_TYPE,        // Command to choose the game type
    CHOOSE_JOIN_OR_START_NEW_GAME, // Command to join an existing game or start a new one
    START_GAME,              // Command to start the game
    END_GAME,                // Command indicating the game has ended
    PLACE_BET,               // Command to place a bet
    INVALID_RAISE,           // Command to communicate to player that he must try again
    INVALID_BET,             //Command to communicate to player that he must try again
    RAISE_OR_CALL,           //Command to communicate to player that
    OTHER_PLAYER_ACTION,     // Command to inform player what other player did

    UNKNOWN; // Command type is unknown or not recognized
}