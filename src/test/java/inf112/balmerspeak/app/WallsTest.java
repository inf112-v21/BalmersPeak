package inf112.balmerspeak.app;

import inf112.balmerspeak.app.board.Walls;
import inf112.balmerspeak.app.robot.Direction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WallsTest {

    @Test
    public void NORTHWallHaveDirectionNORTH() {
        Walls wall = new Walls(1,1,Direction.NORTH);
        Direction direction = Direction.NORTH;
        assertEquals(wall.getDirection(),direction);
    }

    @Test
    public void SOUTHWallHaveDirectionSOUTH() {
        Walls wall = new Walls(1,1,Direction.SOUTH);
        Direction direction = Direction.SOUTH;
        assertEquals(wall.getDirection(),direction);
    }

    @Test
    public void WESTWallHaveDirectionWEST() {
        Walls wall = new Walls(1,1,Direction.WEST);
        Direction direction = Direction.WEST;
        assertEquals(wall.getDirection(),direction);
    }

    @Test
    public void EASTWallHaveDirectionEAST() {
        Walls wall = new Walls(1,1,Direction.EAST);
        Direction direction = Direction.EAST;
        assertEquals(wall.getDirection(),direction);
    }
}
