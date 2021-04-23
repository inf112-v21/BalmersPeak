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
import inf112.balmerspeak.app.cards.Rotation;
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

    public int resolveRotation(Direction direction) {
        if (direction == Direction.SOUTH)
            return 0;
        else if (direction == Direction.EAST)
            return 1;
        else if (direction == Direction.NORTH)
            return 2;
        else
            return 3;
    }



    public void placeRobot(Player player){
        int x = player.getRobot().getX();
        int y = player.getRobot().getY();

        player.getRobot().set(x,y);
        playerLayer.setCell(x,y,robotTextures.get(player.getId()).setRotation(1));
    }


    public void rotateRobot(Player player, int degrees) {
        TiledMapTileLayer.Cell robot = robotTextures.get(player.getId()).setRotation(degrees);
        playerLayer.setCell(player.getRobot().getX(), player.getRobot().getY(),robot);
    }

    public void rotateRobot(Player player, Direction direction) {
        TiledMapTileLayer.Cell robot = robotTextures.get(player.getId()).setRotation(resolveRotation(direction));
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


    public void fireRobotLasers(ArrayList<Player> players, GameScreen screen) {

        for (Player player : players) {
            // Get path of the robot laser first
            ArrayList<Pair<Integer, Integer>> path = getLaserPath(player);

            for (Player targetPlayer : players) {
                if (player != targetPlayer) {
                    // Check if the target player is in path
                    if (isTargetInRobotLaserPath(path, targetPlayer)) {
                        // Robot takes damage
                        targetPlayer.getRobot().takeDamage();
                        // if host player, update GUI
                        if (targetPlayer.getId() == 0) {
                            screen.show();
                        }
                    }
                }
            }
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

    public boolean isTargetInRobotLaserPath(ArrayList<Pair<Integer,Integer>> path, Player targetPlayer) {
        for (Pair<Integer,Integer> coords : path) {
            if (coords.getValue0() == targetPlayer.getRobot().getX() && coords.getValue1() == targetPlayer.getRobot().getY()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Pair<Integer,Integer>> getLaserPath(Player player) {
        // Get coords
        int playerX = player.getRobot().getX();
        int playerY = player.getRobot().getY();

        // Get direction
        Direction dir = player.getRobot().getDirection();

        ArrayList<Pair<Integer,Integer>> path = new ArrayList<>();
        if (dir == Direction.SOUTH) {
            for (int y = playerY-1; y >= 0; y--) {
                path.add(new Pair(playerX,y));
            }
        } else if (dir == Direction.NORTH) {
            for (int y = playerY+1; y < board.getHeight(); y++) {
                path.add(new Pair(playerX, y));
            }
        } else if (dir == Direction.EAST) {
            for (int x = playerX+1; x < board.getWidth(); x++) {
                path.add(new Pair(x, playerY));
            }
        } else if (dir == Direction.WEST) {
            for (int x = playerX-1; x >= 0; x--) {
                path.add(new Pair(x, playerY));
            }
        }
        return path;
    }


    public boolean hasRobot(int x, int y){
        return playerLayer.getCell(x,y) != null;
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


    public void runBelt(ArrayList<Player> players) {


        for (Player player : players) {

            // Get belt for current player
            ConveyorBelt belt = getConveyor(player.getRobot().getX(), player.getRobot().getY());

            // Check belts for current player
            if (belt != null) {
                // Run belt for host player
                if (canMove(belt)) {
                    beltMove(player, belt);
                }
            }
        }
    }

    public void runPusher(ArrayList<Player> players) {
        for (Player player : players) {
            // Get pusher for current player
            Pusher pusher = getPusher(player.getRobot().getX(), player.getRobot().getY());
            // Check pushers for current player
            if (pusher != null) {
                pusherMove(player, pusher);
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

        // Change coords of robot
        player.getRobot().set(playerX+dx,playerY+dy);

        // Move player robot texture
        this.playerLayer.setCell(playerX + dx, playerY + dy, robotTextures.get(player.getId()));
        this.playerLayer.setCell(playerX, playerY, null);
    }

    public void pusherMove(Player player, Pusher push) {
        // Get player coords
        int playerX = player.getRobot().getX();
        int playerY = player.getRobot().getY();

        // Get change of coords from belts
        int dx = push.getNextX(push.getX())-push.getX();
        int dy = push.getNextY(push.getY())-push.getY();

        // Change coords of robot
        player.getRobot().set(playerX+dx,playerY+dy);

        // Move player robot texture
        this.playerLayer.setCell(playerX + dx, playerY + dy, robotTextures.get(player.getId()));
        this.playerLayer.setCell(playerX, playerY, null);
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
