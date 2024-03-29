package inf112.balmerspeak.app.robot;


import inf112.balmerspeak.app.cards.Rotation;

import java.io.Serializable;
import java.util.ArrayList;



import inf112.balmerspeak.app.flag.Flag;
import org.javatuples.Pair;


public class Robot implements IRobot, Serializable {

    private int x;
    private int y;
    private int hp = 9;
    private int ll = 3;
    private Direction direction;

    private ArrayList<Flag> collected = new ArrayList<>();

    private int spawnX;
    private int spawnY;

    public Robot(int xCoord, int yCoord, Direction dir){
        x = xCoord;
        y = yCoord;
        direction = dir;
    }


    public void addFlag(Flag flag){
        if(collected.isEmpty() && flag.getOrder() == 1)
            collected.add(flag);
        else if (collected.size() == 1 && collected.get(0).getOrder() == 1 && flag.getOrder() == 2)
            collected.add(flag);
        else if (collected.size() == 2 && collected.get(0).getOrder() == 1 && collected.get(1).getOrder() == 2 && flag.getOrder() == 3)
            collected.add(flag);
        for (Flag flag1 : collected)
            System.out.println(flag1.toString());
    }

    public boolean checkWinCondition() {
        return collected.size() == 3;
    }




    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getHealth() {
        return this.hp;
    }

    @Override
    public int getLives() {
        return this.ll;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    public Direction uTurn() {
        return uTurnDirection(direction);
    }

    @Override
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setHealth(int x) {
        this.hp = x;
    }

    @Override
    public void setLives(int x) {
        this.ll = x;
    }

    public void takeDamage() {
        this.hp -= 1;
    }

    public void takeLife(){
        this.ll -= 1;
    }

    public void setDirection(Direction dir){
        this.direction = dir;
    }


    public Direction turn(Rotation rotation, Direction direction) {
        switch (direction) {
            case NORTH:
                if (rotation.equals(Rotation.right)) return Direction.EAST;
                else if (rotation.equals(Rotation.uturn)) return Direction.SOUTH;
                else return Direction.WEST;
            case SOUTH:
                if (rotation.equals(Rotation.right)) return Direction.WEST;
                else if (rotation.equals(Rotation.uturn)) return Direction.NORTH;
                else return Direction.EAST;
            case WEST:
                if (rotation.equals(Rotation.right)) return Direction.NORTH;
                else if (rotation.equals(Rotation.uturn)) return Direction.EAST;
                else return Direction.SOUTH;
            case EAST:
                if (rotation.equals(Rotation.right)) return Direction.SOUTH;
                else if (rotation.equals(Rotation.uturn)) return Direction.WEST;
                else return Direction.NORTH;
            default:
                return null;
        }
    }

    public Direction uTurnDirection(Direction direction) {
        switch (direction) {
            case NORTH:
                return Direction.SOUTH;
            case WEST:
                return Direction.EAST;
            case SOUTH:
                return Direction.NORTH;
            case EAST:
                return Direction.WEST;
            default:
                return null;

        }
    }
}
