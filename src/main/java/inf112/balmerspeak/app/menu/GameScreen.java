package inf112.balmerspeak.app.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.balmerspeak.app.InputHandler;
import inf112.balmerspeak.app.MapHandler;
import inf112.balmerspeak.app.cards.CardGetter;
import inf112.balmerspeak.app.cards.MovementCard;
import inf112.balmerspeak.app.cards.ProgramCard;
import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;

import java.util.ArrayList;

public class GameScreen implements Screen {

    Stage stage;
    Skin skin;
    // Background image
    private Texture backgroundImage;

    private OrthogonalTiledMapRenderer rend;

    private static CardGetter getter = new CardGetter();
    private ArrayList<ArrayList<ProgramCard>> hand = new ArrayList<>();
    private UserInterface ui;

    private InputHandler input;
    private MapHandler mapHandler;
    OrthographicCamera cam;

    private Vector2 playerVec;

    private Robot robot;

    public GameScreen() {
        playerVec = new Vector2(0, 0);
        robot = new Robot(0,0, Direction.NORTH);

        // Create input handler
        input = new InputHandler();
        Gdx.input.setInputProcessor(input);

        // Create map handler
        mapHandler = new MapHandler();

        cam = new OrthographicCamera();
        rend = new OrthogonalTiledMapRenderer(mapHandler.getMap(), (float) 1 / 300);

        cam.setToOrtho(false, 16, 16);
        cam.position.set((cam.viewportWidth / 2), (cam.viewportHeight / 2) - 4, 0);
        cam.update();




        rend.setView(cam);
        MovementCard card = new MovementCard(1,2,"move 1");
        handleMoveCard(card);

    }

    public boolean shouldMove(int dx, int dy) {
        // Return false if dx and dy are zero
        if (dx == 0 && dy == 0)
            return false;

        // These return true if the resulting playerVec are out of bounds
        boolean outsideX = robot.getX() + dx > mapHandler.getBoard().getWidth()-1 || robot.getX() + dx < 0;
        boolean outsideY = robot.getY() + dy > mapHandler.getBoard().getHeight()-1 || robot.getY() + dy < 0;

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
        int playerX = robot.getX();
        int playerY = robot.getY();


        // Only update if the player is allowed to move
        if (shouldMove(dx, dy)) {
            // Move player textures
            mapHandler.movePlayer(playerX, playerY, dx, dy);
            robot.set(playerX + dx, playerY + dy);
        }

        // Check if player won
        if (mapHandler.checkWin(playerX + dx, playerY + dy)) {
            System.out.println("You won!");
            mapHandler.changePlayerTextureWin(playerX + dx, playerY + dy);
            robot.set(playerX + dx, playerY + dy);
        }

        // Check if player died
        if (mapHandler.checkDeath(playerX + dx, playerY + dy)) {
            System.out.println("You died :(");
            mapHandler.changePlayerTextureDeath(playerX + dx, playerY + dy);
            robot.set(playerX + dx, playerY + dy);
        }

        // Update player coordinates
        input.clear();
    }

    public void handleMoveCard(MovementCard card){
        int dx = 0;
        int dy = 0;


        if (robot.getDirection().equals(Direction.NORTH))
            dx += card.getDistance();
        else if (robot.getDirection().equals(Direction.SOUTH))
            dx -= card.getDistance();
        else if (robot.getDirection().equals(Direction.EAST))
            dy -= card.getDistance();
        else if (robot.getDirection().equals(Direction.WEST))
            dy += card.getDistance();

        int playerX = robot.getX();
        int playerY = robot.getY();

        if (shouldMove(dx, dy)){
            mapHandler.movePlayer(playerX, playerY, dx, dy);
            robot.set(playerX + dx, playerY + dy);
        }

    }


    @Override
    public void show() {
        // Called when this screen becomes the current screen for the Game.
        stage = new Stage(new ScreenViewport());
        Table register = new Table();
        register.setHeight(270);
        register.setWidth(Gdx.graphics.getWidth());
        register.bottom().debug();
        stage.addActor(register);

        hand = getter.getCardList();

        //this.ui = new UserInterface(this, stage);


    }

    private void updateCardTable() {

    }

    @Override
    public void render(float v) {
        rend.render();

        // Check input and move character
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        handleMove();

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
