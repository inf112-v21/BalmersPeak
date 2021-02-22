package inf112.balmerspeak.app.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.balmerspeak.app.GUI;

public class OptionsScreen extends MainScreen implements Screen {

    // Game object
    private GUI game;

    // Components and buttons for the volume
    private Button plusButton;
    private Button minusButton;
    private ProgressBar volumeBar;

    // Fullscreen and back button
    private TextButton toggleFullscreen;


    public OptionsScreen(GUI game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();

        // Get root table
        Table root = super.getRoot();

        // Get skin
        Skin skin = super.getSkin();

        // Get back to menu and fullscreen button label
        Label backBtnLabel = super.getBtnLabel("Back");
        Label fullscreenBtnLabel = super.getBtnLabel("Toggle Fullscreen");

        // Get title
        Label title = super.getTitleLabel("Robo Rally");

        // Volume title
        // Volume label
        Label volumeTitle = super.getTitleLabel("Volume");

        // Add volume buttons and progress bar
        plusButton = new Button(skin.getDrawable("plus"));
        minusButton = new Button(skin.getDrawable("minus"));

        // Add volume progress bar
        volumeBar = new ProgressBar(0, 10, 1, false, skin);
        volumeBar.setValue(5);

        // Add fullscreen button
        toggleFullscreen = new TextButton("Toggle Fullscreen", skin);
        toggleFullscreen.setLabel(fullscreenBtnLabel);
        addHoverListeners(toggleFullscreen, game);
        addFullscreenClickListener();


        // Add back to menu button
        TextButton backToMenu = new TextButton("Back", skin);
        backToMenu.setLabel(backBtnLabel);
        addNavigationButtonListeners(backToMenu, game, Screens.MENU);


        // Add game title
        root.add(title).padBottom(250.0f).colspan(3).center();
        root.row();
        // Add volume title
        root.add(volumeTitle).colspan(3).center();
        root.row();
        // Add volume bar and controls
        root.add(minusButton).size(20,20).padRight(20.0f);
        root.add(volumeBar).size(400, 100);
        root.add(plusButton).size(20,20).padLeft(20.0f);

        // Add fullscreen button
        root.row();
        root.add(toggleFullscreen).colspan(3).center().padBottom(40.0f);

        // Add back to main menu button
        root.row();
        root.add(backToMenu).colspan(3).center();

        // Add listeners
        addVolumeListeners();
    }

    public void addVolumeListeners() {
        // Add listener to minus button
        minusButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // Decrease volume
                game.volume.decreaseVolume();
                // Decrement volume bar
                volumeBar.setValue(volumeBar.getValue() - 1);
            }
        });

        // Add listener to plus button
        plusButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // Increase volume
                game.volume.increaseVolume();
                // Increment volume bar
                volumeBar.setValue(volumeBar.getValue() + 1);
            }
        });
    }

    public void addFullscreenClickListener() {
        toggleFullscreen.addListener(new ClickListener() {
            boolean fullscreen = false;

            // Go fullscreen when clicked
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (fullscreen) {
                    Gdx.graphics.setWindowedMode(1920,1080);
                } else {
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                    fullscreen = true;
                }
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
