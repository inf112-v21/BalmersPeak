package inf112.balmerspeak.app.robot;

import inf112.balmerspeak.app.cards.Deck;
import inf112.balmerspeak.app.cards.ProgramCard;

public class Move {

    private Robot robot;
    private Deck deck;

    public Move(Robot robot, Deck deck) {
        this.robot = robot;
        this.deck = deck;
    }

    public void programRobot() {
        for (ProgramCard cards : deck.getCards(5))
        robot.giveCard(cards);


    }

}
