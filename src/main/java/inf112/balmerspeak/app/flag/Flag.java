package inf112.balmerspeak.app.flag;

import java.util.Comparator;

public class Flag {

    private int Order;

    public Flag(int order){
        Order = order;
    }

    public int getOrder() {
        return Order;
    }

    @Override
    public String toString() {
        return "Flag{" +
                "Order=" + Order +
                '}';
    }

    public static class compareFlags implements Comparator<Flag> {
        @Override
        public int compare(Flag flag1, Flag flag2) {
            if (flag1.getOrder() > flag2.getOrder()) {
                return 1;
            } else if (flag1.getOrder() < flag2.getOrder()) {
                return -1;
            }
            return 0;
        }
    }
}
