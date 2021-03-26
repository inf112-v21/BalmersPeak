package inf112.balmerspeak.app;

import inf112.balmerspeak.app.board.Pusher;
import inf112.balmerspeak.app.board.RoundType;
import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;
import org.junit.Test;


import static org.junit.Assert.assertTrue;

public class PusherTest {
    private RoundType roundType = RoundType.ODD;
    private Robot player = new Robot(5, 5, Direction.NORTH);
    private Pusher pusherNorth =new Pusher(5,5, Direction.NORTH, roundType);
    private Pusher pusherEast =new Pusher(5,5, Direction.EAST, roundType);
    private Pusher pusherSouth =new Pusher(5,5, Direction.SOUTH, roundType);
    private Pusher pusherWest =new Pusher(5,5, Direction.WEST, roundType);

    @Test
    public void testPushNorth(){
        pusherNorth.pushRobot(player, roundType);
        assertTrue(player.getX() == 5 && player.getY() == 6);
    }

    @Test
    public void testPushEast(){
        pusherEast.pushRobot(player, roundType);
        assertTrue(player.getX() == 6 && player.getY() == 5);
    }

    @Test
    public void testPushSouth(){
        pusherSouth.pushRobot(player, roundType);
        assertTrue(player.getX() == 5 && player.getY() == 4);
    }

    @Test
    public void testPushWest(){
        pusherWest.pushRobot(player, roundType);
        assertTrue(player.getX() == 4 && player.getY() == 5);
    }
    @Test
    public void testWontPushOnWrongRound(){
        pusherNorth.pushRobot(player,RoundType.EVEN);
        assertTrue(player.getX() == pusherWest.getX() && player.getY() == pusherWest.getY());
    }

}
