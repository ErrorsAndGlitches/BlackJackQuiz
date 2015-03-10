package com.blackjackquiz.app.deck;

import java.util.Random;

public class Deck
{
    public static enum Suite
    {
        Hearts,
        Diamonds,
        Spaces,
        Clubs
    }

    public static enum CardValue
    {
        Two(2),
        Three(3),
        Four(4),
        Five(5),
        Six(6),
        Seven(7),
        Eight(8),
        Nine(9),
        Ten(10),
        Jack(11),
        Queen(12),
        King(13),
        Ace(14);

        CardValue(int value)
        {
            this.value = value;
        }

        public final int value;
    }

    public static class Card
    {
        Card(Suite suite, CardValue value)
        {
            this.suite = suite;
            this.value = value;
        }

        public final Suite     suite;
        public final CardValue value;
    }

    private static final Random random          = new Random(System.currentTimeMillis());
    private static final int    NUM_CARD_VALUES = CardValue.values().length;
    private static final int    NUM_SUITES      = Suite.values().length;

    private Deck() {}

    public static Card getRandomCard()
    {
        Suite suite = Suite.values()[random.nextInt(NUM_SUITES)];
        CardValue value = CardValue.values()[random.nextInt(NUM_CARD_VALUES)];
        return new Card(suite, value);
    }
}
