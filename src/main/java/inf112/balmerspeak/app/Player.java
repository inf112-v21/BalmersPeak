package inf112.balmerspeak.app;

import org.javatuples.Pair;

public class Player {

    private Pair<Integer,Integer> robotCoordinates;
    private int health;
    private int damageleft;

    public Player(Pair<Integer,Integer> startCoordinates) {
        this.robotCoordinates = startCoordinates;
        this.health = 3;
        this.damageleft = 10;
    }

    public Pair<Integer,Integer> getCoords() {
        return this.robotCoordinates;
    }


}
