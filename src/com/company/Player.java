package com.company;

public class Player
{

    public Card[] draw(Deck deck)
    {
        Card[] hand = deck.deal();
        return hand;
    }

    public Card[] nextdraw(Deck deck)
    {
        Card[] hand = deck.nextdeal();
        return hand;
    }


}