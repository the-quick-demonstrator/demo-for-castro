package com.github.curriculeon.arcade.lib.utils.decision;

import com.github.curriculeon.arcade.service.highlow.HighLowGame;
import com.github.curriculeon.arcade.service.highlow.HighLowGameDecision;

import java.util.Arrays;
import java.util.StringJoiner;

public class DecisionMenu
        <SomeDecision extends Enum<SomeDecision> & DecisionInterface>
        extends AbstractDecisionMenu<SomeDecision> {
    public DecisionMenu(SomeDecision[] decisions) {
        super(decisions);
    }
    @Override
    public String toString() {
        final SomeDecision[] decisions = getDecisions();
        return new StringJoiner("\n")
                .add(String.format("Welcome to the %s menu!", decisions[0].getClass().getSimpleName()))
                .add("From here you can select any of the following options:\n\t")
                .add(Arrays.toString(decisions))
                .toString();
    }
}
