package inf112.balmerspeak.app;

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

        OrthographicCamera cam = new OrthographicCamera();
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

    public void handleMove() {
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

        // Player x and y coordinates
        int playerX = (int) playerVec.x;
        int playerY = (int) playerVec.y;


        // Only update if the player is allowed to move
        if (shouldMove(dx, dy)) {
            // Move player textures
            mapHandler.movePlayer(playerX, playerY, dx, dy);
            playerVec.set(playerX + dx, playerY + dy);
        }

        // Check if player won
        if (mapHandler.checkWin(playerX + dx, playerY + dy)) {
            System.out.println("You won!");
            mapHandler.changePlayerTextureWin(playerX + dx, playerY + dy);
            playerVec.set(playerX + dx, playerY + dy);
        }

        // Check if player died
        if (mapHandler.checkDeath(playerX + dx, playerY + dy)) {
            System.out.println("You died :(");
            mapHandler.changePlayerTextureDeath(playerX + dx, playerY + dy);
            playerVec.set(playerX + dx, playerY + dy);
        }


        // Update player coordinates
        input.clear();
    }


    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {

        // Check input and move character
        handleMove();

        // Clear screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        rend.render();
    }



    /**
     * Called when the Application is resized. Can happen at any point during non-paused state, but never during create()
     * @param width - the new width in pixels
     * @param height - the new height in pixels
     */
    @Override
    public void resize(int width, int height) {
        // The resize method.
    }

    /**
     * Called when the Application is paused, usually when it's not active or visible on-screen. Always paused before it's destroyed
     */
    @Override
    public void pause() {
        // The pause method.
    }

    /**
     * Called when the Application is resumed from a paused state, usually when it regains focus
     */
    @Override
    public void resume() {
        // The resume method.
    }
}
