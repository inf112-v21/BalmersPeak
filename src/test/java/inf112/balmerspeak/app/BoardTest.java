package inf112.balmerspeak.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.balmerspeak.app.board.Board;
import inf112.balmerspeak.app.cards.Deck;
import inf112.balmerspeak.app.cards.ProgramCard;
import inf112.balmerspeak.app.menu.GameScreen;
import org.javatuples.Pair;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BoardTest {

    Player player;
    GameScreen g;

    @BeforeAll
    public void setup(){
        Deck deck = new Deck();
        ArrayList<ProgramCard> hand = deck.getCards(9);

        player = new Player(new Pair<>(0,0), "test", "localhost", 1);
        player.setHand(hand);

        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Robo Rally");
        cfg.setWindowedMode(1920, 1080);


        new Lwjgl3Application(new GUI() {
            @Override
            public void create() {
                g = new GameScreen();
                g.setMyPlayer(player);
                g.getBoard().placeRobot(player);
                Gdx.app.postRunnable(() -> changeScreen(g)); //TODO: fix this null
                Gdx.app.exit();
            }
        }, cfg);

    }

    @Test
    public void canPlaceRobot(){
        setup();
        g.getBoard().placeRobot(player);
        assertTrue(g.getBoard().hasRobot(0,0));
    }

    @Test
    public void canGetFlag(){
        setup();
//        assertTrue(g.getBoard().getFlag(9,1) != null);
//        assertTrue(g.getBoard().getFlag(15,5) != null);
//        assertTrue(g.getBoard().getFlag(6,10) != null);

    }

    @Test
    public void canGetHole(){
        setup();
        assertTrue(g.getBoard().getHole(9,2) != null);
    }

    @Test
    public void canGetLaser(){
        setup();
        assertTrue(g.getBoard().getLaser(5,1) != null);
    }

    @Test
    public void canGetWalls(){
        setup();
        assertTrue(g.getBoard().getWalls(2,0) != null);
    }

    @Test
    public void canGetWrench(){
        setup();
        assertTrue(g.getBoard().getWrench(9,6) != null);
    }

    @Test
    public void canGetConveyor(){
        setup();
        assertTrue(g.getBoard().getConveyor(5,0) != null);
    }
}
