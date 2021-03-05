package inf112.balmerspeak.app.menu;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


import inf112.balmerspeak.app.GUI;


public class LobbyScreen extends MainScreen implements Screen {

    // Game object
    private GUI game;

    // Whether the person is host of the game
    private boolean isHost;

    // ip address
    private String ipAddress;



    // table of connected clients
    private Table connectedClients;

    // skin
    public Skin skin;

    private String hostName;

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


        // Decide title text
        statusIP = isHost ? "Your IP (host): "  + ipAddress : "You are connected to: " + hostName; // TODO: FETCH IP address from host


        // Add IP label
        ipLabel = super.getBtnLabel(statusIP);

        // Add connected label
        Label connectedLbl = super.getBtnLabel("Connected clients: ");

        // Get title
        Label title = super.getTitleLabel("Robo Rally");

        TextButton backToMenu = new TextButton("Back", skin);
        backToMenu.setLabel(backBtnLabel);


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
    }



    public void hostNameChanged(String hostName) {
        this.hostName = hostName;
        statusIP = "You are connected to: " + hostName;
    }


    public void addConnectedClient(String ipAddress, String username) {
        connectedClients.row();
        connectedClients.add(new Label(username + " - " + ipAddress, skin));
    }


    @Override
    public void render(float v) {
        super.render(v);
        ipLabel.setText(statusIP);
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
