package com.github.curriculeon.arcade.lib.utils;

public class DecisionMenu
        <SomeDecision extends Enum<SomeDecision> & DecisionInterface>
        implements MenuInterface<SomeDecision> {
    private SomeDecision[] decisions;

    public DecisionMenu(SomeDecision[] decisions) {
        this.decisions = decisions;
    }

    @Override
    public SomeDecision[] getDecisions() {
        return decisions;
    }
}
