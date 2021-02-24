package inf112.balmerspeak.app.cards;

public class MovementCard extends ProgramCard {

    private int distance;
    private boolean forward;

    public MovementCard(int priority, int distance, String name) {
        super(MovementType.movement, priority, name);
        this.distance = distance;

    }

    public int getDistance() {
        return distance;
    }
}
