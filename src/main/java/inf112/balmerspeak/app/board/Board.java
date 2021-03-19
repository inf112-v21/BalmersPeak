package inf112.balmerspeak.app.board;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.balmerspeak.app.Hole;
import inf112.balmerspeak.app.flag.Flag;
import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;

import java.util.ArrayList;

import java.util.Arrays;

public class Board {

    private int WIDTH;
    private int HEIGHT;
    private Robot robots[][];
    private Flag flag[][];
    private ArrayList<IObjects>[][] objects;
    private Hole holes[][];

    private TiledMapTileLayer hole;


    public Board(String filename){

        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(filename);

        TiledMapTileLayer board =  (TiledMapTileLayer) map.getLayers().get("Board");
        hole = (TiledMapTileLayer) map.getLayers().get("Hole");
        WIDTH = board.getWidth();
        HEIGHT = board.getHeight();

        robots = new Robot[HEIGHT][WIDTH];
        flag = new Flag[HEIGHT][WIDTH];

        objects = new ArrayList[HEIGHT][WIDTH];

        initHoles();



    }

    }

    public void addItem(IObjects object, int x, int y) {
        object.setX(x);
        object.setY(y);
        objects[y][x].add(object);
    public void initHoles(){
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if(hole.getCell(x,y) != null) {
                    holes[y][x] = new Hole(x,y);
                }

            }
        }
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

    public Flag getFlag(int x, int y){
        return flag[y][x];
    }

    public void setFlag(int x, int y) {
        flag[y][x] = new Flag(1);

    }

    public void removeRobot(int x, int y){
        robots[y][x] = null;
    }

    public void move(int x, int y, int dx, int dy){
        placeRobot(x+dx, y+dy);
        removeRobot(x,y);
    }

}
