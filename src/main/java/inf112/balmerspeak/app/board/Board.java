package inf112.balmerspeak.app.board;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import inf112.balmerspeak.app.Player;
import inf112.balmerspeak.app.flag.Flag;
import inf112.balmerspeak.app.menu.GameScreen;
import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;
import org.javatuples.Pair;

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
    private int WIDTH;
    private int HEIGHT;
    private Robot robots[][];
    private Flag flags[][];
    private Hole holes[][];
    private Laser lasers[][];
    private Walls walls[][];
    private Wrench wrenches[][];
    private ConveyorBelt conveyors[][];

    // Robots
    TiledMapTileLayer.Cell robot0;
    TiledMapTileLayer.Cell robot1;
    TiledMapTileLayer.Cell robot2;
    TiledMapTileLayer.Cell robot3;
    TiledMapTileLayer.Cell robot4;

    ArrayList<TiledMapTileLayer.Cell> robotTextures;


    private TiledMapTileLayer hole;

    public Board(String filename) {

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


        TiledMapTileLayer board = (TiledMapTileLayer) map.getLayers().get("Board");
        WIDTH = board.getWidth();
        HEIGHT = board.getHeight();

        //set up the matrices for the board
        flags = new Flag[HEIGHT][WIDTH];
        holes = new Hole[HEIGHT][WIDTH];
        lasers = new Laser[HEIGHT][WIDTH];
        walls = new Walls[HEIGHT][WIDTH];
        wrenches = new Wrench[HEIGHT][WIDTH];
        conveyors = new ConveyorBelt[HEIGHT][WIDTH];

        // Init board elements
        initFlag();
        initBoardElements();
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

    public void initBoardElements() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (hole.getCell(x,y) != null)
                    holes[y][x] = new Hole(x,y);
                else if (laser.getCell(x,y) != null)
                    lasers[y][x] = new Laser(x,y, getLaserDirection(x,y));
                else if (wall.getCell(x,y) != null)
                    walls[y][x] = new Walls(x,y, getWallDirection(x,y));
                else if (conveyor.getCell(x,y) != null)
                    conveyors[y][x] = new ConveyorBelt(x,y,getConveyorDirection(x,y), getConveyorMovementType(x,y));
                else if (wrench.getCell(x,y) != null)
                    wrenches[y][x] = new Wrench(x,y,1);
            }
        }
    }


    public void initFlag(){
        flags[1][9] = new Flag(2);
        flags[5][15] = new Flag(1);
        flags[10][6] = new Flag(3);
    }



    public void placeRobot(Player player){
        int x = player.getRobot().getX();
        int y = player.getRobot().getY();

        // TODO: REMOVE EVERYTHING INSIDE IF, only for testing
        if (player.getId() == 0) {
            player.getRobot().set(6, 7);
            player.getRobot().setDirection(Direction.NORTH);
            playerLayer.setCell(6, 7, robotTextures.get(player.getId()).setRotation(1)); //rotate to face the correct way

        } else {
            player.getRobot().set(x,y);
            playerLayer.setCell(x,y,robotTextures.get(player.getId()).setRotation(1));
        }
    }

    public void rotateRobot(Player player, int degrees) {
        TiledMapTileLayer.Cell robot = robotTextures.get(player.getId()).setRotation(degrees % 90);
        playerLayer.setCell(player.getRobot().getX(), player.getRobot().getY(),robot);
    }


    // Fires board lasers
    public void fireBoardLasers(Player hostPlayer, ArrayList<Player> players, GameScreen screen) {
        // Check host player first
        if (laserPath.getCell(hostPlayer.getRobot().getX(), hostPlayer.getRobot().getY()) != null) {
            hostPlayer.getRobot().takeDamage();
            // Update GUI
            screen.show();

        }
        // Check if the robot is on a laser path
        for (Player player : players) {
            if (laserPath.getCell(player.getRobot().getX(), player.getRobot().getY()) != null) {
                player.getRobot().takeDamage();
            }
        }
    }

    public void fireRobotLasers(Player hostPlayer, ArrayList<Player> players) {
        // Get path of the robot laser first
        ArrayList<Pair<Integer,Integer>> path = getLaserPath(hostPlayer);
        for (Player player : players) {
            for (Pair<Integer, Integer> coords : path) {
                // Check if any players are in laser path
                if (coords.getValue0() == player.getRobot().getX() && coords.getValue1() == player.getRobot().getY()) {
                    // This player takes damage
                    player.getRobot().takeDamage();
                    break;
                }
            }
        }
    }

    public void placeRobot(int x, int y){
        robots[y][x] = new Robot(x,y,Direction.NORTH);
    }

    public boolean isRobotOnBelt(int x, int y) {
        return conveyor.getCell(x,y) != null;
    }

    public ArrayList<Pair<Integer,Integer>> getLaserPath(Player player) {
        // Get coords
        int playerX = player.getRobot().getX();
        int playerY = player.getRobot().getY();

        // Get direction
        Direction dir = player.getRobot().getDirection();

        ArrayList<Pair<Integer,Integer>> path = new ArrayList<>();
        if (dir == Direction.SOUTH) {
            for (int y = playerY; y < board.getHeight(); y++) {
                path.add(new Pair(playerX,y));
            }
        } else if (dir == Direction.NORTH) {
            for (int y = playerY; y >= 0; y--) {
                path.add(new Pair(playerX, y));
            }
        } else if (dir == Direction.EAST) {
            for (int x = playerX; x < board.getWidth(); x++) {
                path.add(new Pair(x, playerY));
            }
        } else if (dir == Direction.WEST) {
            for (int x = playerX; x >= 0; x--) {
                path.add(new Pair(x, playerY));
            }
        }
        return path;
    }


    public boolean hasRobot(int x, int y){
        return playerLayer.getCell(x,y) != null;
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


    public void move(Player hostPlayer, ArrayList<Player> players,  int dx, int dy) {

        int x = hostPlayer.getRobot().getX();
        int y = hostPlayer.getRobot().getY();

        int pdx = dx;
        int pdy = dy;

        for (Player p : players){
            if (p.getRobot().getX() == x+dx && p.getRobot().getY() == y+dy){
                if (dx < 0) pdx-=1;
                if (dx > 0) pdx+=1;
                if (dy < 0) pdy-=1;
                if (dy > 0) pdy+=1;
                p.getRobot().set(x+pdx,y+pdy);
                this.playerLayer.setCell(x+pdx,y+pdy, robotTextures.get(p.getId()));
                this.playerLayer.setCell(x+dx,y+dy, null);
            }
        }

        hostPlayer.getRobot().set(x+dx,y+dy);
        this.playerLayer.setCell(x + dx, y + dy, robotTextures.get(hostPlayer.getId()));
        //robots[y][x] = null;
        this.playerLayer.setCell(x, y, null);
    }

    public void moveRobot(Player player, int dx, int dy) {
        int playerX = player.getRobot().getX();
        int playerY = player.getRobot().getY();

        this.playerLayer.setCell(playerX + dx, playerY + dy, robotTextures.get(player.getId()));
        this.playerLayer.setCell(playerX, playerY, null);
    }

    public Hole getHole(int x, int y) {
        try {
            return holes[y][x];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public Laser getLaser(int x ,int y){
        try {
            return lasers[y][x];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
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

    public Direction getLaserDirection(int x,int y) {
        int id = laser.getCell(x,y).getTile().getId();
        switch (id) {
            case 38:
                return Direction.WEST;
            case 46:
                return Direction.EAST;
            case 95:
                return Direction.EAST;
            case 37:
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

    public void runBelt(Player hostPlayer, ArrayList<Player> players) {
        // Add host player to list
        players.add(hostPlayer);

        for (Player player : players) {

            // Get belt for current player
            ConveyorBelt belt = getConveyor(player.getRobot().getX(), player.getRobot().getY());

            // Check belts for current player
            if (belt != null) {
                // Run belt for host player
                if (!cantMove(belt)) {
                    beltMove(player, belt);
                    // Check if it is a rotating belt
                    if (nextIsRotating(belt)) {
                        beltRotate();
                    }
                }
            }
        }
    }


    public void beltMove(Player player, ConveyorBelt belt) {
        // Get player coords
        int playerX = player.getRobot().getX();
        int playerY = player.getRobot().getY();

        // Get change of coords from belts
        int dx = belt.getNextX(belt.getX())-belt.getX();
        int dy = belt.getNextY(belt.getY())-belt.getY();

        //move(hostPlayer,players, dx,dy); //TODO: perhaps reimplement this

        // Change coords of robot
        player.getRobot().set(playerX+dx,playerY+dy);

        // Move player robot texture
        this.playerLayer.setCell(playerX + dx, playerY + dy, robotTextures.get(player.getId()));
        this.playerLayer.setCell(playerX, playerY, null);
    }

    public void beltRotate(){
        //Kan låne rotate frå gear
    }

    public boolean cantMove(ConveyorBelt belt){
        if(nextIsBelt(belt))
            if (nextBeltType(belt).equals(ConveyorMovementTypes.move))
                return false;
            else if (nextBeltType(belt).equals(ConveyorMovementTypes.turn))
                return false;
            else if (nextBeltType(belt).equals(ConveyorMovementTypes.braidLeft))
                return checkBraidLeft(belt);
            else if (nextBeltType(belt).equals(ConveyorMovementTypes.braidRight))
                return checkBraidRight(belt);
            else if (nextBeltType(belt).equals(ConveyorMovementTypes.braidT))
                return checkBraidT(belt);
        return nextIsFull(belt);
    }

    public ConveyorMovementTypes nextBeltType(ConveyorBelt belt){ return getConveyor(belt.getNextX(belt.getX()),belt.getNextY(belt.getY())).getBeltType();}

    public boolean nextIsBelt(ConveyorBelt belt){ return containsConveyor(belt.getNextX(belt.getX()),belt.getNextY(belt.getY())); }

    public boolean nextIsFull(ConveyorBelt belt){ return hasRobot(belt.getNextX(belt.getX()),belt.getNextY(belt.getY())); }

    public boolean nextIsRotating(ConveyorBelt belt) {
        if (nextIsBelt(belt)) {
            ConveyorMovementTypes mt = nextBeltType(belt);
            return (mt.equals(ConveyorMovementTypes.turn) || mt.equals(ConveyorMovementTypes.braidLeft) || mt.equals(ConveyorMovementTypes.braidRight) || mt.equals(ConveyorMovementTypes.braidT));
        }else
            return false;
    }

    public boolean checkBraidLeft(ConveyorBelt belt){
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

    public boolean checkBraidRight(ConveyorBelt belt){
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

    public boolean checkBraidT(ConveyorBelt belt){
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
}
