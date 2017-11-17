package client;

import client.lib.WebSocketRunner;
import client.lib.loderunner.SampleLoderunnerSolver;

public class ClientApp {

    private final static String URL = "10.27.13.136:8080";
    private final static String USERNAME = "auto13@test.org";
    private final static String PASSWORD = "qwerty";

    public static void main(String[] args) {
        try {
//            WebSocketRunner.run(URL, USERNAME, PASSWORD, new SampleBombermanSolver());
            WebSocketRunner.run(URL, USERNAME, PASSWORD, new SampleLoderunnerSolver());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
