package inf112.balmerspeak.app.cards;

import inf112.balmerspeak.app.robot.Direction;

public class Turning {

    public Direction turn(Rotation rotation, Direction direction) {
        switch (direction) {
            case NORTH:
                if(rotation.equals(Rotation.right)) return Direction.EAST;
                else return Direction.WEST;
            case SOUTH:
                if(rotation.equals(Rotation.right)) return Direction.WEST;
                else return Direction.EAST;
            case WEST:
                if(rotation.equals(Rotation.right)) return Direction.NORTH;
                else return Direction.SOUTH;
            case EAST:
                if(rotation.equals(Rotation.right)) return Direction.SOUTH;
                else return Direction.NORTH;
            default:
                return null;
        }
    }
}