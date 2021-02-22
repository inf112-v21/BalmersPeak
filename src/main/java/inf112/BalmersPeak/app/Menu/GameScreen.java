package inf112.balmerspeak.app.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.balmerspeak.app.GUI;
import inf112.balmerspeak.app.InputHandler;
import inf112.balmerspeak.app.MapHandler;

public class GameScreen implements Screen {

    private GUI game;
    private OrthogonalTiledMapRenderer rend;


    private InputHandler input;
    private MapHandler mapHandler;

    private Vector2 playerVec;

    public GameScreen(GUI game) {
        this.game = game;
        playerVec = new Vector2(0, 0);

        // Create input handler
        input = new InputHandler();
        Gdx.input.setInputProcessor(input);

        // Create map handler
        mapHandler = new MapHandler();


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
        }

        // Check if player won
        if (mapHandler.checkWin(playerX, playerY)) {
            System.out.println("You won!");
            mapHandler.changePlayerTextureWin(playerX + dx, playerY + dy);
        }

        // Check if player died
        if (mapHandler.checkDeath(playerX, playerY)) {
            System.out.println("You died :(");
            mapHandler.changePlayerTextureDeath(playerX + dx, playerY + dy);
        }

        // Update player coordinates
        playerVec.set(playerX + dx, playerY + dy);
        input.clear();
    }


    @Override
    public void show() {
        // Called when this screen becomes the current screen for the Game.
    }

    @Override
    public void render(float v) {
        // Check input and move character
        handleMove();
        rend.render();
    }

    @Override
    public void resize(int i, int i1) {
        // Called when the window resizes.
    }

    @Override
    public void pause() {
        // Called when this screen is not focus.
    }

    @Override
    public void resume() {
        // Called when game resumes, and this screen is focus.
    }

    @Override
    public void hide() {
        // Called when this screen is no longer the current screen for the Game.
    }

    @Override
    public void dispose() {
        // Called when this screen should release all resources.
    }
}
