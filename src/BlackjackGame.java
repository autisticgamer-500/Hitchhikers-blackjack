import java.util.Scanner;
public class BlackjackGame {

    private Scanner ki = new Scanner(System.in);
    private int users;
    private Player[] players;
    private Deck deck;
    private Dealer dealer = new Dealer();

    public void initializeGame(){
        String names;
        System.out.println("Welcome to Blackjack Casino!");
        System.out.println("");
        System.out.println("  RULES: ");
        System.out.println("	- Player and computer are dealt 2 card, one face-up and one face-down for dealer and both up for player.");
        System.out.println("	- The player goes first. They can allowed to either hit (to get another card) or stand (accepting their hand.)");
        System.out.println("	- The goal is to have a higher card total than the dealer without going over 21.");
        System.out.println("	- Players win their bet if they beat the dealer.");
        System.out.println("	- Players win 3-2 ratio of their bet if they get “Blackjack” which is 21.");
        System.out.println("	- If the player's total equals the dealer total, it is a “Push”, the hand ends, and you get your wager back.");
        System.out.println("");

        do {
            System.out.print("Enter 1 to continue. ");
            users = ki.nextInt();
        } while (users > 1 || users < 0);

        players = new Player[users];
        deck = new Deck();

        for (int i = 0; i < users; i++) {
            System.out.print("What the player's name? ");
            names = ki.next();
            players[i] = new Player();
            players[i].setName(names);
        }
    }

    public void shuffle() throws InvalidDeckPositionException, InvalidCardSuitException, InvalidCardValueException {
        deck.shuffle();

    }

    public void getBets(){
        int betValue;

        for (int i =0; i < users; i++) {
            if (players[i].getToken() > 0) {
                do {
                    System.out.print("How much do you want to bet " + players[i].getName()  + ("? (1-25, You currently have " + players[i].getToken() + " tokens)"));
                    betValue = ki.nextInt();
                    players[i].setBet(betValue);
                } while (!( betValue > 0 && betValue <= 25));
                System.out.println("");
            }

        }

    }

    public void dealCards(){
        for (int j = 0; j < 2; j++) {
            for (int i =0; i < users; i++) {
                if(players[i].getToken() > 0)
                {
                    players[i].addCard(deck.nextCard());
                }
            }

            dealer.addCard(deck.nextCard());
        }
    }

    public void checkBlackjack(){
        if (dealer.isBlackjack() ) {
            System.out.println("Dealer has BlackJack!");
            for (int i =0; i < users; i++) {
                if (players[i].getTotal() == 21 ) {
                    System.out.println(players[i].getName() + " pushes");
                    players[i].push();
                } else {
                    System.out.println(players[i].getName() + " loses");
                    players[i].bust();
                }
            }
        } else {
            if (dealer.peek() ) {
                System.out.println("Dealer peeks and does not have a BlackJack");
            }

            for (int i =0; i < users; i++) {
                if (players[i].getTotal() == 21 ) {
                    System.out.println(players[i].getName() + " has blackjack!");
                    players[i].blackjack();
                }
            }
        }
    }

    public void hitOrStand() {
        String command;
        char c;
        for (int i = 0; i < users; i++) {
            if ( players[i].getBet() > 0 ) {
                System.out.println();
                System.out.println(players[i].getName() + " has " + players[i].getHandString());

                do {
                    do {
                        System.out.print(players[i].getName() + " (H)it or (S)tand? ");
                        command = ki.next();
                        c = command.toUpperCase().charAt(0);
                    } while ( ! ( c == 'H' || c == 'S' ) );
                    if ( c == 'H' ) {
                        players[i].addCard(deck.nextCard());
                        System.out.println(players[i].getName() + " has " + players[i].getHandString());
                    }
                } while (c != 'S' && players[i].getTotal() <= 21 );
            }
        }
    }

    public void dealerPlays() {
        boolean isSomePlayerStillInTheGame = false;
        for (int i =0; i < users && isSomePlayerStillInTheGame == false; i++){
            if (players[i].getBet() > 0 && players[i].getTotal() <= 21 ) {
                isSomePlayerStillInTheGame = true;
            }
        }
        if (isSomePlayerStillInTheGame) {
            dealer.dealerPlay(deck);
        }
    }

    public void settleBets() {
        System.out.println();

        for (int i = 0; i < users; i++) {
            if (players[i].getBet() > 0 ) {
                if( players[i].getTotal() > 21 ) {
                    System.out.println(players[i].getName() + " has busted");
                    players[i].bust();
                } else if ( players[i].getTotal() == dealer.calculateTotal() ) {
                    System.out.println(players[i].getName() + " has pushed");
                    players[i].push();
                } else if ( players[i].getTotal() < dealer.calculateTotal() && dealer.calculateTotal() <= 21 ) {
                    System.out.println(players[i].getName() + " has lost");
                    players[i].loss();
                } else if (players[i].getTotal() == 21) {
                    System.out.println(players[i].getName() + " has won with blackjack!");
                    players[i].blackjack();
                } else {
                    System.out.println(players[i].getName() + " has won");
                    players[i].win();

                }
            }
        }

    }

    public void printStatus() {
        for (int i = 0; i < users; i++) {
            if(players[i].getToken() > 0)
            {
                System.out.println(players[i].getName() + " has " + players[i].getHandString());
            }
        }
        System.out.println("Dealer has " + dealer.getHandString(true, true));
    }

    public void printMoney() {
        for (int i = 0; i < users; i++) {
            if(players[i].getToken() > 0)
            {
                System.out.println(players[i].getName() + " has " + players[i].getToken());
            }
            if(players[i].getToken() == 0)
            {
                System.out.println(players[i].getName() + " has " + players[i].getToken() + " and is out of the game.");
                players[i].removeFromGame();
            }
        }
    }

    public void clearHands() {
        for (int i = 0; i < users; i++) {
            players[i].clearHand();
        }
        dealer.clearHand();

    }

    public boolean playAgain() {
        String command;
        char c;
        Boolean playState = true;
        if(forceEnd()) {
            playState = false;
        }
        else {
            do {
                System.out.println("");
                System.out.print("Do you want to play again (Y)es or (N)o? ");
                command = ki.next();
                c = command.toUpperCase().charAt(0);
            } while ( ! ( c == 'Y' || c == 'N' ) );
            if(c == 'N')
            {
                playState = false;
                    System.out.println("");
                    System.out.println("We are sad to see you go.");
                    System.out.println("Thank you for playing!");
            }
        }
        return playState;
    }

    public boolean forceEnd() {
        boolean end = false;
        int endCount = 0;

        for (int i = 0; i < users; i++) {
            if(players[i].getToken() == -1)
            {
                endCount++;
            }
        }
        if(endCount == users)
        {
            end = true;
        }
        if(end)
        {
            System.out.println("");
            System.out.println("You have lost and the game ends.");
            System.out.println("Thank you for playing!");
        }

        return end;
    }


}