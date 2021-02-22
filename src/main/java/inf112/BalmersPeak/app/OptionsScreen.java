package inf112.balmerspeak.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class OptionsScreen extends MainScreen implements Screen {

    // Game object
    private GUI game;

    // Background image
    private Texture img;

    // Title label
    Label.LabelStyle titleStyle;
    Label title;

    // Button label
    Label.LabelStyle buttonStyle;

    // Volume label
    Label volumeTitle;

    // Components and buttons for the menu
    Button plusButton;
    Button minusButton;
    ProgressBar volumeBar;

    TextButton toggleFullscreen;
    TextButton backToMenu;

    // Table containing title and buttons
    Table root;

    // The quantum skin
    Skin skin;


    // Stage to add all components
    Stage stage;

    public OptionsScreen(GUI game) { this.game = game; }

    @Override
    public void show() {


        // Init stage
        stage = new Stage(new ScreenViewport());
        // Set stage as InputProcessor
        Gdx.input.setInputProcessor(stage);


        // Load background image
        img = new Texture("images/menubackground.jpg");

        // Init skin
        skin = new Skin(Gdx.files.internal("skins/quantum/skin/quantum-horizon-ui.json"));


        // Init root table
        root = new Table();
        root.setFillParent(true);

        // Init title
        titleStyle = new Label.LabelStyle();
        titleStyle.font = skin.getFont("title");
        titleStyle.font.getData().setScale(1.3f, 1.3f);
        title = new Label("Robo Rally", titleStyle);

        // Init button font style
        buttonStyle = new Label.LabelStyle();
        buttonStyle.font = skin.getFont("title");

        // Volume title
        volumeTitle = new Label("Volume", buttonStyle);

        // Add volume buttons and progress bar
        plusButton = new Button(skin.getDrawable("plus"));
        minusButton = new Button(skin.getDrawable("minus"));

        // Add volume progress bar
        volumeBar = new ProgressBar(0, 10, 1, false, skin);
        volumeBar.setValue(5);

        // Add fullscreen button
        toggleFullscreen = new TextButton("Toggle Fullscreen", skin);

        // Add back to menu button
        backToMenu = new TextButton("Back", skin);


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
        addButtonListeners();
        addVolumeListeners();

        //stage.setDebugAll(true);
        stage.addActor(root);
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

    public void addButtonListeners() {
        toggleFullscreen.addListener(new ClickListener() {
            boolean playing = false;
            boolean fullscreen = false;

            // Play sound on hover
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                if (!playing && (fromActor == null || fromActor instanceof TextButton)) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/btn_hover.ogg"));
                    sound.play(game.volume.getValue());
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

            // Go fullscreen when clicked
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("clicked");
                if (fullscreen) {
                    Gdx.graphics.setWindowedMode(1920,1080);
                } else {
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                    fullscreen = true;
                }
            }
        });

        backToMenu.addListener(new ClickListener() {
            boolean playing = false;

            // Play sound on hover
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                if (!playing && (fromActor == null || fromActor instanceof TextButton)) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/btn_hover.ogg"));
                    sound.play(game.volume.getValue());
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

            // Go fullscreen when clicked
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.changeScreen(new MenuScreen(game));
            }
        });
    }

    @Override
    public void render(float v) {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.getBatch().begin();
        // Set color for the background
        stage.getBatch().setColor(skin.getColor("pressed"));
        stage.getBatch().draw(img, 0, 0, stage.getWidth(), stage.getHeight());
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
