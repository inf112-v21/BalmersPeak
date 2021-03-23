package inf112.balmerspeak.app.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.balmerspeak.app.GUI;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LobbyScreen extends MainScreen implements Screen {

    // Game object
    private GUI game;

    // Whether the person is host of the game
    private final boolean isHost;

    // ip address
    private String ipAddress;


    // table of connected clients
    private Table connectedClients;
    private Map<String,Label> clientsLabels = new HashMap<>();

    // skin
    public Skin skin;



    public String statusIP;

    public Label ipLabel;


    public LobbyScreen(GUI game, boolean isHost) {

        this.game = game;
        this.isHost = isHost;
        if (isHost) {
            ipAddress = game.server.getIPAddress();
        }
    }

    @Override
    public void show() {
        super.show();

        // Get root table
        Table root = super.getRoot();

        // Clients table
        connectedClients = new Table();

        // Get skin
        skin = super.getSkin();

        // Get buttons label
        Label backBtnLabel = super.getBtnLabel("Back");
        Label startGameLbl = super.getBtnLabel("Start game");

        // set status text if host
        if (isHost)
            statusIP = "You are hosting this game at " + ipAddress;

        // Add IP label
        ipLabel = super.getBtnLabel(statusIP);

        // Add connected label
        Label connectedLbl = super.getBtnLabel("Connected clients: ");

        // Get title
        Label title = super.getTitleLabel("Robo Rally");

        // Create back button
        TextButton backToMenu = new TextButton("Back", skin);
        backToMenu.setLabel(backBtnLabel);
        // Add navigation listener
        addNavListener(backToMenu);

        // Create play button
        TextButton startButton = new TextButton("Start game", skin);
        startButton.setLabel(startGameLbl);
        addHoverListeners(startButton, game);
        // only host should be able to start game
        if (isHost)
            addStartGameListener(startButton);



        // Add game title
        root.add(title).padBottom(200.0f).colspan(3).center();
        root.row();
        root.add(ipLabel).padBottom(50.0f);
        root.row();
        root.add(connectedLbl);
        root.row();
        root.add(connectedClients).prefWidth(400.0f).prefHeight(300.0f);
        root.row();
        root.add(backToMenu).prefWidth(200.0f).prefHeight(100.0f);
        // only host should see start button
        if (isHost)
            root.add(startButton).prefWidth(200.0f).prefHeight(100.0f);
    }

    public void startGame() {
        Gdx.app.postRunnable(() -> game.changeScreen(new GameScreen()));
    }

    public void addStartGameListener(TextButton btn) {
        // Go to game screen
        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // Send start message to clients
                game.server.sendStartMessage();
                game.changeScreen(new GameScreen());
            }
        });
    }

    public void addNavListener(TextButton btn) {
        super.addHoverListeners(btn, game);
        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // if host, stop the server
                if (isHost)
                    game.stopServer();
                else
                    game.stopClient();
                // Now navigate back to start screen
                game.changeScreen(new StartScreen(game));
            }
        });
    }



    public void setStatusLabel(String hostName, String hostIP) {
        this.statusIP = "You are connected to: " + hostName + " at " + hostIP;
    }


    public void addConnectedClient(String ipAddress, String username) {
        // Add label to list
        clientsLabels.put(ipAddress, new Label(username + " - " + ipAddress,skin));

        // Add to GUI
        connectedClients.row();
        connectedClients.add(clientsLabels.get(ipAddress));
    }

    public void removeConnectedClient(String ipAddress) {
        // Find the corresponding entry and remove it
        connectedClients.removeActor(clientsLabels.get(ipAddress));
        // Remove from label and ip map
        clientsLabels.remove(ipAddress);
    }


    @Override
    public void render(float v) {
        super.render(v);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
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
        super.dispose();
    }
}
