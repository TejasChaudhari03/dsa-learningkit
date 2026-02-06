package javaprograms;

import java.util.Random;

public class GuessGame {

    private int pick;

    public GuessGame(int n) {
        this.pick = new Random().nextInt(n) + 1;
        System.out.println("Number picked between 1 and " + n);
    }

    protected int guess(int num) {
        if (num > pick) return -1;
        if (num < pick) return 1;
        return 0;
    }

    protected int getPick() {
        return pick;
    }
}
