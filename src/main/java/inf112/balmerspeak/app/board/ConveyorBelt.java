package inf112.balmerspeak.app.board;

import inf112.balmerspeak.app.MapHandler;
import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;

public class ConveyorBelt {
    private int x;
    private int y;
    private Direction direction;
    private MapHandler map;

    public ConveyorBelt(int x, int y, Direction direction, MapHandler map){
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.map = map;
    }

    public void runBelt(Robot player){
        if (canMove()){
            beltMove(player);
            if (isRotatingBelt())
                beltRotate();
        }else{
            System.out.println("Can't move because of robot on track");
        }
    }

    public boolean canMove(){
        if(!isRotatingBelt() && !nextIsFull())
            return true;
        else if (isRotatingBelt() && !nextNextIsFull())
            return true;
        else
        return false;
    }

    public boolean isRotatingBelt(){

        return false;
    }

    public boolean nextNextIsFull(){
        if (map.checkPlayer(getNextX(getNextX(x)),getNextY(getNextY(y))) && map.checkBelt(getNextX(getNextX(x)),getNextY(getNextY(y))))
            return true;
        else
            return false;
    }

    public boolean nextIsFull(){
        return map.checkPlayer(getNextX(x),getNextY(y));
    }

    public void beltMove(Robot player) {
        player.set(getNextX(x),getNextY(y));
    }

    public void beltRotate(){
        //Kan låne rotate frå gear
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
}
