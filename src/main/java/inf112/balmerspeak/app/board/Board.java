package inf112.balmerspeak.app.board;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import inf112.balmerspeak.app.Hole;
import inf112.balmerspeak.app.Laser;
import inf112.balmerspeak.app.Player;
import inf112.balmerspeak.app.flag.Flag;
import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private TiledMap map;

    private final TiledMapTileLayer flag;
    private final TiledMapTileLayer playerLayer;
    private final TiledMapTileLayer laserPath;
    private final TiledMapTileLayer wrench;
    private final TiledMapTileLayer gear;
    private final TiledMapTileLayer start;
    private final TiledMapTileLayer laser;
    private final TiledMapTileLayer wall;
    private final TiledMapTileLayer conveyor;
    private final TiledMapTileLayer board;
    private int WIDTH;
    private int HEIGHT;
    private Robot robots[][];
    private Flag flags[][];
    private Hole holes[][];
    private Laser lasers[][];

    // Robots
    TiledMapTileLayer.Cell robot0;
    TiledMapTileLayer.Cell robot1;
    TiledMapTileLayer.Cell robot2;
    TiledMapTileLayer.Cell robot3;
    TiledMapTileLayer.Cell robot4;

    ArrayList<TiledMapTileLayer.Cell> robotTextures;


    private TiledMapTileLayer hole;

    private int flagOrder = 0;

    ArrayList<Robot> players;

    private int turn = 0;


    public Board(String filename){

        TmxMapLoader mapLoader = new TmxMapLoader();
        map = mapLoader.load(filename);

        // Instantiate layers
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");
        laserPath = (TiledMapTileLayer) map.getLayers().get("LaserPath");
        wrench = (TiledMapTileLayer) map.getLayers().get("Wrench");
        hole = (TiledMapTileLayer) map.getLayers().get("Hole");
        gear = (TiledMapTileLayer) map.getLayers().get("Gear");
        start = (TiledMapTileLayer) map.getLayers().get("Start");
        laser = (TiledMapTileLayer) map.getLayers().get("Laser");
        wall = (TiledMapTileLayer) map.getLayers().get("Wall");
        conveyor = (TiledMapTileLayer) map.getLayers().get("Conveyor");
        flag = (TiledMapTileLayer) map.getLayers().get("Flag");
        board = (TiledMapTileLayer) map.getLayers().get("Board");


        // Load robot cells
        loadRobotTextures();


        TiledMapTileLayer board =  (TiledMapTileLayer) map.getLayers().get("Board");
        WIDTH = board.getWidth();
        HEIGHT = board.getHeight();

        robots = new Robot[HEIGHT][WIDTH];
        flags = new Flag[HEIGHT][WIDTH];
        holes = new Hole[HEIGHT][WIDTH];
        lasers = new Laser[HEIGHT][WIDTH];

        players = new ArrayList<>();
        players.add(new Robot(0,0, Direction.NORTH));
        players.add(new Robot(1,1, Direction.NORTH));

        initHoles();
        initFlag();
        initLaser();
    }

    public void loadRobotTextures() {
        // Create list
        robotTextures = new ArrayList<>();
        // Load textures
        Texture robotTexture0 = new Texture("assets/images/robots/robot0.png");
        Texture robotTexture1 = new Texture("assets/images/robots/robot1.png");
        Texture robotTexture2 = new Texture("assets/images/robots/robot2.png");
        Texture robotTexture3 = new Texture("assets/images/robots/robot3.png");
        Texture robotTexture4 = new Texture("assets/images/robots/robot4.png");

        // Set cell textures
        robot0 = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(robotTexture0)));
        robot1 = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(robotTexture1)));
        robot2 = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(robotTexture2)));
        robot3 = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(robotTexture3)));
        robot4 = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(robotTexture4)));

        // Add to list
        robotTextures.addAll(Arrays.asList(robot0, robot1, robot2, robot3, robot4));
    }

    public TiledMap getMap() {
        return map;
    }

    public TiledMapTileLayer getBoard() {
        return board;
    }

    public void initHoles(){
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if(hole.getCell(x,y) != null) {
                    holes[y][x] = new Hole(x,y);
                }

            }
        }
    }

    public void initFlag(){
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if(flag.getCell(x,y) != null)
                    flags[y][x] = new Flag(flagOrder+=1);

            }

        }
    }

    public void initLaser(){
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if(laser.getCell(x,y) != null)
                    lasers[y][x] = new Laser(x,y, Direction.NORTH);

            }

        }
    }

    public Robot[][] getRobots() {
        return robots;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void placeRobot(Player player){
        int x = player.getRobot().getX();
        int y = player.getRobot().getY();

        playerLayer.setCell(x, y, robotTextures.get(player.getId()).setRotation(1)); //rotate to face the correct way
    }

    public void rotateRobot(Player player, int degrees) {
        TiledMapTileLayer.Cell robot = robotTextures.get(player.getId()).setRotation(degrees % 90);
        playerLayer.setCell(player.getRobot().getX(), player.getRobot().getY(),robot);
    }




    public boolean hasRobot(int x, int y){
        return robots[y][x] != null;
    }

    public Robot getRobot(int x, int y){
        return robots[y][x];
    }

    public Flag getFlag(int x, int y){
        return flags[y][x];
    }

    public void setFlag(int x, int y) {
        flags[y][x] = new Flag(1);

    }

    public void removeRobot(int x, int y){
        robots[y][x] = null;
    }


    public void move(Player player, int dx, int dy) {

        int x = player.getRobot().getX();
        int y = player.getRobot().getY();


        this.playerLayer.setCell(x + dx, y + dy, robotTextures.get(player.getId()));
        robots[y][x] = null;
        this.playerLayer.setCell(x, y, null);
    }

    public Hole getHole(int x, int y) {
        return holes[y][x];
    }

    public Laser getLaser(int x ,int y){
        return lasers[y][x];
    }
}
