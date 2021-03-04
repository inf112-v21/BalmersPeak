package inf112.balmerspeak.app.menu;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.balmerspeak.app.GUI;
import inf112.balmerspeak.app.cards.MovementCard;
import inf112.balmerspeak.app.cards.Rotation;
import inf112.balmerspeak.app.cards.RotationCard;
import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.lwjgl.system.CallbackI;

import static org.junit.Assert.*;

public class GameScreenTest {



    private static GameScreen g;
    private static Robot robot;

    @BeforeAll
    public static void setup(){
        //A robot will be placed at (0,0) in the gamescreen setup
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Robo Rally");
        cfg.setWindowedMode(1920, 1080);

        new Lwjgl3Application(new GUI() {
            @Override
            public void create() {
                changeScreen(g = new GameScreen());
                Gdx.app.exit();
            }
        }, cfg);
    }

    @Test
    public void playerCanNotMoveOutOfBounds(){
        setup();
        assertFalse(g.shouldMove(0,-1));
    }

    @Test
    public void playerCanMoveOneUp(){
        setup();
        assertTrue(g.shouldMove(0,1));
    }


    @Test
    public void playerMovesFromCard(){
        setup();
        MovementCard card = new MovementCard(1,1,"Movement 1");
        g.handleMoveCard(card);
        assertTrue(g.getRobot().getY() == 1);

    }

    @Test
    public void playerRotatesFromCard(){
        setup();
        RotationCard card = new RotationCard(1, Rotation.right, "Rotate right");
        g.handleRotation(card);
        assertTrue(g.getRobot().getDirection().equals(Direction.EAST));
    }

}