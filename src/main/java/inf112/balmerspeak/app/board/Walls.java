package inf112.balmerspeak.app.board;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;

import java.util.List;
import java.util.Vector;

public class Walls implements IObjects {

    private Direction direction;
    private int x = 0;
    private int y = 0;

    public Walls(Direction dir, int x, int y) {
        this.direction = dir;
        this.x = x;
        this.y = y;
    }

    public Walls(Direction dir) {
        this.direction = dir;
    }

    public boolean collision(Vector2 pos, Direction dir) {
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }
}
