package inf112.balmerspeak.app.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.balmerspeak.app.GUI;
import inf112.balmerspeak.app.menu.Screens;


public class MenuScreen extends MainScreen implements Screen {
    // Game object
    private GUI game;

    // Quit button
    private TextButton quitButton;


    public MenuScreen(GUI game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();

        // Get stage and root table
        Skin skin = super.getSkin();
        Table root = super.getRoot();

        // Get buttons labels
        Label playLabel = super.getBtnLabel("Play");
        Label optionsLabel = super.getBtnLabel("Options");
        Label rulesLabel = super.getBtnLabel("Rules");
        Label quitLabel = super.getBtnLabel("Quit");


        // Init buttons and listeners
        // Play button
        TextButton playButton = new TextButton("Play", skin);
        playButton.setLabel(playLabel);
        addNavigationButtonListeners(playButton, game, Screens.START);

        // Options button
        TextButton optionsButton = new TextButton("Options", skin);
        optionsButton.setLabel(optionsLabel);
        addNavigationButtonListeners(optionsButton, game, Screens.OPTIONS);

        // Rules button
        TextButton rulesButton = new TextButton("Rules", skin);
        rulesButton.setLabel(rulesLabel);
        addNavigationButtonListeners(rulesButton, game, Screens.RULES);

        quitButton = new TextButton("Quit", skin);
        quitButton.setLabel(quitLabel);
        // Custom function as quit button is not a navigation button
        addQuitListener();
        addHoverListeners(quitButton, game);


        // Add title and buttons to root table
        super.addTitle(250.0f);
        root.add(playButton).prefWidth(200.0f).prefHeight(100.0f);
        root.row();
        root.add(optionsButton).prefWidth(200.0f).prefHeight(100.0f);
        root.row();
        root.add(rulesButton).prefWidth(200.0f).prefHeight(100.0f);
        root.row();
        root.add(quitButton).prefWidth(200.0f).prefHeight(100.0f);
    }

    private void addQuitListener() {
        // Add click listener to quit button
        quitButton.addListener(new ClickListener() {
            // Go to game screen when clicked
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.exit();
            }
        });
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
