package inf112.balmerspeak.app.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.balmerspeak.app.Game;
import inf112.balmerspeak.app.InputHandler;
import inf112.balmerspeak.app.Player;
import inf112.balmerspeak.app.board.Board;
import inf112.balmerspeak.app.cards.*;
import inf112.balmerspeak.app.robot.Direction;
import java.util.ArrayList;



public class GameScreen implements Screen {

    private Stage stage;
    private OrthogonalTiledMapRenderer rend;
    private InputHandler input;
    private OrthographicCamera cam;

    private ArrayList<ProgramCard> queueList = new ArrayList<>();

    private Player myPlayer;
    private Game game;


    private Skin skin1;
    private Texture card;
    private Texture backgroundImage;
    private Texture life;
    private Texture health;
    Board board;




    public GameScreen() {


        // Create input handler
        input = new InputHandler();
        Gdx.input.setInputProcessor(input);


        //load skins
        skin1 = new Skin(Gdx.files.internal("assets/default/skin/uiskin.json"));

        // Create map handler
        board = new Board("assets/map/map.tmx");
        //mapHandler = new MapHandler();

        cam = new OrthographicCamera();
        rend = new OrthogonalTiledMapRenderer(board.getMap(), (float) 1 / 300);

        cam.setToOrtho(false, 16, 16);
        cam.position.set((cam.viewportWidth / 2), (cam.viewportHeight / 2) - 4, 0);
        cam.update();

        rend.setView(cam);
    }

    public Board getBoard() {
        return this.board;
    }


    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setMyPlayer(Player myPlayer) {
        this.myPlayer = myPlayer;
    }

    public Player getMyPlayer() {
        return this.myPlayer;
    }

    public boolean shouldMove(int dx, int dy) {
        // Return false if dx and dy are zero
//        if (dx == 0 && dy == 0)
//            return false;
//
//        // These return true if the resulting playerVec are out of bounds
//        boolean outsideX = board.getActivePlayer().getX() + dx > board.getBoard().getWidth()-1 || board.getActivePlayer().getX() + dx < 0;
//        boolean outsideY = board.getActivePlayer().getY() + dy > board.getBoard().getHeight()-1 || board.getActivePlayer().getY() + dy < 0;
//        boolean isPlayer = board.getRobot(board.getActivePlayer().getX() + dx, board.getActivePlayer().getY() + dy) != null;
//
//        return !(outsideX || outsideY || isPlayer);
        // TODO: commented out for testing purposes
        return true;
    }


    public void executeCard(GeneralCard card, Player player) {
        // Call appropriate method
        if (card instanceof  MovementCard)
            handleMoveCard((MovementCard) card, player);
        else
            handleRotation((RotationCard) card, player);
    }

    public void handleMoveCard(MovementCard card, Player player){
        // Changes in the x coordinate
        int dx = 0;

        // Changes in the y coordinate
        int dy = 0;

        //Check for direction
        if (player.getRobot().getDirection().equals(Direction.NORTH))
            dy += card.getDistance();
        else if (player.getRobot().getDirection().equals(Direction.SOUTH))
            dy -= card.getDistance();
        else if (player.getRobot().getDirection().equals(Direction.EAST))
            dx += card.getDistance();
        else if (player.getRobot().getDirection().equals(Direction.WEST))
            dx -= card.getDistance();

        // Player x and y coordinates
        int playerX = player.getRobot().getX();
        int playerY = player.getRobot().getY();

        // Only update if the player is allowed to move
        if (shouldMove(dx, dy)){
            board.move(player, dx,dy);
            player.getRobot().set(playerX + dx, playerY + dy);
        }
        if (board.getHole(playerX + dx, playerY + dy) != null){
            player.getRobot().setLives(player.getRobot().getLives()-1);
            show();
        }
        if (board.getLaser(playerX + dx, playerY + dy) != null){
            player.getRobot().setHealth(player.getRobot().getHealth()-1);
            show();
        }
    }

    //Handles rotation cards
    public void handleRotation(RotationCard card, Player player){
        if (card.getRotation().equals(Rotation.left)) {
            player.getRobot().setDirection(player.getRobot().turn(Rotation.left, player.getRobot().getDirection()));
            board.rotateRobot(player, 90);
        } else if (card.getRotation().equals(Rotation.right)) {
            player.getRobot().setDirection(player.getRobot().turn(Rotation.right, player.getRobot().getDirection()));
            board.rotateRobot(player, -90);
        } else if (card.getRotation().equals(Rotation.uturn)) {
            player.getRobot().setDirection(player.getRobot().turn(Rotation.uturn, player.getRobot().getDirection()));
            board.rotateRobot(player, 180);
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
                // block action if round is in progress
                if (!game.isRoundInProgress())
                    game.handIsReady(queueList);
                show();
            }
        });

        //Adds the cards to the GUI
        int x = 100;
        for (ProgramCard cards : myPlayer.getHand()) {
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
        int xlife = 1300;
        for (int i = 0; i < myPlayer.getRobot().getLives(); i++) {
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
        for (int i = 0; i < myPlayer.getRobot().getHealth(); i++) {
            health = new Texture("images/health_token.png");
            Button.ButtonStyle tbs = new Button.ButtonStyle();
            tbs.up = new TextureRegionDrawable(new TextureRegion(health));
            Button b = new Button(tbs);
            b.setPosition(xhealth+=50, 50);
            b.setSize(50,50);
            stage.addActor(b);
        }

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
