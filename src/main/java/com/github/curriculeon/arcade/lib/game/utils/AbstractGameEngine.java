package com.github.curriculeon.arcade.lib.game.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.curriculeon.arcade.lib.game.PlayerInterface;

/**
 * Created by leon on 2/25/18.
 * @ATTENTION_TO_STUDENTS - You are advised against modifying this class
 */
public abstract class AbstractGameEngine<
        GameTypePlayer extends PlayerInterface,
        GameType extends GameInterface<GameTypePlayer>>
        implements GameEngineInterface<GameTypePlayer, GameType> {

    private final GameType game;

    public AbstractGameEngine(GameType game) {
        this.game = game;
    }

    @Override
    public GameType getGame() {
        return game;
    }


    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (Throwable t) {
            return "AbstractGameEngine{" + "game=" + game + '}';
        }
    }
}
