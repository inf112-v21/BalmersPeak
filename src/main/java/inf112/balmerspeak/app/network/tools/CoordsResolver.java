package inf112.balmerspeak.app.network.tools;

import org.javatuples.Pair;

import java.util.ArrayList;

public class CoordsResolver {

    // Holds every start coordinate available and serves a new pair when requested
    private ArrayList<Pair<Integer,Integer>> availableStartCoordinates;

    public CoordsResolver() {
        availableStartCoordinates = new ArrayList<>();
        // Available coords (x,y): (1,0), (1,1), (1,3) (1,5), (1,6), (1,8), (1,10), (1,11)
        availableStartCoordinates.add(new Pair<>(1,6));
        availableStartCoordinates.add(new Pair<>(1,5));
        availableStartCoordinates.add(new Pair<>(1,8));
        availableStartCoordinates.add(new Pair<>(1,3));
        availableStartCoordinates.add(new Pair<>(1,10));
        availableStartCoordinates.add(new Pair<>(1,1));
        availableStartCoordinates.add(new Pair<>(1,11));
        availableStartCoordinates.add(new Pair<>(1,0));

    }

    public Pair<Integer,Integer> getCoordsPair() {
        return availableStartCoordinates.remove(0);
    }
}
