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

    public static enum Rank
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
        Jack(10),
        Queen(10),
        King(10),
        Ace(11);

        Rank(int value)
        {
            this.value = value;
        }

        public final int value;
    }

    public static class Card
    {
        Card(Suite suite, Rank rank)
        {
            this.suite = suite;
            this.rank = rank;
        }

        public boolean isAce()
        {
            return rank == Rank.Ace;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }

            if (!(o instanceof Card))
            {
                return false;
            }

            Card card = (Card) o;
            return suite == card.suite && rank == card.rank;
        }

        @Override
        public int hashCode()
        {
            int result = suite.hashCode();
            result = 31 * result + rank.hashCode();
            return result;
        }

        public final Suite suite;
        public final Rank  rank;
    }

    private static final Random random          = new Random(System.currentTimeMillis());
    private static final int    NUM_CARD_VALUES = Rank.values().length;
    private static final int    NUM_SUITES      = Suite.values().length;

    private Deck()
    {
    }

    static Card getRandomCard()
    {
        Suite suite = Suite.values()[random.nextInt(NUM_SUITES)];
        Rank value = Rank.values()[random.nextInt(NUM_CARD_VALUES)];
        return new Card(suite, value);
    }
}
