package inf112.balmerspeak.app;

import org.javatuples.Pair;

public class Player {

    private Pair<Integer,Integer> robotCoordinates;
    private int health;
    private int damageleft;
    private String username;
    private String ipAddress;

    public Player(Pair<Integer,Integer> startCoordinates, String username, String ipAddress) {
        this.robotCoordinates = startCoordinates;
        this.health = 3;
        this.damageleft = 10;
        this.username = username;
        this.ipAddress = ipAddress;

    }

    public Pair<Integer,Integer> getCoords() {
        return this.robotCoordinates;
    }

    public String getUsername() {
        return this.username;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

}
