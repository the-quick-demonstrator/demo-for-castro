package com.github.curriculeon.arcade.lib.game.utils;

import com.github.curriculeon.arcade.lib.game.PlayerInterface;
import com.github.curriculeon.arcade.lib.profile.ProfileInterface;
import com.github.curriculeon.arcade.lib.profile.ProfileManager;
import com.github.curriculeon.arcade.lib.utils.logging.AnsiColor;
import com.github.curriculeon.arcade.lib.utils.logging.InputOutputConsoleInterface;
import com.github.curriculeon.arcade.lib.utils.logging.InputOutputSocketInterface;

import java.util.List;

/**
 * Created by leon on 2/25/18.
 * @ATTENTION_TO_STUDENTS - You are advised against modifying this class
 */
public interface GameInterface<PlayerType extends PlayerInterface> extends Runnable, InputOutputSocketInterface {
    List<PlayerType> getPlayers();

    void run();

    @Override
    default InputOutputConsoleInterface getConsole() {
        return InputOutputSocketInterface.super.getConsole(AnsiColor.BLUE);
    }

    PlayerType createPlayer(ProfileInterface profile);

    default void createPlayers() {
        final Integer numberOfPlayers = getConsole().getIntegerInput("How many players will be playing?");
        for (int i = 0; i < numberOfPlayers; i++) {
            final String infoMessage = "Player number [ %s ], enter your profile id.";
            final Long playerId = getConsole().getLongInput(infoMessage, i);
            final ProfileInterface profile = ProfileManager.INSTANCE.getProfileById(playerId);
            final PlayerType player = createPlayer(profile);
            addPlayer(player);
        }
    }

    default void addPlayer(PlayerType player) {
        getConsole().println("Adding player [ %s ] to game", player.getName());
        getPlayers().add(player);
        getConsole().println("Player [ %s ] has been added to the game", player.getName());
    }

    default void removePlayer(PlayerType player) {
        getPlayers().add(player);
    }

    default Boolean contains(PlayerType player) {
        return getPlayers().contains(player);
    }
}
