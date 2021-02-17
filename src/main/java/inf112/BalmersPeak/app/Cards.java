package inf112.balmerspeak.app;

import javax.swing.*;

public class Cards implements ICards {

    private int priority;
    private int action;

    // Legge til funksjon
    public Cards (int priority, int action) {
        this.priority = priority;
        this.action = action;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int getAction() {
        return action;
    }


}
