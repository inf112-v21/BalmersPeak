package inf112.balmerspeak.app.board;

import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;


public class Pusher {
    private int x;
    private int y;
    private Direction direction;
    private RoundType roundType;

    public Pusher(int x, int y, Direction direction, RoundType roundType){
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.roundType = roundType;
    }

    public void pushRobot(Robot player, RoundType thisRoundType){
        if (roundType.equals(thisRoundType)){
            player.set(getNextX(x),getNextY(y));
        }
    }

    public int getNextX(int x){
        if (direction.equals(Direction.EAST))
            return x+1;
        else if (direction.equals(Direction.WEST))
            return x-1;
        else
            return x;
    }

    public int getNextY(int y){
        if (direction.equals(Direction.NORTH))
            return y+1;
        else if (direction.equals(Direction.SOUTH))
            return y-1;
        else
            return y;
    }

    public int getX() {return x;}

    public int getY() {return y;}
}
