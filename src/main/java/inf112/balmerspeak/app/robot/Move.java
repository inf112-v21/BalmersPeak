package inf112.balmerspeak.app.robot;

import inf112.balmerspeak.app.cards.Deck;

public class Move {

    private Robot robot;
    private Deck deck;

    public Move(Robot robot, Deck deck) {
        this.robot = robot;
        this.deck = deck;
    }

    public void programRobot() {

        robot.giveCard(deck.getCards(5));


    }

}
