package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Game
{

    private final int HAND_SIZE = 5;
    private int again = 1;

    // instantiate Deck and Player
    Scanner scan = new Scanner(System.in);
    Deck deck = new Deck();
    Player Black = new Player();
    Player White = new Player();
    Card[] hand;


    public void play()
    {
        String blackResult = "";
        String whiteResult = "";
        while (again == 1)
        {

            deck.fillDeck();
            deck.shuffle();
            hand = Black.draw(deck);
            Arrays.sort(hand);
            this.checkHand("Black");
            blackResult = this.evaluate();


            hand = White.nextdraw(deck);
            Arrays.sort(hand);
            this.checkHand("White");
            whiteResult = this.evaluate();

            this.rankPlayers(blackResult,whiteResult);

            System.out.println();
            this.again();
        }
        System.out.println("Thanks for playing! =]");
    }

    public void rankPlayers(String bResult, String wResult)
    {
        String bR = bResult.substring(0,bResult.length()-2);
        String wR = wResult.substring(0,wResult.length()-2);

        if (bR == "HighCard")
        {
            if (wR == "HighCard")
            {
                Integer num1 =Integer.parseInt(bResult.substring(bResult.length() - 1));
                Integer num2 = Integer.parseInt(wResult.substring(wResult.length()-1));
                if (num1 > num2)
                {
                    System.out.println("Black wins - with high card " + num1);
                }
                else if (num1 < num2)
                {
                    System.out.println("White wins - with high card " + num2);
                }
            }
            else
            {
                System.out.println("White wins. - with " + wResult);
            }
        }
        else if (bResult == "Pair")
        {
            if (wR == "HighCard")
            {
                System.out.println("Black wins : with a Pair");
            }
            else if(wResult == "Pair")
            {

            }
            else
            {
                System.out.println("White wins - with " + wResult);
            }

        }
        else if (bResult == "TwoPairs")
        {
            if ((wR == "HighCard")| (wResult == "Pair"))
            {
                System.out.println("Black wins : with a Two Pairs");
            }

            else if (wResult == "TwoPairs")
            {

            }
            else
            {
                System.out.println("White wins - with " + wResult);
            }
        }
        else if (bResult == "ThreeOfAKind")
        {
            if ((wR == "HighCard") | (wResult == "Pair") | (wResult == "TwoPairs"))
            {
                System.out.println("Black wins : with a Three of a kind");
            }

            else if (wResult == "ThreeOfAKind")
            {

            }
            else
            {
                System.out.println("White wins - with " + wResult);
            }
        }
        else if (bResult == "Straight")
        {
            if ((wR == "HighCard") | (wResult == "Pair") | (wResult == "TwoPairs") | (wResult == "ThreeOfAKind"))
            {
                System.out.println("Black wins : with a Straight");
            }

            else if (wResult == "Straight")
            {

            }
            else
            {
                System.out.println("White wins - with " + wResult);
            }
        }
        else if (bResult == "Flush")
        {
            if ((wR == "HighCard") | (wResult == "Pair") | (wResult == "TwoPairs")| (wResult == "ThreeOfAKind") | (wResult == "Straight"))
            {
                System.out.println("Black wins : with a Flush");
            }

            else if (wResult == "Flush")
            {

            }
            else
            {
                System.out.println("White wins - with " + wResult);
            }
        }

        else if (bResult == "FullHouse")
        {
            if ((wR == "HighCard") | (wResult == "Pair") | (wResult == "TwoPairs")| (wResult == "ThreeOfAKind") | (wResult == "Straight") | (wResult == "Flush"))
            {
                System.out.println("Black wins : with a Full House");
            }

            else if (wResult == "FullHouse")
            {

            }
            else
            {
                System.out.println("White wins - with " + wResult);
            }
        }

        else if (bResult == "FourOfAKind")
        {
            if ((wR == "HighCard") | (wResult == "Pair") | (wResult == "TwoPairs")| (wResult == "ThreeOfAKind") | (wResult == "Straight") | (wResult == "Flush")| (wResult == "FullHouse"))
            {
                System.out.println("Black wins : with Four of a kind");
            }

            else if (wResult == "FourOfAKind")
            {

            }
            else
            {
                System.out.println("White wins - with " + wResult);
            }
        }

        else
        {
            if ((wR == "HighCard") | (wResult == "Pair") | (wResult == "TwoPairs")| (wResult == "ThreeOfAKind") | (wResult == "Straight") | (wResult == "Flush")| (wResult == "FullHouse")| (wResult == "FourOfAKind"))
            {
                System.out.println("Black wins : with a Straight Flush");
            }

            else if (wResult == "StraightFlush")
            {

            }

        }

    }

    public void checkHand(String player)
    {
        System.out.print(player + ": ");
        for (int handCounter = 0; handCounter < HAND_SIZE; handCounter++)
        {
            this.display(hand[handCounter] );
        }
    }


    public String evaluate()
    {
        String result = "";
        System.out.println();
        if (this.straightFlush() == 1)
        {
            result = "StraightFlush";
        }
        else if (this.fourOfaKind() == 1)
        {
            result = "FourOfAKind";
        }
        else if (this.fullHouse() == 1)
        {
            result = "FullHouse";
        }
        else if (this.flush() == 1)
        {
            result = "Flush";
        }
        else if (this.straight() == 1)
        {
            result = "Straight";
        }
        else if (this.triple() == 1)
        {
            result = "ThreeOfAKind";
        }
        else if (this.twoPairs() == 1)
        {
            result = "TwoPairs";
        }
        else if (this.pair() == 1)
        {
            result = "Pair";
        }
        else
        {
            int highCard = this.highCard();
            result = "HighCard: " + highCard;
        }
        return result;
    }


    public int straightFlush()
    {
        for (int counter = 1; counter < 5; counter++)
        {
            if (hand[0].suit != hand[counter].suit)
            {
                return 0;
            }
        }
        for (int counter2 = 1; counter2 < 5; counter2++)
        {
            if (hand[counter2 - 1].rank != (hand[counter2].rank - 1))
            {
                return 0;
            }

        }
        return 1;

    }


    public int fourOfaKind()
    {
        if (hand[0].rank != hand[3].rank && hand[1].rank != hand[4].rank)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }


    public int fullHouse()
    {
        int comparison = 0;
        for (int counter = 1; counter < 5; counter++)
        {
            if (hand[counter - 1].rank == hand[counter].rank)
            {
                comparison++;
            }
        }
        if (comparison == 3)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }


    public int flush()
    {
        for (int counter = 1; counter < 5; counter++)
        {
            if (hand[0].suit != hand[counter].suit)
            {
                return 0;
            }
        }
        return 1;
    }


    public int straight()
    {
        for (int counter2 = 1; counter2 < 5; counter2++)
        {
            if (hand[counter2 - 1].rank != (hand[counter2].rank - 1))
            {
                return 0;
            }

        }
        return 1;
    }


    public int triple()
    {
        if (hand[0].rank == hand[2].rank || hand[2].rank == hand[4].rank)
        {
            return 1;
        }
        return 0;
    }


    public int twoPairs()
    {
        int check = 0;
        for(int counter = 1; counter < 5; counter++)
        {
            if (hand[counter - 1].rank == hand[counter].rank)
            {
                check++;
            }
        }
        if (check == 2)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }


    public int pair()
    {
        int check = 0;
        for(int counter = 1; counter < 5; counter++)
        {
            if (hand[counter - 1].rank == hand[counter].rank)
            {
                check++;
            }
        }
        if (check == 1)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }


    public int highCard()
    {
        int highCard = 0;
        for (int counter = 0; counter < 5; counter++)
        {
            if (hand[counter].rank > highCard)
            {
                highCard = hand[counter].rank;
            }
        }
        return highCard;
    }


    public void again()
    {
        System.out.print("Play again? (1 for yes, 0 for no)");
        again = scan.nextInt();
    }


    public void display(Card card)
    {

        if (card.rank == 1)
        {
            System.out.print("A");
        }
        if (card.rank == 2)
        {

            System.out.print("2");
        }
        if (card.rank == 3)
        {
            System.out.print("3");
        }
        if (card.rank == 4)
        {
            System.out.print("4");
        }
        if (card.rank == 5)
        {
            System.out.print("5");
        }
        if (card.rank == 6)
        {
            System.out.print("6");
        }
        if (card.rank == 7)
        {
            System.out.print("7");
        }
        if (card.rank == 8)
        {
            System.out.print("8");
        }
        if (card.rank == 9)
        {
            System.out.print("9");
        }
        if (card.rank == 10)
        {
            System.out.print("10");
        }
        if (card.rank == 11)
        {
            System.out.print("J");
        }
        if (card.rank == 12)
        {
            System.out.print("Q");
        }
        if (card.rank == 13)
        {
            System.out.print("K");
        }
        if (card.suit == 1)
        {
            System.out.print("S ");
        }
        if (card.suit == 2)
        {
            System.out.print("H ");
        }
        if (card.suit == 3)
        {
            System.out.print("D ");
        }
        if (card.suit == 4)
        {
            System.out.print("C ");
        }

    }
}