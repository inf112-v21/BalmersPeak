package inf112.balmerspeak.app;

import inf112.balmerspeak.app.board.Wrench;
import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WrenchTest {
    /**{private Robot player = new Robot(0, 0, Direction.NORTH);
    private Wrench wrench = new Wrench(1,0,1);
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

    @Test
    public void testSpawnSetter(){
        wrench.setNewSpawn(player);
        assertTrue(player.getSpawnX() == wrench.getX() && player.getSpawnY() == player.getSpawnY());
    }*/
}
