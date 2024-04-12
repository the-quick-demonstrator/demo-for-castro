package com.github.curriculeon.arcade.service.highlow;

import com.github.curriculeon.arcade.lib.game.cardgame.utils.AbstractCardGame;
import com.github.curriculeon.arcade.lib.game.cardgame.utils.card.CardInterface;
import com.github.curriculeon.arcade.lib.utils.Pair;

/**
 * Created by leon on 6/24/2020.
 */
public class HighLowGame extends AbstractCardGame<HighLowPlayer> implements HighLowGameInterface {
    private Pair<HighLowPlayer, CardInterface> highestScoringPlayerAndCard;

    @Override
    public Pair<HighLowPlayer, CardInterface> getHighestScoringPlayerAndCard() {
        return highestScoringPlayerAndCard;
    }

    @Override
    public void setHighestScoringPlayerAndCard(Pair<HighLowPlayer, CardInterface> playerAndCard) {
        this.highestScoringPlayerAndCard = playerAndCard;
    }
}
