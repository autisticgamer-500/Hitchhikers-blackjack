import java.io.Serializable;

public class Card implements Serializable {

    /*Making data "private" is information hiding, so that it cannot be access by
     *someone else with code outside this class.*/
    private final char suit;
    private final int value;

    public Card(char newSuit, int newValue) throws InvalidCardValueException, InvalidCardSuitException {
        if (newValue < 1 || newValue > 21) {
            throw new InvalidCardValueException(newValue);
        } else {

            this.value = newValue;
        }
        if (newSuit != 'H' && newSuit != 'S' && newSuit != 'R' && newSuit != 'C' && newSuit != 'W' && newSuit != 'E' && newSuit != 'D' && newSuit != 'F') {
            throw new InvalidCardSuitException(newSuit);
        } else {
            this.suit = newSuit;
        }

    }


    public String toString() {

        return getSuitName() + " " + this.value;

    }

    public String getSuitName() {

        String suit;

        if (this.suit == 'H') {

            suit = "Hearts";

        } else if (this.suit == 'S') {

            suit = "Spades";

        } else if (this.suit == 'C') {

            suit = "Clubs";

        } else if (this.suit == 'R') {

            suit = "Rubies";

        } else if (this.suit == 'W') {

            suit = "Whales";

        } else if (this.suit == 'E') {

            suit = "Eagles";

        } else if (this.suit == 'D') {

            suit = "Dolphins";

        } else if (this.suit == 'F') {

            suit = "Falcons";

        } else {

            suit = "Unknown";
        }

        return suit;

    }

    public char getSuitDesignator() {

        return suit;

    }

    public String getValueName() {

        String name = "Unknown";

        if (this.value == 1) { // added 8 extra cards
            name = "Ace";
        } else if (this.value == 2) {
            name = "Two";
        } else if (this.value == 3) {
            name = "Three";
        } else if (this.value == 4) {
            name = "Four";
        } else if (this.value == 5) {
            name = "Five";
        } else if (this.value == 6) {
            name = "Six";
        } else if (this.value == 7) {
            name = "Seven";
        } else if (this.value == 8) {
            name = "Eight";
        } else if (this.value == 9) {

            name = "Nine";
        } else if (this.value == 10) {

            name = "Ten";
        } else if (this.value == 11) {

            name = "Eleven";
        } else if (this.value == 12) {

            name = "Twelve";
        } else if (this.value == 13) {

            name = "Thirteen";

        } else if (this.value == 14) {

            name = "Fourteen";

        } else if (this.value == 15) {

            name = "Fifteen";

        } else if (this.value == 16) {

            name = "Jack";

        } else if (this.value == 17) {

            name = "Cavalier";

        } else if (this.value == 18) {

            name = "Bishop";

        } else if (this.value == 19) {

            name = "Rook";

        } else if (this.value == 20) {

            name = "Queen";

        } else if (this.value == 21) {

            name = "King";

        }
        return name;

    }// Added extra cards

    /*This is encapsulation, it's providing access to the hidden information by
     *putting it together in one unit with a public method. So, anyone who wants
     *our data will have to use a setter/getter.*/
    public int getValue() {

        return this.value;
    }


} //End class