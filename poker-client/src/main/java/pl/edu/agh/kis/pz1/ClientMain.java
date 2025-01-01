package pl.edu.agh.kis.pz1;

import java.util.Scanner;

/**
 * Entry point for the Poker client application.
 */
public class ClientMain {

    // Constants for server connection details
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8080;

    /**
     * Main method to start the Poker client.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        PokerClient client = new PokerClient();

        try {
            // Attempt to connect to the server
            client.connect(SERVER_HOST, SERVER_PORT);
            System.out.println("Connected to the server.");

            // Send initial message to the server
            client.sendMessage("Hello, server!");

            // Start client logic handling
            try (Scanner scanner = new Scanner(System.in)) {
                ClientLogicHandler clientLogicHandler = new ClientLogicHandler(client, scanner);
                clientLogicHandler.start();
            }
        } catch (Exception e) {
            System.err.println("An error occurred during client execution: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Ensure the client connection is closed
            closeClientConnection(client);
        }
    }

    /**
     * Closes the client connection gracefully.
     *
     * @param client the Poker client instance
     */
    private static void closeClientConnection(PokerClient client) {
        try {
            client.closeConnection();
            System.out.println("Client connection closed.");
        } catch (Exception e) {
            System.err.println("Failed to close client connection: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
