package inf112.balmerspeak.app.board;

import inf112.balmerspeak.app.robot.Direction;

public class ConveyorBelt {
    private int x;
    private int y;
    private Direction direction;
    private ConveyorMovementTypes beltType;
    private ConveyorColor color;


    public ConveyorBelt(int x, int y, Direction direction, ConveyorMovementTypes beltType, ConveyorColor color){
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.beltType = beltType;
        this.color = color;
    }
    public int getX(){return x;}

    public int getY(){return y;}

    public Direction getDirection(){return direction;}

    public ConveyorMovementTypes getBeltType(){return beltType;}

    public ConveyorColor getColor(){return color;}

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
}
