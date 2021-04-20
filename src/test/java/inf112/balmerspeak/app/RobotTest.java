package inf112.balmerspeak.app;

import inf112.balmerspeak.app.cards.Rotation;
import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;
import org.junit.Test;
import org.lwjgl.system.CallbackI;

import static org.junit.Assert.assertTrue;


public class RobotTest {

    Robot robot = new Robot(0,0, Direction.NORTH);

    @Test
    public void canSetDirection(){
        robot.setDirection(Direction.EAST);
        assertTrue(robot.getDirection().equals(Direction.EAST));
    }

    @Test
    public void canGetDirection(){
        assertTrue(robot.getX() == 0 && robot.getY() == 0);
    }

    @Test
    public void canTurn(){
        assertTrue( robot.turn(Rotation.right, Direction.EAST) == Direction.SOUTH);
    }

    @Test
    public void canSetHealth(){
        robot.setHealth(5);
        assertTrue(robot.getHealth() == 5);
    }

    @Test
    public void canSetLives(){
        robot.setLives(1);
        assertTrue(robot.getLives() == 1);
    }
}
