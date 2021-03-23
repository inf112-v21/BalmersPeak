package inf112.balmerspeak.app;

import org.javatuples.Pair;

public class Player {

    private Pair<Integer,Integer> robotCoordinates;
    private int health;
    private int damageleft;
    private String username;
    private String ipAddress;
    private int id;

    public Player(Pair<Integer,Integer> startCoordinates, String username, String ipAddress, int id) {
        this.robotCoordinates = startCoordinates;
        this.health = 3;
        this.damageleft = 10;
        this.username = username;
        this.ipAddress = ipAddress;
        this.id = id;

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

    public int getId() {
        return this.id;
    }

    // Will only work when the robot are at starting positions, which is the only time it will be used
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player))
            return false;
        Player other = (Player) obj;
        return this.id == other.getId();
    }
}
