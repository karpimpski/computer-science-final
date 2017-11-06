import javax.swing.JOptionPane;
import java.util.concurrent.ThreadLocalRandom;

public class Game
{
    public static void main(String[] args)
    {
        //declare variables
        String[] deck = generateDeck();
        int[] scores = {0,0};
        String[] twoCards;

        //shuffle deck
        deck = shuffle(deck);

        //pick 2 cards from desk
        twoCards = pickCards(deck, 2);
        deck = removeCards(deck, 2);
    }

    /**
     * Returns array of strings to model a standard 52-deck of cards
     */
    public static String[] generateDeck()
    {
        //declare variables
        String[] values = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
        String[] suits = {"D","C","H","S"};
        String[] deck = new String[52];
        String card;
        int index;

        //generate deck
        for(int i = 0; i < values.length; i++)
        {
            for(int j = 0; j < suits.length; j++)
            {
                //determine index of current card
                index = (i * 4) + j;

                //create and append card
                card = values[i] + suits[j];
                deck[index] = card;
            }
        }

        return deck;
    }

    /**
     * Accepts a deck of cards
     * Returns a shuffled version
     * Uses Fisher-Yates shuffle algorithm
     */
    public static String[] shuffle(String[] deck)
    {
        //declare variables
        int randomIndex;
        String randomCard;
        String currentCard;

        for(int i = 0; i < deck.length; i++)
        {
            //generate randomIndex and assign card variables
            randomIndex = ThreadLocalRandom.current().nextInt(0, deck.length);
            randomCard = deck[randomIndex];
            currentCard = deck[i];

            //switch cards
            deck[randomIndex] = currentCard;
            deck[i] = randomCard;
        }

        return deck;
    }

    /**
     * Accepts a deck of cards and a number to determine how many cards to return
     * Returns specified number of cards from beginning of deck
     */
    public static String[] pickCards(String[] deck, int numberOfCards)
    {
        //declare variables
        String[] result = new String[numberOfCards];

        //generate result
        for(int i = 0; i < result.length; i++)
        {
            result[i] = deck[i];
        }

        return result;
    }

    /**
     * Accepts a deck of cards and a number to determine how many cards to remove
     * Deletes specified number of cards from beginning of deck, moves the rest of the cards forward,
     * and leaves specified number of empty Strings at end of the array
     * Returns modified deck
     */
    public static String[] removeCards(String[] deck, int numberOfCards)
    {
        //generate modified deck
        for(int i = 0; i < deck.length; i++)
        {
            //"push forward" cards in deck
            if(i < deck.length - numberOfCards)
            {
                deck[i] = deck[i + numberOfCards];
            }
            //empty the last numberOfCards values
            else
            {
                deck[i] = "";
            }
        }

        return deck;
    }

    /**
     * Accepts a card (such as "2H" for 2 of Hearts)
     * Returns card's value as an int
     */
    public static int determineValue(String card)
    {
        //declare variables
        final char firstLetter = Character.toUpperCase(card.charAt(0));
        String aceInput = "";
        int aceValue;
        int result;
        
        //handle digits
        if(!(Character.isLetter(firstLetter)))
        {
            //special case for 10
            if(card.substring(0,2).equals("10"))
            {
                result = 10;
            }
            else
            {
                result = Integer.parseInt(Character.toString(firstLetter));
            }
        }
        //handle ace
        else if(Character.toString(firstLetter).equalsIgnoreCase("A"))
        {
            while(!aceInput.equalsIgnoreCase("1") && !aceInput.equalsIgnoreCase("11"))
            {
                aceInput = JOptionPane.showInputDialog("An ace has been drawn. 1 or 11?");
            }
            result = Integer.parseInt(aceInput);
        }
        //handle royalty
        else
        {
            result = 10;
        }

        return result;
    }
}