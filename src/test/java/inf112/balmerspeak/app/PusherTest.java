package inf112.balmerspeak.app;

import inf112.balmerspeak.app.board.Pusher;
import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class PusherTest {
    private ArrayList<Integer> roundList;
    private Robot player = new Robot(5, 5, Direction.NORTH);
    private Pusher pusherNorth =new Pusher(roundList, Direction.NORTH);
    private Pusher pusherEast =new Pusher(roundList, Direction.EAST);
    private Pusher pusherSouth =new Pusher(roundList, Direction.SOUTH);
    private Pusher pusherWest =new Pusher(roundList, Direction.WEST);

    @Test
    public void testPushNorth(){
        pusherNorth.pushRobot(player);
        assertTrue(player.getX() == 5 && player.getY() == 6);
    }

    @Test
    public void testPushEast(){
        pusherEast.pushRobot(player);
        assertTrue(player.getX() == 6 && player.getY() == 5);
    }

    @Test
    public void testPushSouth(){
        pusherSouth.pushRobot(player);
        assertTrue(player.getX() == 5 && player.getY() == 4);
    }

    @Test
    public void testPushWest(){
        pusherWest.pushRobot(player);
        assertTrue(player.getX() == 4 && player.getY() == 5);
    }

}
