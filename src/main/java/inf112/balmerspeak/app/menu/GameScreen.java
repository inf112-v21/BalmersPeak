package inf112.balmerspeak.app.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.balmerspeak.app.InputHandler;
import inf112.balmerspeak.app.MapHandler;
import inf112.balmerspeak.app.board.Board;
import inf112.balmerspeak.app.board.Walls;
import inf112.balmerspeak.app.cards.*;
import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;

import javax.swing.*;
import java.util.ArrayList;



public class GameScreen implements Screen {

    private Stage stage;
    // Background image

    private OrthogonalTiledMapRenderer rend;


    private ArrayList<ProgramCard> hand;

    private InputHandler input;
    private MapHandler mapHandler;
    private OrthographicCamera cam;

    private ArrayList<ProgramCard> queueList = new ArrayList<>();

    private Robot robot;

    private Skin skin1;

    private Texture card;

    private Texture backgroundImage;
    private Texture life;
    private Texture health;
    Board board;
    int turn = 0;

    public GameScreen() {

        // Create input handler
        input = new InputHandler();
        Gdx.input.setInputProcessor(input);


        //load skins
        skin1 = new Skin(Gdx.files.internal("assets/default/skin/uiskin.json"));
        hand = new ArrayList<>();

        // Create map handler
        board = new Board("assets/map/map.tmx");
        //mapHandler = new MapHandler();

        cam = new OrthographicCamera();
        rend = new OrthogonalTiledMapRenderer(board.getMap(), (float) 1 / 300);

        cam.setToOrtho(false, 16, 16);
        cam.position.set((cam.viewportWidth / 2), (cam.viewportHeight / 2) - 4, 0);
        cam.update();

        rend.setView(cam);

        for (Robot robots : board.getPlayers()) {
            board.placeRobot(robots.getX(), robots.getY());
            robots.giveHand(9);
        }
    }

    public Robot getRobot() {
        return robot;
    }

    public boolean shouldMove(int dx, int dy) {
        // Return false if dx and dy are zero
        if (dx == 0 && dy == 0)
            return false;

        if ((board.getWalls(board.getActivePlayer().getX(), board.getActivePlayer().getY()) != null) && (board.getActivePlayer().getDirection() == board.getWalls(board.getActivePlayer().getX(),board.getActivePlayer().getY()).getDirection())) {
            return false;
        }
        if ((board.getWalls(board.getActivePlayer().getX()+dx, board.getActivePlayer().getY()+dy) != null) && (board.getActivePlayer().uTurn() == board.getWalls(board.getActivePlayer().getX()+dx,board.getActivePlayer().getY()+dy).getDirection())) {
            return false;
        }

        if ((board.getLaser(board.getActivePlayer().getX(), board.getActivePlayer().getY()) != null) && (board.getActivePlayer().getDirection() == board.getLaser(board.getActivePlayer().getX(),board.getActivePlayer().getY()).getDirection())) {
            return false;
        }
        if ((board.getLaser(board.getActivePlayer().getX()+dx, board.getActivePlayer().getY()+dy) != null) && (board.getActivePlayer().uTurn() == board.getLaser(board.getActivePlayer().getX()+dx,board.getActivePlayer().getY()+dy).getDirection())) {
            return false;
        }

        // These return true if the resulting playerVec are out of bounds
        boolean outsideX = board.getActivePlayer().getX() + dx > board.getBoard().getWidth()-1 || board.getActivePlayer().getX() + dx < 0;
        boolean outsideY = board.getActivePlayer().getY() + dy > board.getBoard().getHeight()-1 || board.getActivePlayer().getY() + dy < 0;

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
        int playerX = board.getActivePlayer().getX();
        int playerY = board.getActivePlayer().getY();

        // Only update if the player is allowed to move
        if (shouldMove(dx, dy)) {
            board.move(playerX, playerY, dx, dy);
            board.getActivePlayer().set(playerX + dx, playerY + dy);

            if (board.getHole(playerX + dx, playerY + dy) != null) {
                board.getActivePlayer().setLives(board.getActivePlayer().getLives() - 1);
                System.out.println("lost life");
                show();
            }
            if (board.getLaser(playerX + dx, playerY + dy) != null) {
                board.getActivePlayer().setHealth(board.getActivePlayer().getHealth() - 1);
                System.out.println("Lost health");
                show();
            }
            if (board.getFlag(playerX + dx, playerY + dy) != null) {
                board.getActivePlayer().addFlag(board.getFlag(playerX + dx, playerY + dy));
            }
            if (board.getActivePlayer().checkWinCondition())
                System.out.println("Player" + board.getActivePlayer() + " won");

            // Check if player died
            if (shouldMove(dx, dy)) {
                board.getActivePlayer().set(playerX + dx, playerY + dy);
                board.move(playerX, playerY, dx, dy);
            }
            if (board.getHole(playerX + dx, playerY + dy) != null) {
                board.getActivePlayer().setLives(board.getActivePlayer().getLives() - 1);
                show();
            }
            if (board.getLaser(playerX + dx, playerY + dy) != null) {
                board.getActivePlayer().setHealth(board.getActivePlayer().getHealth() - 1);
                show();
            }


            // Update player coordinates
            input.clear();
        }
    }

    public void winFrame(){
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, "Player " + board.getActivePlayer().toString() + " won");
    }

    public void handleMoveCard(MovementCard card) {
        // Changes in the x coordinate
        int dx = 0;

        // Changes in the y coordinate
        int dy = 0;

        //Check for direction
        if (board.getActivePlayer().getDirection().equals(Direction.NORTH))
            dy += card.getDistance();
        else if (board.getActivePlayer().getDirection().equals(Direction.SOUTH))
            dy -= card.getDistance();
        else if (board.getActivePlayer().getDirection().equals(Direction.EAST))
            dx += card.getDistance();
        else if (board.getActivePlayer().getDirection().equals(Direction.WEST))
            dx -= card.getDistance();

        // Player x and y coordinates
        int playerX = board.getActivePlayer().getX();
        int playerY = board.getActivePlayer().getY();

        // Only update if the player is allowed to move
        if (shouldMove(dx, dy)){
            board.getActivePlayer().set(playerX + dx, playerY + dy);
            board.move(playerX,playerY, dx,dy);
            board.getActivePlayer().set(playerX + dx, playerY + dy);

            if (board.getHole(playerX + dx, playerY + dy) != null) {
                board.getActivePlayer().setLives(board.getActivePlayer().getLives() - 1);
                showHealthLives();
            }
            if (board.getLaser(playerX + dx, playerY + dy) != null) {
                board.getActivePlayer().setHealth(board.getActivePlayer().getHealth() - 1);
                showHealthLives();
            }
            if (board.getFlag(playerX + dx, playerY + dy) != null){
                board.getActivePlayer().addFlag(board.getFlag(playerX +dx, playerY+dy));
            }
            if (board.getActivePlayer().checkWinCondition())
                System.out.println("Player" + board.getActivePlayer() + " won");

        }

    }

    //Handles rotation cards
    public void handleRotation(RotationCard card){
        if (card.getRotation().equals(Rotation.left))
            board.getActivePlayer().setDirection(board.getActivePlayer().turn(Rotation.left, board.getActivePlayer().getDirection()));
        if (card.getRotation().equals(Rotation.right))
            board.getActivePlayer().setDirection(board.getActivePlayer().turn(Rotation.right, board.getActivePlayer().getDirection()));
        if (card.getRotation().equals(Rotation.uturn))
            board.getActivePlayer().setDirection(board.getActivePlayer().turn(Rotation.uturn, board.getActivePlayer().getDirection()));
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

    public void showHealthLives() {
        int xlife = 1300;
        for (int i = 0; i < board.getActivePlayer().getLives(); i++) {
            life = new Texture("images/lifetoken.png");
            Button.ButtonStyle tbs = new Button.ButtonStyle();
            tbs.up = new TextureRegionDrawable(new TextureRegion(life));
            Button b = new Button(tbs);
            b.setPosition(xlife+=100, 150);
            b.setSize(50,50);
            stage.addActor(b);

        }

        //Adds the health tokes to the GUI
        int xhealth = 1250;
        for (int i = 0; i < board.getActivePlayer().getHealth(); i++) {
            health = new Texture("images/health_token.png");
            Button.ButtonStyle tbs = new Button.ButtonStyle();
            tbs.up = new TextureRegionDrawable(new TextureRegion(health));
            Button b = new Button(tbs);
            b.setPosition(xhealth+=50, 50);
            b.setSize(50,50);
            stage.addActor(b);
        }
    }

    @Override
    public void show() {
        // Called when this screen becomes the current screen for the Game.
        stage = new Stage(new ScreenViewport());
        Table register = new Table();
        register.setHeight(270);
        register.setWidth(Gdx.graphics.getWidth());

        //add the button to start the sequence of moves
        TextButton button = new TextButton("Start round", skin1);
        button.setPosition(1100,100);
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

        //Adds the cards to the GUI
        int x = 100;
        for (ProgramCard cards : board.getActivePlayer().getHand()) {
            card = new Texture("assets/images/cards/" + cards.toString() + ".png");
            Button.ButtonStyle tbs = new Button.ButtonStyle();
            tbs.up = new TextureRegionDrawable(new TextureRegion(card));

            Button b = new Button(tbs);
            b.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent changeEvent, Actor actor) {
                    if(!queueList.contains(cards) && queueList.size()<5) {
                        queueList.add(cards);
                    }
                    else {
                        queueList.remove(cards);
                    }
                }
            });
            b.setPosition(x+=100, 50);

            stage.addActor(b);
        }

        //Adds the life tokens to the GUI
        showHealthLives();

        //Adds text field for lives
        TextField life = new TextField("Lives", skin1);
        life.setPosition(1500, 210);
        life.setSize(50,life.getHeight());

        //Adds text field for health
        TextField health = new TextField("Health", skin1);
        health.setPosition(1495, 110);
        health.setSize(60,health.getHeight());

        stage.addActor(life);
        stage.addActor(health);



        backgroundImage = new Texture("images/background.png");

        stage.addActor(button);
        stage.addActor(register);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        rend.render();

        //Adds the queue list to the GUI
        TextField field = new TextField("Queue: " + queueList, skin1);
        field.setPosition(Gdx.graphics.getWidth()/4, 200);
        field.setSize(queueList.size()+400, field.getHeight());
        stage.addActor(field);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundImage, 0, 0, stage.getWidth(), 270);
        stage.getBatch().end();
        stage.draw();
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
