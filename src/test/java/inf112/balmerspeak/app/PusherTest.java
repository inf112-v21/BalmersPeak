package inf112.balmerspeak.app;

import inf112.balmerspeak.app.board.Pusher;
import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class PusherTest {
    private ArrayList<Integer> roundList = new ArrayList<>();
    private Robot player = new Robot(5, 5, Direction.NORTH);
    private Pusher pusherNorth =new Pusher(roundList, Direction.NORTH,5,5);
    private Pusher pusherEast =new Pusher(roundList, Direction.EAST,5,5);
    private Pusher pusherSouth =new Pusher(roundList, Direction.SOUTH,5,5);
    private Pusher pusherWest =new Pusher(roundList, Direction.WEST,5,5);

    @Test
    public void testPushNorth(){
        roundList.add(1);
        pusherNorth.pushRobot(player, 1);
        assertTrue(player.getX() == 5 && player.getY() == 6);
    }

    @Test
    public void testPushEast(){
        roundList.add(1);
        pusherEast.pushRobot(player, 1);
        assertTrue(player.getX() == 6 && player.getY() == 5);
    }

    @Test
    public void testPushSouth(){
        roundList.add(1);
        pusherSouth.pushRobot(player, 1);
        assertTrue(player.getX() == 5 && player.getY() == 4);
    }

    @Test
    public void testPushWest(){
        roundList.add(1);
        pusherWest.pushRobot(player, 1);
        assertTrue(player.getX() == 4 && player.getY() == 5);
    }
    @Test
    public void testWontPushOnWrongRound(){
        roundList.add(2);
        pusherNorth.pushRobot(player,1);
        assertTrue(player.getX() == pusherWest.getX() && player.getY() == pusherWest.getY());
    }

}
