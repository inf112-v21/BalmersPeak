package inf112.balmerspeak.app;

import inf112.balmerspeak.app.board.Pusher;
import inf112.balmerspeak.app.board.RoundType;
import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;
import org.junit.Test;


import static org.junit.Assert.assertTrue;

public class PusherTest {
    private Pusher pusherNorth =new Pusher(5,5, Direction.NORTH, RoundType.ODD);
    private Pusher pusherEast =new Pusher(5,5, Direction.EAST, RoundType.ODD);
    private Pusher pusherSouth =new Pusher(5,5, Direction.SOUTH, RoundType.ODD);
    private Pusher pusherWest =new Pusher(5,5, Direction.WEST, RoundType.ODD);

    @Test
    public void testGetNextX() {
        int x1 = pusherNorth.getNextX(pusherNorth.getX());
        int x2 = pusherEast.getNextX(pusherEast.getX());
        int x3 = pusherSouth.getNextX(pusherSouth.getX());
        int x4 = pusherWest.getNextX(pusherWest.getX());
        assertTrue(x1 == 5 && x2 == 6 && x3 == 5 && x4 == 4);
    }

    @Test
    public void testGetNextY() {
        int y1 = pusherNorth.getNextY(pusherNorth.getY());
        int y2 = pusherEast.getNextY(pusherEast.getY());
        int y3 = pusherSouth.getNextY(pusherSouth.getY());
        int y4 = pusherWest.getNextY(pusherWest.getY());
        assertTrue(y1 == 6 && y2 == 5 && y3 == 4 && y4 ==  5);
    }

}
