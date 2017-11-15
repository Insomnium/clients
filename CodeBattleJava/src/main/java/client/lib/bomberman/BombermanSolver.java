package client.lib.bomberman;


import client.lib.Solver;

public abstract class BombermanSolver extends Solver<BombermanBlock> {

    @Override
    protected BombermanBlock charToElement(char c) {
        return BombermanBlock.valueOf(c);
    }

    @Override
    protected BombermanBlock[][] initField() {
        return new BombermanBlock[size][size];
    }
}
