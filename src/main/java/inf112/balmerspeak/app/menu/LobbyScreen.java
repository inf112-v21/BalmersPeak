package inf112.balmerspeak.app.menu;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import inf112.balmerspeak.app.GUI;


public class LobbyScreen extends MainScreen implements Screen {

    // Game object
    private GUI game;


    public LobbyScreen(GUI game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();

        // Get root table
        Table root = super.getRoot();

        // Get skin
        Skin skin = super.getSkin();

        // Get buttons label
        Label backBtnLabel = super.getBtnLabel("Back");

        // Add IP label
        Label ipLabel = super.getBtnLabel("Game IP: 192.186.19"); //TODO: make this dynamic

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
        root.add(connectedLbl).padBottom(50.0f);
        root.row();

        // TODO: Some function to add connected clients programmatically here

        root.add(backToMenu).prefWidth(200.0f).prefHeight(100.0f);
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
