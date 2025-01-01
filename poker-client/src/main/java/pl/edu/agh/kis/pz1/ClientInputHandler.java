package pl.edu.agh.kis.pz1;

import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

public class ClientInputHandler {

    /**
     * Enum representing different types of commands that can be processed.
     */
    public enum CommandType {
        CHOOSE_GAME_TYPE,        // Command to choose the game type
        CHOOSE_JOIN_OR_START_NEW_GAME, // Command to join an existing game or start a new one
        START_GAME,              // Command to start the game
        END_GAME,                // Command indicating the game has ended
        PLACE_BET,               // Command to place a bet
        CALL,                    // Command to match the current bet
        FOLD,                    // Command to fold the hand
        CHECK,                   // Command to check without placing a bet
        RAISE,                   // Command to increase the bet
        ALL_IN,                  // Command to bet all remaining chips
        UNKNOWN;                 // Command type is unknown or not recognized
    }

    // Map storing the mapping between messages and their corresponding command types
    private static final Map<String, CommandType> MESSAGE_TO_COMMAND = new HashMap<>();

    static {
        // Initialize the map with predefined message-to-command mappings
        MESSAGE_TO_COMMAND.put("You are the first one, choose game type", CommandType.CHOOSE_GAME_TYPE);
        MESSAGE_TO_COMMAND.put("Here are game types please choose one", CommandType.CHOOSE_GAME_TYPE);
        MESSAGE_TO_COMMAND.put("Would you like to join an existing game or start a new one?", CommandType.CHOOSE_JOIN_OR_START_NEW_GAME);
        MESSAGE_TO_COMMAND.put("Game is starting now!", CommandType.START_GAME);
        MESSAGE_TO_COMMAND.put("The game has ended, thank you for playing!", CommandType.END_GAME);

        // Poker-specific messages
        MESSAGE_TO_COMMAND.put("Place your bet", CommandType.PLACE_BET);
        MESSAGE_TO_COMMAND.put("Do you want to call?", CommandType.CALL);
        MESSAGE_TO_COMMAND.put("Do you want to fold?", CommandType.FOLD);
        MESSAGE_TO_COMMAND.put("It's your turn, do you want to check?", CommandType.CHECK);
        MESSAGE_TO_COMMAND.put("Would you like to raise?", CommandType.RAISE);
        MESSAGE_TO_COMMAND.put("Go all-in or continue?", CommandType.ALL_IN);
    }

    /**
     * Maps an input message to its corresponding command type.
     *
     * @param message the input message to be mapped
     * @return the corresponding {@link CommandType} if a match is found;
     *         {@link CommandType#UNKNOWN} otherwise
     */
    public static CommandType mapMessageToCommand(String message) {
        if (message == null || message.isBlank()) {
            return CommandType.UNKNOWN; // Return UNKNOWN for null or blank messages
        }
        return MESSAGE_TO_COMMAND.getOrDefault(message.trim(), CommandType.UNKNOWN);
    }
}
