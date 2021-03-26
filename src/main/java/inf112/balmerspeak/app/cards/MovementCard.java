package inf112.balmerspeak.app.cards;

public class MovementCard extends ProgramCard {

    private int distance;
    private int priority;

    public MovementCard(int priority, int distance, String name) {
        super(MovementType.movement, priority, name);
        this.distance = distance;
        this.priority = priority;

    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return distance+"move"+priority;
    }

}
