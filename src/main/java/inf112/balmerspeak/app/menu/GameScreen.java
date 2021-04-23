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

    private ArrayList<ProgramCard> queueList = new ArrayList<>();


    private Player myPlayer;
    private Game game;



    private Skin skin1;
    private Texture card;
    private Texture backgroundImage;
    private Texture life;
    private Texture health;
    Board board;
    InputHandler input;


    public GameScreen() {


        // Create input handler
        input = new InputHandler();
        Gdx.input.setInputProcessor(input);


        //load skins
        skin1 = new Skin(Gdx.files.internal("assets/default/skin/uiskin.json"));

        // Create board
        board = new Board("assets/map/map.tmx");

        OrthographicCamera cam = new OrthographicCamera();
        rend = new OrthogonalTiledMapRenderer(board.getMap(), (float) 1 / 300);

        cam.setToOrtho(false, 16, 16);
        cam.position.set((cam.viewportWidth / 2), (cam.viewportHeight / 2) - 4, 0);
        cam.update();

        rend.setView(cam);
    }

    public void clearQueuelist(){
        queueList.clear();
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

    public boolean shouldMove(Player player, int dx, int dy) {
        // Return false if dx and dy are zero
        if (dx == 0 && dy == 0)
            return false;

        if ((board.getWalls(player.getRobot().getX(), player.getRobot().getY()) != null) && (player.getRobot().getDirection() == board.getWalls(player.getRobot().getX(),player.getRobot().getY()).getDirection())) {
            return false;
        }
        if ((board.getWalls(player.getRobot().getX()+dx, player.getRobot().getY()+dy) != null) && (player.getRobot().uTurn() == board.getWalls(player.getRobot().getX()+dx,player.getRobot().getY()+dy).getDirection())) {
            return false;
        }

        if ((board.getLaser(player.getRobot().getX(), player.getRobot().getY()) != null) && (player.getRobot().getDirection() == board.getLaser(player.getRobot().getX(),player.getRobot().getY()).getDirection())) {
            return false;
        }
        if ((board.getLaser(player.getRobot().getX()+dx, player.getRobot().getY()+dy) != null) && (player.getRobot().uTurn() == board.getLaser(player.getRobot().getX()+dx,player.getRobot().getY()+dy).getDirection())) {
            return false;
        }

        int x = player.getRobot().getX();
        int y = player.getRobot().getY();


        // These return true if the resulting playerVec are out of bounds
        boolean outsideX = x + dx > board.getBoard().getWidth()-1 || x + dx < 0;
        boolean outsideY = y + dy > board.getBoard().getHeight()-1 || y + dy < 0;


        return !(outsideX || outsideY);

    }


    public void executeCard(GeneralCard card, Player player) {
        // Call appropriate method
        if (card instanceof  MovementCard)
            handleMoveCard((MovementCard) card, player);
        else
            handleRotation((RotationCard) card, player);
    }



    public void handleMoveCard(MovementCard card, Player player) {

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


        if (shouldMove(player, dx, dy)) {
            board.moveRobot(player, dx, dy);
            player.getRobot().set(playerX + dx, playerY + dy);

        }
    }


    //Handles rotation cards
    public void handleRotation(RotationCard card, Player player){
        player.getRobot().setDirection(player.getRobot().turn(card.getRotation(), player.getRobot().getDirection()));
        board.rotateRobot(player, player.getRobot().getDirection());
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
                System.out.println("Is round in progress: " + game.isRoundInProgress());
                System.out.println(queueList);
                if (!game.isRoundInProgress())
                    game.handIsReady(queueList);
            }
        });

        //Adds the cards to the GUI
        int x = 100;

        for (ProgramCard cards : myPlayer.getHand()) {
            card = new Texture("assets/cards/" + cards.getName() + "/" + cards.toString() + ".jpg");

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
            b.setSize(91,123);
            b.setPosition(x+=100, 50);
            stage.addActor(b);

        }
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
        field.setPosition(Gdx.graphics.getWidth()/5, 210);
        field.setSize(queueList.size()+600, field.getHeight());
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
