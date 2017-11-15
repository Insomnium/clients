package client.lib;

import static client.lib.Action.NONE;
import static java.lang.String.format;


public abstract class Solver<E extends Enum> {

    private static final String UP_DIR = "UP";
    private static final String DOWN_DIR = "DOWN";
    private static final String LEFT_DIR = "LEFT";
    private static final String RIGHT_DIR = "RIGHT";
    private static final String ACTION = "ACT";

    protected int size;
    protected E[][] field;

    private String act(String direction, Action action) {
        action = action == null ? NONE : action;
        return format("%s%s%s", action.getPreTurn(), direction, action.getPostTurn());
    }

    protected final String up(Action action) {
        return act(UP_DIR, action);
    }

    protected final String down(Action action) {
        return act(DOWN_DIR, action);
    }

    protected final String left(Action action) {
        return act(LEFT_DIR, action);
    }

    protected final String right(Action action) {
        return act(RIGHT_DIR, action);
    }

    protected final String act() {
        return act(ACTION, NONE);
    }

    protected final String up() {
        return act(UP_DIR, NONE);
    }

    protected final String down() {
        return act(DOWN_DIR, NONE);
    }

    protected final String left() {
        return act(LEFT_DIR, NONE);
    }

    protected final String right() {
        return act(RIGHT_DIR, NONE);
    }

    protected abstract E charToElement(char c);

    protected abstract E[][] initField();

    public abstract String move();

    /**
     * Метод парсинга игрового поля. Вызывается после ответа сервера
     *
     * @param boardString игровое поле
     */
    void parseField(String boardString) {
        String board = boardString.replaceAll("\n", "");

        size = (int) Math.sqrt(board.length());
        field = initField();

        char[] temp = board.toCharArray();
        for (int y = 0; y < size; y++) {
            int dy = y * size;
            for (int x = 0; x < size; x++) {
                field[x][y] = charToElement(temp[dy + x]);
            }
        }
    }
}
