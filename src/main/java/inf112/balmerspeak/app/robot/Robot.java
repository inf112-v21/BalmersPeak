package inf112.balmerspeak.app.robot;

import inf112.balmerspeak.app.cards.Deck;
import inf112.balmerspeak.app.cards.ProgramCard;
import inf112.balmerspeak.app.cards.Rotation;

import java.io.Serializable;
import java.util.ArrayList;


import inf112.balmerspeak.app.robot.Direction;



public class Robot implements IRobot, Serializable {

    private int x;
    private int y;
    private int hp = 9;
    private int ll = 3;
    private Direction direction;

    private int spawnX;
    private int spawnY;

    public Robot(int xCoord, int yCoord, Direction dir){
        x = xCoord;
        y = yCoord;
        direction = dir;
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

    @Override
    public boolean isAlive() {
        return (getHealth() == 0 || getLives() == 0);
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
  
    public int getSpawnX(){return spawnX;}

    public int getSpawnY(){return spawnY;}

    public void setSpawnCoordinates(int spawnX, int spawnY){
        this.spawnX = spawnX;
        this.spawnY = spawnY;

    }
}
