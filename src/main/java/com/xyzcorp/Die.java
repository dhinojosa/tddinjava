package com.xyzcorp;

import java.util.Random;

public class Die {

    private int pips;
    private Random random;

    public Die(Random random) {
        this.pips = 1;
        this.random = random;
    }

    public Die(int pips) {
        this.pips = pips;
    }

    public int getPips() {
        return pips;
    }

    public Die roll() {
        return new Die(1 + random.nextInt(6));
    }
}
