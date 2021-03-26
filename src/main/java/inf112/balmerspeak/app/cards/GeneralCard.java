package inf112.balmerspeak.app.cards;

import inf112.balmerspeak.app.Player;

import java.io.Serializable;

public class GeneralCard implements ICards, Serializable, Comparable<GeneralCard> {

    private MovementType type;
    private int priority;
    private String name;
    private Player player;

    public GeneralCard(MovementType type, int priority, String name) {
        this.type = type;
        this.priority = priority;
        this.name = name;
    }

    @Override
    public int getPriority() { return priority; }

    @Override
    public String getName() { return name; }

    @Override
    public MovementType getType() {
        return type;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }


    // Reverse order with highest priority first, therefore the reverse operation below
    @Override
    public int compareTo(GeneralCard other) {
        return other.getPriority() - this.getPriority();
    }
}
