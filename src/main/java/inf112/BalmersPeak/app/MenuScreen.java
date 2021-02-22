package inf112.balmerspeak.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class MenuScreen extends MainScreen implements Screen {
    // Game object
    private GUI game;

    // Play button
    TextButton playButton;
    // Options button
    TextButton optionsButton;
    // Quit button
    TextButton quitButton;
    // Rules button
    TextButton rulesButton;


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
        playButton = new TextButton("Play", skin);
        playButton.setLabel(playLabel);
        addNavigationButtonListeners(playButton, game, Screens.GAME);

        optionsButton = new TextButton("Options", skin);
        optionsButton.setLabel(optionsLabel);
        addNavigationButtonListeners(optionsButton, game, Screens.OPTIONS);

        rulesButton = new TextButton("Rules", skin);
        rulesButton.setLabel(rulesLabel);
        addNavigationButtonListeners(rulesButton, game, Screens.RULES);

        quitButton = new TextButton("Quit", skin);
        quitButton.setLabel(quitLabel);
        // Custom function as quit button is not a navigation button
        addQuitListener();


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
        // Add listeners to quit button
        quitButton.addListener(new ClickListener() {
            boolean playing = false;

            // Play sound on hover
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                if (!playing && (fromActor == null || fromActor instanceof TextButton)) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/btn_hover.ogg"));
                    sound.play(1F);
                    playing = true;
                }
            }

            // Stop sound on exit
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                if (toActor == null || toActor instanceof TextButton)
                    playing = false;
            }

            // Go to game screen when clicked
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.exit(0);
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
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        super.dispose();
    }
}
