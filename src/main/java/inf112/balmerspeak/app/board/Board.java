package inf112.balmerspeak.app.board;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import inf112.balmerspeak.app.board.Hole;
import inf112.balmerspeak.app.board.Laser;
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
    private Walls walls[][];
    private Wrench wrenches[][];
    private ConveyorBelt conveyors[][];

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

        TiledMapTileLayer board = (TiledMapTileLayer) map.getLayers().get("Board");
        WIDTH = board.getWidth();
        HEIGHT = board.getHeight();

        robots = new Robot[HEIGHT][WIDTH];
        flags = new Flag[HEIGHT][WIDTH];
        holes = new Hole[HEIGHT][WIDTH];
        lasers = new Laser[HEIGHT][WIDTH];
        walls = new Walls[HEIGHT][WIDTH];
        wrenches = new Wrench[HEIGHT][WIDTH];
        conveyors = new ConveyorBelt[HEIGHT][WIDTH];

        players = new ArrayList<>();
        players.add(new Robot(0,0, Direction.NORTH));
        players.add(new Robot(1,1, Direction.NORTH));

        initHoles();
        initFlag();
        initLaser();
        initWalls();
        initWrenches();
        initConveyor();
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

    public boolean isFacingWall(int x, int y, Direction dir) {
        return false;
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

        flags[1][9] = new Flag(2);
        flags[5][15] = new Flag(1);
        flags[10][6] = new Flag(3);


    }

    public void initLaser(){
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if(laser.getCell(x,y) != null)
                    lasers[y][x] = new Laser(x,y, Direction.NORTH);
            }
        }
    }

    public void initWalls() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (wall.getCell(x,y) != null) {
                    walls[y][x] = new Walls(x, y,getWallDirection(x,y));
//                    System.out.println(""+ x + y + getWallDirection(x,y));
                }
            }

        }
    }

    public void initWrenches(){
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (wrench.getCell(x, y) != null) {
                    wrenches[y][x] = new Wrench(x, y, 1);
                }
            }
        }
    }

    public void initConveyor(){
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (conveyor.getCell(x, y) != null) {
                    conveyors[y][x] = new ConveyorBelt(x,y,getConveyorDirection(x,y),getConveyorMovementType(x,y));
                }
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

    public Walls getWalls(int x, int y) {
        return walls[y][x];
    }

    public Wrench getWrench(int x, int y) {return wrenches[y][x];}

    public ConveyorBelt getConveyor(int x, int y) {
        return conveyors[y][x];
    }

    public boolean containsConveyor(int x, int y){
        return conveyors[y][x] != null;
    }

    public Direction getWallDirection(int x,int y) {
        int id = wall.getCell(x,y).getTile().getId();
        switch (id) {
            case 30:
                return Direction.WEST;
            case 31:
                return Direction.NORTH;
            case 23:
                return Direction.EAST;
            case 29:
                return Direction.SOUTH;
        }
        return null;
    }

    public Direction getConveyorDirection(int x,int y) {
        int id = conveyor.getCell(x,y).getTile().getId();
        if (id == 13 || id == 49 || id == 77)
            return Direction.NORTH;
        else if (id == 52)
            return Direction.EAST;
        else if (id == 21 || id == 33  || id == 36 || id == 50)
            return Direction.SOUTH;
        else if (id == 22 || id == 28 ||  id == 51)
            return Direction.WEST;
        else
            return null;
    }

    public ConveyorMovementTypes getConveyorMovementType(int x, int y){
        int id = conveyor.getCell(x,y).getTile().getId();
        if (id == 13 || id == 21 || id == 22 || id == 49 || id == 50 || id == 51 || id == 52)
            return ConveyorMovementTypes.move;
        else if (id == 28 || id == 33 || id == 36 || id == 77)
            return ConveyorMovementTypes.turn;
        else
            return null;
    }


    public void runBelt(ConveyorBelt belt){
        if (!cantMove(belt)){
            beltMove(belt);
            if (nextIsRotating(belt))
                beltRotate();
        }else{
            System.out.println("Can't move because of robot on track");
        }
    }

    public boolean cantMove(ConveyorBelt belt){
        if(nextIsBelt(belt))
            if (nextBeltType(belt).equals(ConveyorMovementTypes.move))
                return false;
            else if (nextBeltType(belt).equals(ConveyorMovementTypes.turn))
                return false;
            else if (nextBeltType(belt).equals(ConveyorMovementTypes.braidLeft))
                return impossibleBraidLeft(belt);
            else if (nextBeltType(belt).equals(ConveyorMovementTypes.braidRight))
                return impossibleBraidRight(belt);
            else if (nextBeltType(belt).equals(ConveyorMovementTypes.braidT))
                return impossibleBraidT(belt);
        return nextIsFull(belt);
    }

    public ConveyorMovementTypes nextBeltType(ConveyorBelt belt){ return getConveyor(belt.getNextX(belt.getX()),belt.getNextY(belt.getY())).getBeltType();}

    public boolean nextIsBelt(ConveyorBelt belt){ return containsConveyor(belt.getNextX(belt.getX()),belt.getNextY(belt.getY())); }

    public boolean nextIsFull(ConveyorBelt belt){ return hasRobot(belt.getNextX(belt.getX()),belt.getNextY(belt.getY())); }

    public boolean nextIsRotating(ConveyorBelt belt){
        ConveyorMovementTypes mt = nextBeltType(belt);
        return  (mt.equals(ConveyorMovementTypes.turn) ||mt.equals(ConveyorMovementTypes.braidLeft) || mt.equals(ConveyorMovementTypes.braidRight) || mt.equals(ConveyorMovementTypes.braidT));
    }

    public boolean impossibleBraidLeft(ConveyorBelt belt){
        ConveyorBelt rBelt = getConveyor(belt.getNextX(belt.getX()),belt.getNextY(belt.getY()));
        Direction rotateDir = rBelt.getDirection();
        if (rotateDir.equals(Direction.NORTH)){
            return (hasRobot(rBelt.getX()-1,rBelt.getY()) && hasRobot(rBelt.getX(), rBelt.getY()-1));
        }else if (rotateDir.equals(Direction.EAST)){
            return (hasRobot(rBelt.getX()+1,rBelt.getY()) && hasRobot(rBelt.getX(), rBelt.getY()-1));
        }else if (rotateDir.equals(Direction.SOUTH)){
            return (hasRobot(rBelt.getX()+1,rBelt.getY()) && hasRobot(rBelt.getX(), rBelt.getY()+1));
        }else if (rotateDir.equals(Direction.WEST)){
            return (hasRobot(rBelt.getX()-1,rBelt.getY()) && hasRobot(rBelt.getX(), rBelt.getY()+1));
        }
        else
            return false;
    }

    public boolean impossibleBraidRight(ConveyorBelt belt){
        ConveyorBelt rBelt = getConveyor(belt.getNextX(belt.getX()),belt.getNextY(belt.getY()));
        Direction rotateDir = rBelt.getDirection();
        if (rotateDir.equals(Direction.NORTH)){
            return (hasRobot(rBelt.getX()+1,rBelt.getY()) && hasRobot(rBelt.getX(), rBelt.getY()-1));
        }else if (rotateDir.equals(Direction.EAST)){
            return (hasRobot(rBelt.getX()+1,rBelt.getY()) && hasRobot(rBelt.getX(), rBelt.getY()+1));
        }else if (rotateDir.equals(Direction.SOUTH)){
            return (hasRobot(rBelt.getX()-1,rBelt.getY()) && hasRobot(rBelt.getX(), rBelt.getY()+1));
        }else if (rotateDir.equals(Direction.WEST)){
            return (hasRobot(rBelt.getX()-1,rBelt.getY()) && hasRobot(rBelt.getX(), rBelt.getY()-1));
        }
        else
            return false;
    }

    public boolean impossibleBraidT(ConveyorBelt belt){
        ConveyorBelt rBelt = getConveyor(belt.getNextX(belt.getX()),belt.getNextY(belt.getY()));
        Direction rotateDir = rBelt.getDirection();
        if (rotateDir.equals(Direction.NORTH)){
            return (hasRobot(rBelt.getX()+1,rBelt.getY()) && hasRobot(rBelt.getX()-1, rBelt.getY()));
        }else if (rotateDir.equals(Direction.EAST)){
            return (hasRobot(rBelt.getX(),rBelt.getY()-1) && hasRobot(rBelt.getX(), rBelt.getY()+1));
        }
        else
            return false;
    }

    public void beltMove(ConveyorBelt belt) {
        int dx = belt.getNextX(belt.getX())-belt.getX();
        int dy = belt.getNextY(belt.getY())-belt.getY();
        move(belt.getX(), belt.getY(), dx,dy);
    }

    public void beltRotate(){
        //Kan låne rotate frå gear
    }
}
