package com.github.curriculeon.arcade.lib.game.cardgame.utils.card;

public interface RankInterface {
    int[] getValues();

    default int getPrimaryValue() {
        return getValues()[0];
    }
}
