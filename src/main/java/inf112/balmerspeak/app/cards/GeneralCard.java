package inf112.balmerspeak.app.cards;

import inf112.balmerspeak.app.robot.Robot;

public class GeneralCard implements ICards {

    private MovementType type;
    private int priority;
    private String name;
    private Robot robot;

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

    public Robot getRobot() {
        return robot;
    }

}
