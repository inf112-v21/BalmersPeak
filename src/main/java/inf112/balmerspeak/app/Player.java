package inf112.balmerspeak.app;

import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;
import org.javatuples.Pair;

import java.io.Serializable;

public class Player implements Serializable {

    private String username;
    private String ipAddress;
    private int id;
    private Robot robot;
    private boolean handReady;

    public Player(Pair<Integer,Integer> robotCoords, String username, String ipAddress, int id) {
        this.username = username;
        this.ipAddress = ipAddress;
        this.id = id;
        this.robot = new Robot(robotCoords.getValue0(), robotCoords.getValue1(), Direction.NORTH);
        this.handReady = false;
    }

    public Robot getRobot() {
        return this.robot;
    }

    public String getUsername() {
        return this.username;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public boolean isHandReady() {
        return this.handReady;
    }

    public int getId() {
        return this.id;
    }

    public void setHandReady(boolean ready) {
        this.handReady = ready;
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
