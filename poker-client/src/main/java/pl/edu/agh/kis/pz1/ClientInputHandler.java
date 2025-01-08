package pl.edu.agh.kis.pz1;

import lombok.EqualsAndHashCode;
import pl.edu.agh.kis.pz1.util.CommandType;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientInputHandler {


    // Map storing the mapping between messages and their corresponding command types
    private static final Map<String, CommandType> MESSAGE_TO_COMMAND;

    static {
       MESSAGE_TO_COMMAND  = Stream.of(CommandType.values()).collect(Collectors.toMap(CommandType::toString, commandType -> commandType));
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
