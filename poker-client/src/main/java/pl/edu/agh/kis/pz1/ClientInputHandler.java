package pl.edu.agh.kis.pz1;

import lombok.EqualsAndHashCode;
import pl.edu.agh.kis.pz1.util.CommandType;

import java.util.HashMap;
import java.util.Map;

public class ClientInputHandler {


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
