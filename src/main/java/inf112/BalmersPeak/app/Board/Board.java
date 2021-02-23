package inf112.balmerspeak.app.Board;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.balmerspeak.app.Flag.Flag;
import inf112.balmerspeak.app.Robot.Direction;
import inf112.balmerspeak.app.Robot.Robot;

public class Board {

    private int WIDTH;
    private int HEIGHT;
    private Robot robots[][];
    private Flag flag[][];


    public Board(String filename){

        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(filename);

        TiledMapTileLayer board =  (TiledMapTileLayer) map.getLayers().get("Board");
        WIDTH = board.getWidth();
        HEIGHT = board.getHeight();

        robots = new Robot[HEIGHT][WIDTH];
        flag = new Flag[HEIGHT][WIDTH];


    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void placeRobot(int x, int y){
        robots[y][x] = new Robot(x,y, Direction.NORTH);
    }

    public boolean hasRobot(int x, int y){
        return robots[y][x] != null;
    }

    public Robot getRobot(int x, int y){
        return robots[y][x];
    }
}
