package inf112.balmerspeak.app.board;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import inf112.balmerspeak.app.Hole;
import inf112.balmerspeak.app.Laser;
import inf112.balmerspeak.app.flag.Flag;
import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;

import java.util.ArrayList;
import java.util.Arrays;

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
    private final TiledMapTileLayer.Cell player;
    private final TiledMapTileLayer.Cell wonCell;
    private final TiledMapTileLayer.Cell dieCell;
    private int WIDTH;
    private int HEIGHT;
    private Robot robots[][];
    private Flag flags[][];
    private Hole holes[][];
    private Laser lasers[][];

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

        // Instantiate player texture
        Texture playerTexture = new Texture("assets/images/player.png");
        TextureRegion[][] playerTextureRegion = TextureRegion.split(playerTexture, 300, 300);

        StaticTiledMapTile normalPlayerTexture = new StaticTiledMapTile(playerTextureRegion[0][0]);
        StaticTiledMapTile playerDiedTexture = new StaticTiledMapTile(playerTextureRegion[0][1]);
        StaticTiledMapTile playerWonTexture = new StaticTiledMapTile(playerTextureRegion[0][2]);
        player = new TiledMapTileLayer.Cell().setTile(normalPlayerTexture);
        wonCell = new TiledMapTileLayer.Cell().setTile(playerWonTexture);
        dieCell = new TiledMapTileLayer.Cell().setTile(playerDiedTexture);

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

    public int switchTurn(){
        if (turn == 0)
            return turn = 1;
        else
            return turn = 0;
    }

    public ArrayList<Robot> getPlayers() {
        return players;
    }

    public Robot getActivePlayer(){
        return players.get(turn);
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

    public Robot placeRobot(int x, int y){
        playerLayer.setCell(x,y, player);
        return robots[y][x] = new Robot(x,y, Direction.NORTH);
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

    public void move(int x, int y, int dx, int dy){
        robots[y+dy][x+dx] = robots[y][x];
        if(holes[y+dy][x+dx] != null) {
           this.playerLayer.setCell(x + dx, y + dy, dieCell);
        }
        else if(flags[y+dy][x+dx] != null)
            this.playerLayer.setCell(x+dx,y+dy, wonCell);
        else {
            this.playerLayer.setCell(x + dx, y + dy, player);
        }
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
