package inf112.balmerspeak.app.network.messages;


import org.javatuples.Pair;

public class StartMsg {

    // Pair holding the x,y coordinates of where the receiving player should start
    private final Pair<Integer, Integer> startCoordinates;

    public StartMsg(Pair<Integer, Integer> startCoordinates) {
        this.startCoordinates = startCoordinates;
    }

    public Pair<Integer, Integer> getCoordinates() {
        return this.startCoordinates;
    }

    public int getX() {
        return this.startCoordinates.getValue0();
    }

    public int getY() {
        return this.startCoordinates.getValue1();
    }
}
