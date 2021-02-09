package inf112.BalmersPeak.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import org.lwjgl.system.CallbackI;

import javax.swing.*;
import java.awt.*;

public class GUI implements ApplicationListener {

    private SpriteBatch batch;
    private BitmapFont font;
    private TiledMap map;
    private TiledMapTileLayer board;
    private TiledMapTileLayer hole;
    private TiledMapTileLayer flag;
    private TiledMapTileLayer playerLayer;
    private OrthogonalTiledMapRenderer rend;
    private OrthographicCamera cam;
    private TiledMapTileLayer.Cell player;
    private TiledMapTileLayer.Cell wonCell;
    private TiledMapTileLayer.Cell dieCell;
    private TiledMapTileLayer.Cell defaultCell;
    private Vector2 playerVec;

    private InputHandler input;


    @Override
    public void create() {
        input = new InputHandler();
        Gdx.input.setInputProcessor(input);
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        map = new TmxMapLoader().load("assets/example.tmx");
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");
        board = (TiledMapTileLayer) map.getLayers().get("Board");
        hole = (TiledMapTileLayer) map.getLayers().get("Hole");
        flag = (TiledMapTileLayer) map.getLayers().get("Flag");

        Texture playerTexture = new Texture("assets/player.png");
        TextureRegion[][] texRegion = TextureRegion.split(playerTexture, 300, 300);

        Texture boardTexture = new Texture("assets/standard-300dpi.png");
        TextureRegion[][] boardRegion = TextureRegion.split(boardTexture, 300, 300);

        StaticTiledMapTile defaultTileTexture = new StaticTiledMapTile(boardRegion[12][1]);
        StaticTiledMapTile normalPlayerTexture = new StaticTiledMapTile(texRegion[0][0]);
        StaticTiledMapTile playerDiedTexture = new StaticTiledMapTile(texRegion[0][1]);
        StaticTiledMapTile playerWonTexture = new StaticTiledMapTile(texRegion[0][2]);
        player = new TiledMapTileLayer.Cell().setTile(normalPlayerTexture);
        wonCell = new TiledMapTileLayer.Cell().setTile(playerWonTexture);
        dieCell = new TiledMapTileLayer.Cell().setTile(playerDiedTexture);
        defaultCell = new TiledMapTileLayer.Cell().setTile(defaultTileTexture);
        playerVec = new Vector2(0, 0);

        cam = new OrthographicCamera();
        rend = new OrthogonalTiledMapRenderer(map, (float) 1 / 300);

        cam.setToOrtho(false, 5, 5);
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
        cam.update();

        playerLayer.setCell(0, 0, player);


        rend.setView(cam);
    }

    public void checkOutOfBounds() {
        if (playerVec.x > board.getWidth() - 2) {
            playerVec.x = board.getWidth() - 2;
        } else if (playerVec.y > board.getHeight() - 2) {
            playerVec.y = board.getHeight() - 2;
        }
    }


    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public void checkInput() {
        int dx = 0;
        int dy = 0;

        if (input.wPressed) {
            dy += 1;
        } else if (input.aPressed) {
            dx -= 1;
        } else if(input.sPressed) {
            dy -=1;
        } else if (input.dPressed) {
            dx += 1;
        }
        if (dx != 0 || dy != 0) {
            // TODO: Fix float issue here
            playerLayer.setCell((int) playerVec.x + dx, (int) playerVec.y + dy, player);
            playerLayer.setCell((int) playerVec.x, (int) playerVec.y, defaultCell);
            playerVec.set(playerVec.x + dx, playerVec.y + dy);
        }
        // Temporary fix, render method is faster than InputHandler is able to set bools
        input.clear();
    }

    @Override
    public void render() {
        // Check input and move character
        checkInput();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        if(flag.getCell((int) playerVec.x,(int) playerVec.y) != null) {
            playerLayer.setCell((int) playerVec.x,(int) playerVec.y, wonCell);
            System.out.println("you win");
        }
        if(hole.getCell((int) playerVec.x, (int) playerVec.y) != null){
            playerLayer.setCell((int) playerVec.x, (int) playerVec.y, dieCell);
            System.out.println("you died");
        }
        rend.render();

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
