package com.blackjackquiz.app.deck;

import com.blackjackquiz.app.deck.Deck.Card;

public class Field
{
    private Field(Card dealerCard, Card playerCardOne, Card playerCardTwo)
    {
        this.dealerCard = dealerCard;
        this.playerCardOne = playerCardOne;
        this.playerCardTwo = playerCardTwo;
    }

    public static Field newField()
    {
        return new Field(Deck.getRandomCard(), Deck.getRandomCard(), Deck.getRandomCard());
    }

    public final Card dealerCard;
    public final Card playerCardOne;
    public final Card playerCardTwo;
}
