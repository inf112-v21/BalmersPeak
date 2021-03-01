package inf112.BalmersPeak.app;

import inf112.BalmersPeak.app.board.Wrench;
import inf112.BalmersPeak.app.robot.Direction;
import inf112.BalmersPeak.app.robot.Robot;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WrenchTest {
    Robot player = new Robot(0, 0, Direction.NORTH);
    Wrench wrench = new Wrench();
    @Test
    public void testHealingAmount(){
        player.setHealth(1);
        wrench.fixDmg(player);
        wrench.fixDmg(player);
        wrench.fixDmg(player);
        wrench.fixDmg(player);
        assertTrue(player.getHealth() == 5);
    }

    @Test
    public void testHealingLimit(){
        player.setHealth(7);
        wrench.fixDmg(player);
        wrench.fixDmg(player);
        wrench.fixDmg(player);
        wrench.fixDmg(player);
        assertTrue(player.getHealth() == 10);
    }
}
