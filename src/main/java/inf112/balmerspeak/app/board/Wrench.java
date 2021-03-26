package inf112.balmerspeak.app.board;

import inf112.balmerspeak.app.robot.Robot;

public class Wrench {
    public int x;
    public int y;
    public int numberOfWrenches;

    public Wrench(int x, int y, int numberOfWrenches) {
        this.x = x;
        this.y = y;
        this.numberOfWrenches = numberOfWrenches;
    }

    public int getX() {return x;}

    public int getY() {return y;}

    public int getNumberOfWrenches(){return numberOfWrenches;}

}
