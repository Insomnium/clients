package ru.codebattle.client;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.codebattle.client.domain.BombermanBlock;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ru.codebattle.client.domain.Action.BEFORE_TURN;
import static ru.codebattle.client.domain.BombermanBlock.*;

public class ClientSampler {
    private final Map<String, String> playerCredentials;
    private final String serverAddress;
    private final ExecutorService executorService;

    public ClientSampler(Map<String, String> playerCredentials, String serverAddress) {
        this.playerCredentials = playerCredentials;
        this.serverAddress = serverAddress;
        this.executorService = Executors.newFixedThreadPool(playerCredentials.size());
    }

    public void run() {
        playerCredentials.forEach((key, value) -> executorService.submit(new Client(key, value, serverAddress)));
        executorService.shutdown();
    }

    @RequiredArgsConstructor
    private class Client implements Runnable {
        @NonNull
        private final String email;
        @NonNull
        private final String authCode;
        @NonNull
        private final String serverAddress;

        private final Set<BombermanBlock> BLOCK_TYPES = Collections.unmodifiableSet(new HashSet<BombermanBlock>() {{
            add(WALL);
            add(DESTROY_WALL);
            add(MEAT_CHOPPER);
            add(BOMB_TIMER_1);
            add(BOMB_TIMER_2);
            add(BOMB_TIMER_3);
            add(BOMB_TIMER_4);
            add(BOMB_TIMER_5);
            add(OTHER_BOMBERMAN);
            add(OTHER_BOMB_BOMBERMAN);
        }});

        @Override
        @SneakyThrows
        public void run() {
            CodeBattleJavaLibrary client = new CodeBattleJavaLibrary(serverAddress, email, authCode);
            Random random = new Random();
            client.run(map -> {
                boolean done = false;
                switch (random.nextInt(5)) {
                    case 0:
                        if (!isBlock(map[client.getPlayerX()][client.getPlayerY() - 1])) {
                            client.up(BEFORE_TURN);
                            done = true;
                        }
                        break;
                    case 1:
                        if (!isBlock(map[client.getPlayerX() + 1][client.getPlayerY()])) {
                            client.right(BEFORE_TURN);
                            done = true;
                        }
                        break;
                    case 2:
                        if (!isBlock(map[client.getPlayerX()][client.getPlayerY() + 1])) {
                            client.down(BEFORE_TURN);
                            done = true;
                        }
                        break;
                    case 3:
                        if (!isBlock(map[client.getPlayerX() -1 ][client.getPlayerY()])) {
                            client.left(BEFORE_TURN);
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

        private boolean isBlock(BombermanBlock e) {
            return BLOCK_TYPES.contains(e);
        }
    }
}
