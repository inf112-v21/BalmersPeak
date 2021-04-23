package inf112.balmerspeak.app;


import inf112.balmerspeak.app.board.Wrench;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WrenchTest {
    private Wrench wrench = new Wrench(1,0,1);
    @Test
    public void testingTheGetCoordinates(){
        assertTrue(wrench.getX() == 1 && wrench.getY() == 0);
    }

    @Test
    public void testNumberOfWrenches() {
        assertTrue(wrench.numberOfWrenches >= 0 && wrench.numberOfWrenches <= 1);
    }

}
