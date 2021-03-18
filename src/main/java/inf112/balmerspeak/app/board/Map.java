package inf112.balmerspeak.app.board;

import inf112.balmerspeak.app.robot.Direction;

public class Map {

    private Direction north = Direction.NORTH;
    private Direction south = Direction.SOUTH;
    private Direction west = Direction.WEST;
    private Direction east = Direction.EAST;

    public Map(Board board) {
        board.addItem(new Walls(north), 0,4);
    }
}
