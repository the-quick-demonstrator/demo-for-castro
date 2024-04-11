package com.github.curriculeon.arcade.service.blackjack;

import com.github.curriculeon.arcade.lib.game.cardgame.utils.Deck;
import com.github.curriculeon.arcade.lib.profile.Profile;

import java.util.Arrays;
import java.util.List;

public class BlackJackDealer extends BlackJackPlayer {
    private final Deck deck;

    public BlackJackDealer(Deck deck) {
        super(new Profile("DEALER", Double.MAX_VALUE, null));
        this.deck = deck;
    }

    public void deal(int numberOfCards, BlackJackPlayer... players) {
        deal(numberOfCards, Arrays.asList(players));
    }

    public void deal(int numberOfCards, List<BlackJackPlayer> players) {
        for (int i = 0; i < numberOfCards; i++) {
            players.forEach((player) -> player.addCard(deck.pop()));
        }
    }
}