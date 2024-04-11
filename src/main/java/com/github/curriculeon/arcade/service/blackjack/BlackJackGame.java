package com.github.curriculeon.arcade.service.blackjack;

import com.github.curriculeon.arcade.lib.game.cardgame.utils.AbstractCardGame;
import com.github.curriculeon.arcade.lib.game.cardgame.utils.card.CardInterface;
import com.github.curriculeon.arcade.lib.profile.ProfileInterface;
import com.github.curriculeon.arcade.lib.profile.ProfileManager;
import com.github.curriculeon.arcade.service.highlow.HighLowPlayer;

import java.util.Collections;
import java.util.List;

public class BlackJackGame extends AbstractCardGame<BlackJackPlayer> {
    @Override
    public void run() {
        getDeck().shuffle();
        final BlackJackDealer dealer = new BlackJackDealer(getDeck());
        dealer.deal( 1, dealer);
        dealer.deal( 2, getPlayers());
        dealer.deal( 1, dealer);

        final List<CardInterface> hand = dealer.getHand();
        final CardInterface card = hand.get(0);
        hand.remove(card);
        getDiscardPile().add(dealer, card);
    }

    @Override
    public BlackJackPlayer createPlayer(ProfileInterface profile) {
        return new BlackJackPlayer(profile);
    }
}
