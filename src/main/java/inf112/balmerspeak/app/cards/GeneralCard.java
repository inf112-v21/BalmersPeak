package inf112.balmerspeak.app.cards;

public class GeneralCard implements ICards {

    private int priority;
    private String name;

    public GeneralCard(MovementType type, int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    @Override
    public int getPriority() { return priority; }

    @Override
    public String getName() { return name; }


}
