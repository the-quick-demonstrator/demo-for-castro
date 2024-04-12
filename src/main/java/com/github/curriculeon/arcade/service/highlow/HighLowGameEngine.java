package com.github.curriculeon.arcade.service.highlow;

import com.github.curriculeon.arcade.lib.game.cardgame.utils.card.CardInterface;
import com.github.curriculeon.arcade.lib.game.utils.AbstractGameEngine;
import com.github.curriculeon.arcade.lib.utils.Pair;
import com.github.curriculeon.arcade.lib.utils.decision.DecisionMenu;
import com.github.curriculeon.arcade.lib.utils.logging.InputOutputSocketInterface;
import com.github.curriculeon.arcade.lib.utils.logging.LoggingProxyFactory;

/**
 * Created by leon on 6/24/2020.
 */
public class HighLowGameEngine extends AbstractGameEngine<HighLowPlayer, HighLowGameInterface> implements InputOutputSocketInterface {
    private Pair<HighLowPlayer, CardInterface> highestScoringPlayerAndCard;

    public HighLowGameEngine() {
        this(new HighLowGame());
    }

    public HighLowGameEngine(final HighLowGame game) {
        super(game);
    }

    @Override
    public HighLowGameInterface getGame() {
        return LoggingProxyFactory.createProxy(super.getGame());
    }

    @Override
    public void run() {
        do {
            super.run();
            getConsole().println(
                    "The winner is [ %s ], with a card value of [ %s ]",
                    highestScoringPlayerAndCard.getKey().getName(),
                    highestScoringPlayerAndCard.getValue().getValue());
        } while (!getGame().getDeck().isEmpty());
    }

    @Override
    public void evaluateTurn(HighLowPlayer currentPlayer) {
        final DecisionMenu<HighLowGameDecision> highLowGameDecision = new DecisionMenu<>(HighLowGameDecision.values());
        boolean playerTurnIsOver;
        do {
            final HighLowGameDecision decision = highLowGameDecision.getInput();
            decision.perform(getGame(), currentPlayer);
            getGame().evaluateCardAndPlayer(getGame().getDiscardPile().getPlayerAndCardAtIndex(0));
            this.highestScoringPlayerAndCard = getGame().getHighestScoringPlayerAndCard();
            playerTurnIsOver =
                    HighLowPlayer.DecisionState.HIGH.equals(currentPlayer.getDecision()) ||
                    HighLowPlayer.DecisionState.LOW.equals(currentPlayer.getDecision()) ||
                    HighLowPlayer.DecisionState.BLUFF.equals(currentPlayer.getDecision());
        } while (!playerTurnIsOver);
    }
}
