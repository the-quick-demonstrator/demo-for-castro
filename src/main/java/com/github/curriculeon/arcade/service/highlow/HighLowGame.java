package com.github.curriculeon.arcade.service.highlow;

import com.github.curriculeon.arcade.lib.game.cardgame.utils.AbstractCardGame;
import com.github.curriculeon.arcade.lib.game.cardgame.utils.Deck;
import com.github.curriculeon.arcade.lib.game.cardgame.utils.card.CardInterface;
import com.github.curriculeon.arcade.lib.profile.Profile;
import com.github.curriculeon.arcade.lib.profile.ProfileInterface;
import com.github.curriculeon.arcade.lib.utils.Pair;

/**
 * Created by leon on 6/24/2020.
 */
public class HighLowGame extends AbstractCardGame<HighLowPlayer> {
    private Pair<HighLowPlayer, CardInterface> highestScoringPlayerAndCard;

    @Override
    public HighLowPlayer createPlayer(ProfileInterface profile) {
        return new HighLowPlayer(profile);
    }

    @Override
    public void run() {
        HighLowPlayer dealer = new HighLowPlayer(new Profile("DEALER", Double.MAX_VALUE, null));
        getDiscardPile().add(dealer, getDeck().pop());
        Deck deck = new Deck();
        deck.shuffle();
        getPlayers().forEach(player -> player.addCard(deck.pop()));
    }

    public CardInterface getCurrentFaceUpValue() {
        return getDiscardPile().getPlayerAndCardAtIndex(0).getValue();
    }

    public void evaluateCardAndPlayer(Pair<HighLowPlayer, CardInterface> ownerAndCard) {
        if (highestScoringPlayerAndCard != null) {
            final int currentPlayerCardValue = getCurrentFaceUpValue().getValue();
            final int highestScoringPlayerCardValue = highestScoringPlayerAndCard.getValue().getValue();

            if (highestScoringPlayerCardValue < currentPlayerCardValue) {
                highestScoringPlayerAndCard = new Pair<>(ownerAndCard.getKey(), getCurrentFaceUpValue());
            }
        } else {
            this.highestScoringPlayerAndCard = getDiscardPile().getPlayerAndCardAtIndex(0);
        }
    }

    public Pair<HighLowPlayer, CardInterface> getHighestScoringPlayerAndCard() {
        return highestScoringPlayerAndCard;
    }
}
