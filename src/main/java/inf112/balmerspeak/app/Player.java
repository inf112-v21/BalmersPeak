package inf112.balmerspeak.app;

import inf112.balmerspeak.app.cards.Deck;
import inf112.balmerspeak.app.cards.ProgramCard;
import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;
import org.javatuples.Pair;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

    private String username;
    private String ipAddress;
    private int id;

    private Robot robot;

    private boolean handReady;
    private ArrayList<ProgramCard> hand;

    public Player(Pair<Integer,Integer> robotCoords, String username, String ipAddress, int id) {
        this.username = username;
        this.ipAddress = ipAddress;
        this.id = id;
        this.robot = new Robot(robotCoords.getValue0(), robotCoords.getValue1(), Direction.EAST);
        this.handReady = false;
        hand = new ArrayList<>();
    }

    public void dealHand(int amount) {
        Deck deck = new Deck();
        hand.addAll(deck.getCards(amount));
    }

    public void setHand(ArrayList<ProgramCard> cards) {
        this.hand = cards;
    }

    public ArrayList<ProgramCard> getHand() {
        return this.hand;
    }

    public Robot getRobot() {
        return this.robot;
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player))
            return false;
        Player other = (Player) obj;
        return this.id == other.getId();
    }
}
