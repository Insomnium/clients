package client.lib.loderunner;


import client.lib.Solver;

public abstract class LoderunnerSolver extends Solver<LoderunnerBlock> {

    @Override
    protected LoderunnerBlock charToElement(char c) {
        return LoderunnerBlock.valueOf(c);
    }

    @Override
    protected LoderunnerBlock[][] initField() {
        return new LoderunnerBlock[size][size];
    }
}
