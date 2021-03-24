package inf112.balmerspeak.app.cards;

import inf112.balmerspeak.app.robot.Robot;

import java.io.Serializable;

public class GeneralCard implements ICards, Serializable, Comparable<GeneralCard> {

    private MovementType type;
    private int priority;
    private String name;
    private Robot robot;

    public GeneralCard(MovementType type, int priority, String name) {
        this.type = type;
        this.priority = priority;
        this.name = name;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    @Override
    public int getPriority() { return priority; }

    @Override
    public String getName() { return name; }

    @Override
    public MovementType getType() {
        return type;
    }

    public Robot getRobot() {
        return robot;
    }


    // Reverse order with highest priority first, therefore the reverse operation below
    @Override
    public int compareTo(GeneralCard other) {
        return other.getPriority() - this.getPriority();
    }
}
