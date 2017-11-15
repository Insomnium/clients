package client;

import client.lib.WebSocketRunner;
import client.lib.bomberman.SampleBombermanSolver;

public class ClientApp {

    private final static String URL = "52.232.32.105:8080";
    private final static String USERNAME = "picachu@pokemon.org";
    private final static String PASSWORD = "picachu@pokemon.org";

    public static void main(String[] args) {
        try {
            WebSocketRunner.run(URL, USERNAME, PASSWORD, new SampleBombermanSolver());
//            WebSocketRunner.run(URL, USERNAME, PASSWORD, new SampleLoderunnerSolver());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
