package inf112.balmerspeak.app.board;

import inf112.balmerspeak.app.robot.Direction;

public class Walls implements IObjects {

    private Direction direction;
    private int x;
    private int y;

    public Walls(int x, int y, Direction dir) {
        this.direction = dir;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }
}
