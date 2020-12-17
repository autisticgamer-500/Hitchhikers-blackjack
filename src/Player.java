import java.io.Serializable;

public class Player implements Serializable
{

    private int token;
    private int bet;
    private String name;
    private Hand hand;

    public Player() {
        token = 25;
        hand = new Hand();

    }

    public int getToken() {
        return token;
    }

    public void bust() {
        token -= bet;
        bet = 0;
    }

    public void win() {
        token += bet;
        bet = 0;
    }

    public void loss() {
        token -= bet;
        bet = 0;
    }

    public void removeFromGame() {
        token = -1;
    }

    public void resetToken() {
        token = 0;
    }

    public void blackjack() {
        token += bet * 1.5;
        bet = 0;
    }

    public void push() {
        bet = 0;
    }

    public void setBet(int newBet) {
        bet = newBet;
    }

    public void setName(String name1){
        name = name1;
    }

    public String getName() {
        return name;
    }

    public int getTotal() {
        return hand.calculateTotal();
    }

    public int getBet(){
        return this.bet;
    }

    public void addCard(Card card) {
        hand.addCard(card);

    }

    public String getHandString() {
        String str = "Cards:" + hand.toString();

        return str;
    }

    public void clearHand() {
        hand.clearHand();
    }

}