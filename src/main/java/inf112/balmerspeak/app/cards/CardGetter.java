package inf112.balmerspeak.app.cards;

import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Move;
import inf112.balmerspeak.app.robot.Robot;

import java.util.ArrayList;

public class CardGetter {

    private static Move move;
    private static Robot robot = new Robot(0,0, Direction.NORTH);
    private static Deck deck = new Deck();

    public ArrayList<ProgramCard> getCardList() {
        move = new Move(robot, deck);
        move.programRobot();
        System.out.println(robot.getHand());
        return robot.getHand();
    }
}
