package inf112.balmerspeak.app;

import inf112.balmerspeak.app.board.ConveyorBelt;
import inf112.balmerspeak.app.board.ConveyorColor;
import inf112.balmerspeak.app.board.ConveyorMovementTypes;
import inf112.balmerspeak.app.robot.Direction;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConveyorBeltTest {
    ConveyorBelt belt1 = new ConveyorBelt(4, 1, Direction.NORTH, ConveyorMovementTypes.MOVE, ConveyorColor.BLUE);
    ConveyorBelt belt2 = new ConveyorBelt(4, 2, Direction.EAST, ConveyorMovementTypes.MOVE, ConveyorColor.BLUE);
    ConveyorBelt belt3 = new ConveyorBelt(4, 3, Direction.SOUTH, ConveyorMovementTypes.MOVE, ConveyorColor.BLUE);
    ConveyorBelt belt4 = new ConveyorBelt(4, 4, Direction.WEST, ConveyorMovementTypes.MOVE, ConveyorColor.BLUE);

    @Test
    public void testGetNextX() {
        int x1 = belt1.getNextX(belt1.getX());
        int x2 = belt2.getNextX(belt2.getX());
        int x3 = belt3.getNextX(belt3.getX());
        int x4 = belt4.getNextX(belt4.getX());
        assertTrue(x1 == 4 && x2 == 5 && x3 == 4 && x4 ==  3);
    }

    @Test
    public void testGetNextY() {
        int y1 = belt1.getNextY(belt1.getY());
        int y2 = belt2.getNextY(belt2.getY());
        int y3 = belt3.getNextY(belt3.getY());
        int y4 = belt4.getNextY(belt4.getY());
        assertTrue(y1 == 2 && y2 == 2 && y3 == 2 && y4 ==  4);
    }
}
