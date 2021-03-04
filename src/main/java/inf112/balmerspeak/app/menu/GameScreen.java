package inf112.balmerspeak.app.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.balmerspeak.app.InputHandler;
import inf112.balmerspeak.app.MapHandler;
import inf112.balmerspeak.app.cards.*;
import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;
import java.util.ArrayList;

public class GameScreen implements Screen {

    Stage stage;
    // Background image
    private OrthogonalTiledMapRenderer rend;

    private ArrayList<ProgramCard> hand = new ArrayList<>();

    private InputHandler input;
    private MapHandler mapHandler;
    OrthographicCamera cam;

    ArrayList<ProgramCard> queueList = new ArrayList<>();

    private Robot robot;

    Skin skin1;

    public GameScreen() {

        // Create input handler
        input = new InputHandler();
        Gdx.input.setInputProcessor(input);

        //load skins
        skin1 = new Skin(Gdx.files.internal("assets/default/skin/uiskin.json"));

        // Create map handler
        mapHandler = new MapHandler();

        cam = new OrthographicCamera();
        rend = new OrthogonalTiledMapRenderer(mapHandler.getMap(), (float) 1 / 300);

        cam.setToOrtho(false, 16, 16);
        cam.position.set((cam.viewportWidth / 2), (cam.viewportHeight / 2) - 4, 0);
        cam.update();

        rend.setView(cam);

        //set player at (0,0)
        robot = new Robot(0,0, Direction.NORTH);
    }

    public Robot getRobot() {
        return robot;
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

    //Unused, but still usable for testing
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
        // Changes in the x coordinate
        int dx = 0;

        // Changes in the y coordinate
        int dy = 0;

        //Check for direction
        if (robot.getDirection().equals(Direction.NORTH))
            dy += card.getDistance();
        else if (robot.getDirection().equals(Direction.SOUTH))
            dy -= card.getDistance();
        else if (robot.getDirection().equals(Direction.EAST))
            dx += card.getDistance();
        else if (robot.getDirection().equals(Direction.WEST))
            dx -= card.getDistance();

        // Player x and y coordinates
        int playerX = robot.getX();
        int playerY = robot.getY();

        // Only update if the player is allowed to move
        if (shouldMove(dx, dy)){
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

    }
    public void handleRotation(RotationCard card){
        if (card.getRotation().equals(Rotation.left))
            robot.setDirection(turn(Rotation.left, robot.getDirection()));
        if (card.getRotation().equals(Rotation.right))
            robot.setDirection(turn(Rotation.right, robot.getDirection()));
        if (card.getRotation().equals(Rotation.uturn))
            robot.setDirection(turn(Rotation.uturn, robot.getDirection()));

    }
    public Direction turn(Rotation rotation, Direction direction) {
        switch (direction) {
            case NORTH:
                if (rotation.equals(Rotation.right)) return Direction.EAST;
                else if (rotation.equals(Rotation.uturn)) return Direction.SOUTH;
                else return Direction.WEST;
            case SOUTH:
                if (rotation.equals(Rotation.right)) return Direction.WEST;
                else if (rotation.equals(Rotation.uturn)) return Direction.NORTH;
                else return Direction.EAST;
            case WEST:
                if (rotation.equals(Rotation.right)) return Direction.NORTH;
                else if (rotation.equals(Rotation.uturn)) return Direction.EAST;
                else return Direction.SOUTH;
            case EAST:
                if (rotation.equals(Rotation.right)) return Direction.SOUTH;
                else if (rotation.equals(Rotation.uturn)) return Direction.WEST;
                else return Direction.NORTH;
            default:
                return null;
        }
    }


    @Override
    public void show() {
        // Called when this screen becomes the current screen for the Game.
        hand = robot.giveHand(5);
        stage = new Stage(new ScreenViewport());
        Table register = new Table();
        register.setHeight(270);
        register.setWidth(Gdx.graphics.getWidth());
        register.bottom().debug();


        //set up the menu box
        Dialog dialog = new Dialog("Card menu", skin1);
        dialog.setSize(Gdx.graphics.getWidth()/2, 160);
        dialog.setPosition(0,100);

        //add the drop down box
        SelectBox<ProgramCard> selectBox = new SelectBox<>(skin1);
        Array<ProgramCard> a = new Array<>();
        for (ProgramCard card : hand)
            a.add(card);
        selectBox.setItems(a);

        //add the button to start the sequence of moves
        TextButton button = new TextButton("Start round", skin1);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                //moves the robot for each card in list
                for (ProgramCard card : queueList){
                    if (card.getType().equals(MovementType.movement)) {
                        handleMoveCard((MovementCard) card);
                    }
                    else {
                        handleRotation((RotationCard) card);
                    }
                }
            }
        });

        selectBox.addListener(new ChangeListener() {
        @Override
        public void changed(ChangeEvent changeEvent, Actor actor) {
            if (queueList.size() < 5) {
                queueList.add(selectBox.getSelected());
            }
        }
        });
        dialog.getContentTable().defaults().pad(10);
        dialog.getContentTable().add(selectBox);
        dialog.add(button);

        stage.addActor(dialog);
        stage.addActor(register);
        Gdx.input.setInputProcessor(stage);

    }


    @Override
    public void render(float v) {
        rend.render();
        Label label = new Label("Queue: " + queueList, skin1);
        label.setPosition(Gdx.graphics.getWidth()/2+10, 200);
        stage.addActor(label);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        //handleMove();

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
