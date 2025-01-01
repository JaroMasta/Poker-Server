package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.util.TextUtils;

/**
 * Main class for running the server
 */
public class ServerMain {

    public static void main(String[] args) {

        PokerServer pokerServer = new PokerServer();
        pokerServer.start();
    }
}
