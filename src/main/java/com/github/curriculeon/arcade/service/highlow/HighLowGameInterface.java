package com.github.curriculeon.arcade.service.highlow;

import com.github.curriculeon.arcade.lib.game.cardgame.utils.CardGameInterface;
import com.github.curriculeon.arcade.lib.game.cardgame.utils.Deck;
import com.github.curriculeon.arcade.lib.game.cardgame.utils.card.CardInterface;
import com.github.curriculeon.arcade.lib.profile.Profile;
import com.github.curriculeon.arcade.lib.profile.ProfileInterface;
import com.github.curriculeon.arcade.lib.utils.Pair;

public interface HighLowGameInterface extends CardGameInterface<HighLowPlayer> {

    Pair<HighLowPlayer, CardInterface> getHighestScoringPlayerAndCard();
    void setHighestScoringPlayerAndCard(Pair<HighLowPlayer, CardInterface> playerAndCard);
    @Override
    default HighLowPlayer createPlayer(ProfileInterface profile) {
        return new HighLowPlayer(profile);
    }

    @Override
    default void run() {
        final HighLowPlayer dealer = new HighLowPlayer(new Profile("DEALER", Double.MAX_VALUE, null));
        getDiscardPile().add(dealer, getDeck().pop());
        final Deck deck = new Deck();
        deck.shuffle();
        getPlayers().forEach(player -> player.addCard(deck.pop()));
    }

    default CardInterface getCurrentFaceUpValue() {
        return getDiscardPile().getPlayerAndCardAtIndex(0).getValue();
    }

    default void evaluateCardAndPlayer(Pair<HighLowPlayer, CardInterface> ownerAndCard) {
        if (getHighestScoringPlayerAndCard() != null) {
            final int currentPlayerCardValue = getCurrentFaceUpValue().getValue();
            final int highestScoringPlayerCardValue = getHighestScoringPlayerAndCard().getValue().getValue();

            if (highestScoringPlayerCardValue < currentPlayerCardValue) {
                setHighestScoringPlayerAndCard(new Pair<>(ownerAndCard.getKey(), getCurrentFaceUpValue()));
            }
        } else {
            setHighestScoringPlayerAndCard(getDiscardPile().getPlayerAndCardAtIndex(0));
        }
    }
}
