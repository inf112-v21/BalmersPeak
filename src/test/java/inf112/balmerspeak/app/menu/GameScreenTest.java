package inf112.balmerspeak.app.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.balmerspeak.app.GUI;
import inf112.balmerspeak.app.Player;
import inf112.balmerspeak.app.cards.*;
import inf112.balmerspeak.app.robot.Direction;
import org.javatuples.Pair;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameScreenTest {



    private static GameScreen g;
    private static Player player;
    private static Player player1;
    private static ArrayList<Player> players = new ArrayList<>();

    @BeforeAll
    public static void setup(){
        Deck deck = new Deck();
        ArrayList<ProgramCard> hand = deck.getCards(9);

        player = new Player(new Pair<>(0,0), "test", "localhost", 1);
        player.setHand(hand);

        player1 = new Player(new Pair<>(1,0), "test1", "localhost", 2);
        player1.setHand(hand);

        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Robo Rally");
        cfg.setWindowedMode(1920, 1080);


        new Lwjgl3Application(new GUI() {
            @Override
            public void create() {
                g = new GameScreen();
                g.setMyPlayer(player);
                g.getBoard().placeRobot(player);
                g.getBoard().placeRobot(player1);
                Gdx.app.postRunnable(() -> changeScreen(g)); //TODO: fix this null
                Gdx.app.exit();
            }
        }, cfg);

        players.add(player1);

    }

    @Test
    public void playerMovesFromCard(){
        setup();
        MovementCard card = new MovementCard(1,1,"Movement 1");
        g.handleMoveCard(card, g.getMyPlayer());
        assertTrue(g.getMyPlayer().getRobot().getX() == 1);

    }

    @Test
    public void playerRotatesFromCard(){
        setup();
        RotationCard card = new RotationCard(1, Rotation.right, "Rotate right");
        g.handleRotation(card, g.getMyPlayer());
        assertTrue(g.getMyPlayer().getRobot().getDirection().equals(Direction.SOUTH));
    }

    @Test
    public void collisionTestPositiveX(){
        setup();
        System.out.println(players.get(0).getRobot().getX() + " " + g.getMyPlayer().getRobot().getX());
        g.getBoard().move(g.getMyPlayer(), players, 1,0);
        System.out.println(players.get(0).getRobot().getX() + " " + g.getMyPlayer().getRobot().getX());
        assertTrue(players.get(0).getRobot().getX() == 2 && g.getMyPlayer().getRobot().getX() == 1);
    }

    @Test
    public void collisionTestPositiveY(){
        setup();
        g.getBoard().move(players.get(0), players, -1, 1);
        g.getBoard().move(g.getMyPlayer(), players, 0, 1);
        assertTrue(players.get(0).getRobot().getY() == 2 && g.getMyPlayer().getRobot().getY() == 1);
    }
    
}