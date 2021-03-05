package inf112.balmerspeak.app.cards;

public class RotationCard extends ProgramCard {

    private Rotation rotation;
    public int rotate;
    public boolean uturn;

    public RotationCard(int priority, Rotation rotation, String name) {
        super(MovementType.rotation, priority, name);
        this.rotation = rotation;

    }

    public Rotation getRotation() {
        return rotation;
    }

    @Override
    public String toString() {
        return "RotationCard " + rotation;
    }
}
