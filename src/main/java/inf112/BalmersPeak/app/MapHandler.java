package inf112.BalmersPeak.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class MapHandler {

    // Map
    private TiledMap map;

    // Layers
    private TiledMapTileLayer laserPath;
    private TiledMapTileLayer wrench;
    private TiledMapTileLayer hole;
    private TiledMapTileLayer gear;
    private TiledMapTileLayer start;
    private TiledMapTileLayer laser;
    private TiledMapTileLayer wall;
    private TiledMapTileLayer conveyor;
    private TiledMapTileLayer playerLayer;
    private TiledMapTileLayer flag;
    private TiledMapTileLayer board;

    // Player cells (alive, dead, won)
    public TiledMapTileLayer.Cell player;
    public TiledMapTileLayer.Cell wonCell;
    public TiledMapTileLayer.Cell dieCell;


    public MapHandler() {
        // Instantiate map
        map = new TmxMapLoader().load("assets/map/map.tmx");

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

        // Place player at (0,0)
        playerLayer.setCell(0, 0, player);
    }

    public TiledMap getMap() {
        return this.map;
    }

    public TiledMapTileLayer getBoard() {
        return this.board;
    }

    public TiledMapTileLayer getLaserPathLayer() { return this.laserPath; }

    public TiledMapTileLayer getWrenchLayer() { return this.wrench; }

    public TiledMapTileLayer getGearLayer() { return this.gear; }

    public TiledMapTileLayer getStartPadsLayer() { return this.start; }

    public TiledMapTileLayer getLaserLayer() { return this.laser; }

    public TiledMapTileLayer getWallLayer() { return this.wall; }

    public TiledMapTileLayer getConveyorLayer() { return this.conveyor; }

    public TiledMapTileLayer getPlayerLayer() {
        return this.playerLayer;
    }

    public void movePlayer(int xCoord, int yCoord, int dx, int dy) {
        // Set new cell to the player texture
        this.playerLayer.setCell(xCoord + dx, yCoord + dy, this.player);
        // Set remove previous cell texture
        this.playerLayer.setCell(xCoord, yCoord, null);
    }

    public boolean checkWin(int xCoord, int yCoord) {
        return this.flag.getCell(xCoord, yCoord) != null;
    }

    public boolean checkDeath(int xCoord, int yCoord) {
        return this.hole.getCell(xCoord, yCoord) != null;
    }

    public boolean checkWrench(int xCoord, int yCoord) {
        return this.wrench.getCell(xCoord, yCoord) != null;
    }

    public void changePlayerTextureWin(int xCoord, int yCoord) {
        this.playerLayer.setCell(xCoord, yCoord, wonCell);
    }

    public void changePlayerTextureDeath(int xCoord, int yCoord) {
        this.playerLayer.setCell(xCoord, yCoord, dieCell);
    }

    
}
