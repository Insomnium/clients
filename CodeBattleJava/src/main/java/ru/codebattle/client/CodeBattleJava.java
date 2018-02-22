package ru.codebattle.client;


import lombok.SneakyThrows;
import ru.codebattle.client.domain.Action;
import ru.codebattle.client.domain.BombermanBlock;

import java.net.URISyntaxException;
import java.util.*;

public class CodeBattleJava {

//    private static final String SERVER_ADDRESS = "52.178.183.203:8080";
    private static final String SERVER_ADDRESS = "localhost:8081";
//    private static final String PLAYER_NAME = "bot0@bot.secret";
//    private static final String AUTH_CODE = "10432978061512349060";
    private static final String PLAYER_NAME = "regular@mail.org";
    private static final String AUTH_CODE = "12645402411403548524";

    private static final Set<BombermanBlock> BLOCK_TYPES = Collections.unmodifiableSet(new HashSet<BombermanBlock>() {{
        add(BombermanBlock.WALL);
        add(BombermanBlock.DESTROY_WALL);
        add(BombermanBlock.MEAT_CHOPPER);
        add(BombermanBlock.BOMB_TIMER_1);
        add(BombermanBlock.BOMB_TIMER_2);
        add(BombermanBlock.BOMB_TIMER_3);
        add(BombermanBlock.BOMB_TIMER_4);
        add(BombermanBlock.BOMB_TIMER_5);
        add(BombermanBlock.OTHER_BOMBERMAN);
        add(BombermanBlock.OTHER_BOMB_BOMBERMAN);
    }});

    private static final Map<String, String> SAMPLE_PLAYERS = Collections.unmodifiableMap(
            new HashMap<String, String>() {{
//                put("AristarhkStepanovich@mail.org", "199156891617850128");
                put("JohnDoe@mail.org", "19538010221597751223");
                put("SerseyaIvanovna@mail.org", "111276748767943530");
                put("bomber_bot@mail.org", "1987626998751380587");
                put("iivanov@mail.org", "16688974112085572133");
                put("zerocool@mail.org", "15494554602085234076");
                put("Dima@mail.org", "2008272492740297775");
                put("Nikita@mail.org", "1789694651472879178");
                put("Natasha@mail.org", "2800432011057222690");
                put("Masha@mail.org", "5146841711011993173");
                put("Sergei@mail.org", "1908538430285206148");
                put("Sasha@mail.org", "746931375947464949");
                put("Veronika@mail.org", "1971406992193270485");
                put("Igor@mail.org", "20781506761381890555");
            }}
    );

    @SneakyThrows
    public static void main(String[] args) {
//        runSampler();
//        runConsoleClient(true);
        runRandomClient(true, SERVER_ADDRESS, "bot0@bot.secret", "10432978061512349060");
        runRandomClient(false, SERVER_ADDRESS, "regular@mail.org", "12645402411403548524");
    }

    private static void runSampler() {
        new ClientSampler(SAMPLE_PLAYERS, "epruizhw0172.moscow.epam.com:8080").run();
    }

    private static void runConsoleClient(boolean chopper) throws URISyntaxException {
        CodeBattleJavaLibrary client = !chopper
                ? new CodeBattleJavaLibrary(SERVER_ADDRESS, PLAYER_NAME, AUTH_CODE)
                : new CodeBattleJavaLibrary(SERVER_ADDRESS, PLAYER_NAME, AUTH_CODE, true);
        Scanner scanner = new Scanner(System.in);

        client.run(map -> {
            String read = scanner.next();
            switch (read) {
                case "w":
                    client.up();
                    break;
                case "s":
                    client.down();
                    break;
                case "a":
                    client.left();
                    break;
                case "d":
                    client.right();
                    break;
                case "b":
                    client.act();
                    break;
            }
        });
    }

    private static void runRandomClient(boolean chopper, String address, String playerName, String authCode) throws URISyntaxException {
        CodeBattleJavaLibrary client = !chopper
                ? new CodeBattleJavaLibrary(address, playerName, authCode)
                : new CodeBattleJavaLibrary(address, playerName, authCode, true);
        Random random = new Random();
        client.run(map -> {
            boolean done = false;
            switch (random.nextInt(chopper ? 4 : 5)) {
                case 0:
                    if (!isBlock(map[client.getPlayerX()][client.getPlayerY() - 1])) {
                        client.up(Action.BEFORE_TURN);
                        done = true;
                    }
                    break;
                case 1:
                    if (!isBlock(map[client.getPlayerX() + 1][client.getPlayerY()])) {
                        client.right(Action.BEFORE_TURN);
                        done = true;
                    }
                    break;
                case 2:
                    if (!isBlock(map[client.getPlayerX()][client.getPlayerY() + 1])) {
                        client.down(Action.BEFORE_TURN);
                        done = true;
                    }
                    break;
                case 3:
                    if (!isBlock(map[client.getPlayerX() -1 ][client.getPlayerY()])) {
                        client.left(Action.BEFORE_TURN);
                        done = true;
                    }
                    break;
                case 4:
                    client.act();
                    done = true;
                    break;
            }
            if (!done) {
                client.blank();
            }
        });
    }

    private static boolean isBlock(BombermanBlock e) {
        return BLOCK_TYPES.contains(e);
    }
}
