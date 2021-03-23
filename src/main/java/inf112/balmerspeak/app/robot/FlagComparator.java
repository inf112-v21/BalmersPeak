package inf112.balmerspeak.app.robot;

import inf112.balmerspeak.app.flag.Flag;

import java.util.Comparator;

public class FlagComparator implements Comparator<Flag> {

    @Override
    public int compare(Flag o1, Flag o2) {
        return o1.getOrder() - o2.getOrder();
    }
}
