package com.github.curriculeon.arcade.service.utils;

import com.github.curriculeon.arcade.lib.ArcadeInterface;
import com.github.curriculeon.arcade.lib.profile.ProfileManagerMenu;
import com.github.curriculeon.arcade.lib.profile.ProfileManagerSelection;
import com.github.curriculeon.arcade.lib.utils.DecisionInterface;
import com.github.curriculeon.arcade.lib.utils.DecisionMenu;

import java.util.function.Consumer;

/**
 * Created by leon on 6/25/2020.
 * @ATTENTION_TO_STUDENTS - You are advised against modifying this class
 */
public enum ArcadeDecision implements DecisionInterface {
    MANAGE_PROFILE((casino) -> {
        final DecisionMenu<ProfileManagerSelection> profileManagerMenu = new DecisionMenu<>(ProfileManagerSelection.values());
        final ProfileManagerSelection profileManagerSelection = profileManagerMenu.getInput();
        profileManagerSelection.perform(casino);
    }),

    SELECT_GAME((casino) -> {
        final DecisionMenu<GameSelection> gameSelectionMenu = new DecisionMenu<>(GameSelection.values());
        final GameSelection gameSelection = gameSelectionMenu.getInput();
        gameSelection.perform();
    }),

    EXIT((casino) -> {
        casino.getProfileManager().printProfilesInformation();
    });


    private final Consumer<ArcadeInterface> casinoConsumer;

    ArcadeDecision(Consumer<ArcadeInterface> casinoConsumer) {
        this.casinoConsumer = casinoConsumer;
    }

    public void perform(ArcadeInterface casino) {
        casinoConsumer.accept(casino);
    }
}