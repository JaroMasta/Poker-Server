package pl.edu.agh.kis.pz1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Optional;

/**
 * Manages the client connection to server and sending/receiving.
 * Client that communicates with the PokerServer.
 */
public class PokerClient {
    private SocketChannel clientChannel;
    private ByteBuffer buffer;

    /**
     * Connects the client to the server.
     *
     * @param host The server's hostname or IP address.
     * @param port The server's port.
     */
    public void connect(String host, int port) {
        try {
            // Open a socket channel and connect to the server
            clientChannel = SocketChannel.open(new InetSocketAddress(host, port));
            buffer = ByteBuffer.allocate(256); // Buffer for sending/receiving data
            System.out.println("Connected to the server: " + host + ":" + port);
        } catch (IOException e) {
            System.err.println("Failed to connect to the server: " + e.getMessage());
        }
    }

    /**
     * Sends a message to the server.
     *
     * @param message The message to send.
     */
    public void sendMessage(String message) {
        try {
            buffer.clear();
            buffer.put(message.getBytes());
            buffer.flip();
            clientChannel.write(buffer);
            System.out.println("Sent: " + message);
        } catch (IOException e) {
            System.err.println("Error sending message: " + e.getMessage());
        }
    }
    /**
     * Receives a message from the server.
     * @return Optional String (containing message from server) that is empty if something went wrong
     */
    public Optional<String> receiveMessage() {
        try {
            buffer.clear();
            int bytesRead = clientChannel.read(buffer);
            if (bytesRead > 0) {
                buffer.flip();
                String message = new String(buffer.array(), 0, bytesRead);
                return Optional.of(message);
            } else if (bytesRead == -1) {
                System.out.println("Server has closed the connection.");
                closeConnection();
            }
        } catch (IOException e) {
            System.err.println("Error receiving message: " + e.getMessage());
        }
        return Optional.empty();
    }
    /**
     * Receives a message from the server and prints it.
     */
    public void receiveAndPrintMessage() {
        try {
            buffer.clear();
            int bytesRead = clientChannel.read(buffer);
            if (bytesRead > 0) {
                buffer.flip();
                String message = new String(buffer.array(), 0, bytesRead);
                System.out.println("Received: " + message);
            } else if (bytesRead == -1) {
                System.out.println("Server has closed the connection.");
                closeConnection();
            }
        } catch (IOException e) {
            System.err.println("Error receiving message: " + e.getMessage());
        }
    }

    /**
     * Closes the connection to the server.
     */
    public void closeConnection() {
        try {
            if (clientChannel != null && clientChannel.isOpen()) {
                clientChannel.close();
                System.out.println("Disconnected from the server.");
            }
        } catch (IOException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}