package inf112.balmerspeak.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.esotericsoftware.kryonet.Server;
import inf112.balmerspeak.app.cards.Deck;
import inf112.balmerspeak.app.cards.GeneralCard;
import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Move;
import inf112.balmerspeak.app.robot.Robot;
import org.lwjgl.system.CallbackI;

public class Main {

    private static Move move;
    private static Robot robot = new Robot(0,0, Direction.NORTH);
    private static Deck deck = new Deck();

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Robo Rally");
        cfg.setWindowedMode(1920, 1080);
        move = new Move(robot, deck);
        move.programRobot();
        System.out.println(robot.getHand());

        //new Lwjgl3Application(new GUI(), cfg);
    }
}