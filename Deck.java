import java.io.Serializable;
import java.util.Random;

public class Deck extends Exception implements Serializable {

    private int nextCardIndex;


    Card[] deck = new Card[1680];

    public Deck() {

        int count = 0;
        try {
            for (int j = 1; j<=10; j++){ // creates a deck 10 times
                for (int i = 1; i <= 21; i++) {
                    deck[count++] = new Card('H', i);
                }
                for (int i = 1; i <= 21; i++) {
                    deck[count++] = new Card('S', i);
                }
                for (int i = 1; i <= 21; i++) {
                    deck[count++] = new Card('C', i);
                }
                for (int i = 1; i <= 21; i++) {
                    deck[count++] = new Card('D', i);
                }
                for (int i = 1; i <= 21; i++) {
                    deck[count++] = new Card('R', i);
                }
                for (int i = 1; i <= 21; i++) {
                    deck[count++] = new Card('E', i);
                }
                for (int i = 1; i <= 21; i++) {
                    deck[count++] = new Card('W', i);
                }
                for (int i = 1; i <= 21; i++) {
                    deck[count++] = new Card('F', i);
                }
            }//added 9 extra decks
        } catch (InvalidCardValueException | InvalidCardSuitException ignored) {

        }
        nextCardIndex = 0;// added 4 extra decks and modified code required
    }

    private void isIndexGood(int index) throws InvalidDeckPositionException {
        if (index < 0 || index > 1679) {
            throw new InvalidDeckPositionException(index);
        }
    }

    public String toString() {

        StringBuilder str = new StringBuilder();

        for (Card card : deck) {
            str.append(card.toString()).append(" ");
        }
        return str.toString();
    }


    private void swapCards(int index1, int index2) throws InvalidDeckPositionException {
        Card hold;

        isIndexGood(index1);
        isIndexGood(index2);
        hold = deck[index1];
        deck[index1] = deck[index2];
        deck[index2] = hold;
    }

    public void shuffle() throws InvalidDeckPositionException {
        Random rn = new Random();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < deck.length; j++) {
                swapCards(i, rn.nextInt(1680));
            }
        }
        nextCardIndex = 0;
    }


    public Card nextCard() {

        if (nextCardIndex < 0 || nextCardIndex > 1680) {
            System.out.println("Future exception goes here");
        }
        return deck[nextCardIndex++];
    }

} //End class