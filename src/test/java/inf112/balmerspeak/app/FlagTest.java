package inf112.balmerspeak.app;

import inf112.balmerspeak.app.flag.Flag;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import static org.junit.Assert.assertEquals;


public class FlagTest {

    private Flag flag1 = new Flag(1);
    private Flag flag2 = new Flag(2);
    private Flag flag3 = new Flag(3);

    private ArrayList<Flag> win = new ArrayList<>();
    private ArrayList<Flag> randomFlags = new ArrayList<>();

    @Before
    public void shuffleAndSortFlags() {
        win.add(flag1);
        win.add(flag2);
        win.add(flag3);

        randomFlags.add(flag1);
        randomFlags.add(flag2);
        randomFlags.add(flag3);

        Collections.shuffle(randomFlags);

        randomFlags.sort(new Flag.compareFlags());
    }

    @Test
    public void isOrderCorrect() {
        assertEquals(win,randomFlags);
    }
}
