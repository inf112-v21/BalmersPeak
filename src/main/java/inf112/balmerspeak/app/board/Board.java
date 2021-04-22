package inf112.balmerspeak.app.board;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import inf112.balmerspeak.app.Player;
import inf112.balmerspeak.app.flag.Flag;
import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.cards.Rotation;
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
    private final TiledMapTileLayer pusher;
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
    private Pusher pushers[][];
    private Gear gears[][];

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
        pusher = (TiledMapTileLayer) map.getLayers().get("Pusher");
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
        pushers = new Pusher[HEIGHT][WIDTH];
        gears = new Gear[HEIGHT][WIDTH];


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
                    conveyors[y][x] = new ConveyorBelt(x,y,getConveyorDirection(x,y),getConveyorMovementType(x,y),getConveyorColor(x,y));
                else if (wrench.getCell(x,y) != null)
                    wrenches[y][x] = new Wrench(x,y,1);
                else if (pusher.getCell(x,y) != null)
                    pushers[y][x] = new Pusher(x,y,getPusherDirection(x,y), getRoundType(x,y));
                else if (gear.getCell(x,y) != null)
                    gears[y][x] = new Gear(x,y, getGearRotation(x, y));
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

        player.getRobot().set(x,y);
        playerLayer.setCell(x,y,robotTextures.get(player.getId()).setRotation(1));
    }

    public int getDegrees(Player player){
        return robotTextures.get(player.getId()).getRotation();
    }


    public void rotateRobot(Player player, int degrees) {
        TiledMapTileLayer.Cell robot = robotTextures.get(player.getId()).setRotation(degrees);
        playerLayer.setCell(player.getRobot().getX(), player.getRobot().getY(),robot);
    }

    public void runBoardElements(Player player) {
        int playerX = player.getRobot().getX();
        int playerY = player.getRobot().getY();

        if (isRobotOnBelt(playerX,playerY)) {
            // execute belt
            //conveyors[playerX][playerY].runBelt(player.getRobot());
        }
    }


    public Rotation getGearRotation(int x, int y) {
        int id = gear.getCell(x, y).getTile().getId();
        switch (id) {
            case 54:
                return Rotation.right;
            case 53:
                return Rotation.left;




        }
        return null;
    }

    public void placeRobot(int x, int y){
        robots[y][x] = new Robot(x,y,Direction.NORTH);
    }

    public boolean isRobotOnBelt(int x, int y) {
        return conveyor.getCell(x,y) != null;
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

    public Gear getGear(int x, int y) { return gears[y][x]; }

    public void setFlag(int x, int y) {
        flags[y][x] = new Flag(1);

    }

    public void removeRobot(int x, int y){
        robots[y][x] = null;
    }

    public void runGear(Player player){

        int x = player.getRobot().getX();
        int y = player.getRobot().getY();

        if (gears[y][x] != null){
            if (gears[y][x].getRotation() == Rotation.right && player.getRobot().getDirection() == Direction.EAST){
                player.getRobot().setDirection(player.getRobot().turn(Rotation.right, player.getRobot().getDirection()));
                rotateRobot(player, 4);
                System.out.println(gears[y][x].getRotation() + " " + player.getRobot().getDirection());
            }
            else if (gears[y][x].getRotation() == Rotation.right && player.getRobot().getDirection() == Direction.SOUTH){
                player.getRobot().setDirection(player.getRobot().turn(Rotation.right, player.getRobot().getDirection()));
                rotateRobot(player, 3);
                System.out.println(gears[y][x].getRotation() + " " + player.getRobot().getDirection());
            }
            else if (gears[y][x].getRotation() == Rotation.right && player.getRobot().getDirection() == Direction.WEST){
                player.getRobot().setDirection(player.getRobot().turn(Rotation.right, player.getRobot().getDirection()));
                rotateRobot(player, 2);
                System.out.println(gears[y][x].getRotation() + " " + player.getRobot().getDirection());
            }
            else if (gears[y][x].getRotation() == Rotation.right && player.getRobot().getDirection() == Direction.NORTH){
                player.getRobot().setDirection(player.getRobot().turn(Rotation.right, player.getRobot().getDirection()));
                rotateRobot(player, 1);
                System.out.println(gears[y][x].getRotation() + " " + player.getRobot().getDirection());
            }
            else if (gears[y][x].getRotation() == Rotation.left && player.getRobot().getDirection() == Direction.EAST){
                player.getRobot().setDirection(player.getRobot().turn(Rotation.left, player.getRobot().getDirection()));
                rotateRobot(player, 2);
                System.out.println(gears[y][x].getRotation() + " " + player.getRobot().getDirection());
            }
            else if (gears[y][x].getRotation() == Rotation.left && player.getRobot().getDirection() == Direction.NORTH) {
                player.getRobot().setDirection(player.getRobot().turn(Rotation.left, player.getRobot().getDirection()));
                rotateRobot(player, 3);
                System.out.println(gears[y][x].getRotation() + " " + player.getRobot().getDirection());
            }
            else if (gears[y][x].getRotation() == Rotation.left && player.getRobot().getDirection() == Direction.WEST) {
                player.getRobot().setDirection(player.getRobot().turn(Rotation.left, player.getRobot().getDirection()));
                rotateRobot(player, 4);
                System.out.println(gears[y][x].getRotation() + " " + player.getRobot().getDirection());
            }
            else if (gears[y][x].getRotation() == Rotation.left && player.getRobot().getDirection() == Direction.SOUTH) {
                player.getRobot().setDirection(player.getRobot().turn(Rotation.left, player.getRobot().getDirection()));
                rotateRobot(player, 1);
                System.out.println(gears[y][x].getRotation() + " " + player.getRobot().getDirection());
            }
        }
        else
            return;
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

    public Pusher getPusher(int x, int y) {return pushers[y][x];}

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

    public Direction getPusherDirection(int x,int y) {
        int id = pusher.getCell(x,y).getTile().getId();
        if (id == 1 || id == 9)
            return Direction.SOUTH;
        else if (id == 2 || id == 10)
            return Direction.WEST;
        else if (id == 3 || id == 11)
            return Direction.NORTH;
        else if (id == 4 || id == 12)
            return Direction.EAST;
        else
            return null;
    }

    public RoundType getRoundType(int x, int y){
        int id = pusher.getCell(x,y).getTile().getId();
        if (id == 1 || id == 2 || id == 11 || id == 4)
            return RoundType.EVEN;
        else if (id == 9 || id == 10 || id == 3 || id == 12)
            return RoundType.ODD;
        else
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
            return ConveyorMovementTypes.MOVE;
        else if (id == 28 || id == 33 || id == 36)
            return ConveyorMovementTypes.TURN;
        else if (id == 77)
            return ConveyorMovementTypes.CROSS;
        else
            return null;
    }

    public ConveyorColor getConveyorColor(int x, int y){
        int id = conveyor.getCell(x,y).getTile().getId();
        if (id == 33 || id == 36 || id == 49 || id == 50 || id == 51 || id == 52)
            return ConveyorColor.YELLOW;
        else if (id == 13 || id == 21 || id == 22 || id == 28 || id == 77)
            return ConveyorColor.BLUE;
        else
            return null;
    }

    public void pusherMove(Player hostPlayer,ArrayList<Player> players, Pusher push) {
        int dx = push.getNextX(push.getX())-push.getX();
        int dy = push.getNextY(push.getY())-push.getY();
        move(hostPlayer,players, dx,dy);
    }


    public void runBelt(Player player,ArrayList<Player> players, ConveyorBelt belt){
        if (canMove(belt)){
            move(player,players, belt.getNextX(belt.getX())-belt.getX(),belt.getNextY(belt.getY())-belt.getY());
        }else{
            System.out.println("Can't move because of robot on track");
        }
    }

    public void beltRotate(){
        //Kan låne rotate frå gear
    }

    public boolean canMove(ConveyorBelt belt){
        if(nextIsBelt(belt))
            if (nextBelt(belt).getBeltType().equals(ConveyorMovementTypes.MOVE))
                return true;
            else if (nextBelt(belt).getBeltType().equals(ConveyorMovementTypes.TURN))
                return true;
            else if (nextBelt(belt).getBeltType().equals(ConveyorMovementTypes.CROSS))
                return checkCross(belt);
        return !nextIsFull(belt);
    }

    public boolean nextIsBelt(ConveyorBelt belt){ return containsConveyor(belt.getNextX(belt.getX()),belt.getNextY(belt.getY())); }

    public ConveyorBelt nextBelt(ConveyorBelt belt){return getConveyor(belt.getNextX(belt.getX()),belt.getNextY(belt.getY()));}

    public boolean nextIsFull(ConveyorBelt belt){ return hasRobot(belt.getNextX(belt.getX()),belt.getNextY(belt.getY())); }

    public boolean checkCross(ConveyorBelt belt){
        ConveyorBelt cross = nextBelt(belt);
        int waitingRobots = 0;
        if (containsConveyor(cross.getX()+1, cross.getY()))
            if (hasRobot(cross.getX()+1, cross.getY()) && getConveyor(cross.getX()+1, cross.getY()).getDirection().equals(cross.getDirection()))
                waitingRobots++;
        if (containsConveyor(cross.getX()-1, cross.getY()))
            if (hasRobot(cross.getX()-1, cross.getY()) && getConveyor(cross.getX()-1, cross.getY()).getDirection().equals(cross.getDirection()))
                waitingRobots++;
        if (containsConveyor(cross.getX(), cross.getY()+1))
            if (hasRobot(cross.getX(), cross.getY()+1) && getConveyor(cross.getX(), cross.getY()+1).getDirection().equals(cross.getDirection()))
                waitingRobots++;
        if (containsConveyor(cross.getX()+1, cross.getY()-1))
            if (hasRobot(cross.getX(), cross.getY()-1) && getConveyor(cross.getX(), cross.getY()-1).getDirection().equals(cross.getDirection()))
                waitingRobots++;
        if (waitingRobots >= 2)
            return false;
        return true;
    }
}
