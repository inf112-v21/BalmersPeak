package inf112.balmerspeak.app;

import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;

public class Laser {

        private int x;
        private int y;
        private Direction direction;

        public Laser(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;

        }


    public int getX() { return x; }

    public int getY() { return y; }

    public Direction getDirection() {return direction;}
}
