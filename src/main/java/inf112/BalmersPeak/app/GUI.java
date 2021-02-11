package inf112.BalmersPeak.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;


public class GUI implements ApplicationListener {

    private SpriteBatch batch;
    private BitmapFont font;
    private OrthogonalTiledMapRenderer rend;
    private OrthographicCamera cam;

    private Vector2 playerVec;

    private InputHandler input;
    private MapHandler mapHandler;


    @Override
    public void create() {
        // Create input handler
        input = new InputHandler();
        Gdx.input.setInputProcessor(input);

        // Create map handler
        mapHandler = new MapHandler();

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);



        playerVec = new Vector2(0, 0);

        cam = new OrthographicCamera();
        rend = new OrthogonalTiledMapRenderer(mapHandler.getMap(), (float) 1 / 300);

        cam.setToOrtho(false, 16, 12);
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
        cam.update();




        rend.setView(cam);
    }

    public boolean shouldMove(int dx, int dy) {
        // Return false if dx and dy are zero
        if (dx == 0 && dy == 0)
            return false;

        // These return true if the resulting playerVec are out of bounds
        boolean outsideX = playerVec.x + dx > mapHandler.getBoard().getWidth()-1 || playerVec.x + dx < 0;
        boolean outsideY = playerVec.y + dy > mapHandler.getBoard().getHeight()-1 || playerVec.y + dy < 0;

        return !(outsideX || outsideY);
    }

    public void checkInput(int playerX, int playerY) {
        // Changes in the x coordinate
        int dx = 0;
        // Changes in the y coordinate
        int dy = 0;

        if (input.wPressed)
            dy += 1;
        else if (input.aPressed)
            dx -= 1;
        else if(input.sPressed)
            dy -=1;
        else if (input.dPressed)
            dx += 1;

        // Only update if the player is allowed to move
        if (shouldMove(dx, dy)) {
            // Move player textures
            mapHandler.movePlayer(playerX, playerY, dx, dy);

            // Update player coordinates
            playerVec.set(playerX + dx, playerY + dy);
        }
        // TODO: Temporary fix below, render method is called 4-5 times before InputHandler is able to change the keypress bools
        input.clear();
    }


    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {

        // Get player coordinates as ints
        int playerX = (int) playerVec.x;
        int playerY = (int) playerVec.y;


        // Check input and move character
        checkInput(playerX, playerY);

        // Clear screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);


        // Check if player has won
        if (mapHandler.checkWin(playerX, playerY)) {
            System.out.println("You won!");
            //mapHandler.changePlayerTextureWin(playerX, playerY);
            mapHandler.getPlayerLayer().setCell(playerX, playerY, mapHandler.wonCell);
        }

        // Check if player died
        if (mapHandler.checkDeath(playerX, playerY)) {
            System.out.println("You died :(");
            mapHandler.changePlayerTextureDeath(playerX, playerY);
        }
        rend.render();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}
