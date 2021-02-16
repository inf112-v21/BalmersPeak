package inf112.balmerspeak.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {
    // Game object
    private GUI game;

    // Background image
    private Texture img;

    // Title label
    Label.LabelStyle titleStyle;
    Label title;

    // Button label
    Label.LabelStyle buttonStyle;

    // Play button
    TextButton playButton;
    // Options button
    TextButton optionsButton;
    // Quit button
    TextButton quitButton;

    // Fonts for title and buttons
    BitmapFont titleFont;
    BitmapFont buttonFont;

    // Table containing buttons and the title
    Table root;

    // The skin loaded from assets
    Skin skin;

    // The basis to add table and other actors
    Stage stage;


    public MenuScreen(GUI game) {
        this.game = game;
    }

    @Override
    public void show() {

        // Load title font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("pdark.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 69; // font size
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 3;
        titleFont = generator.generateFont(parameter);
        parameter.size = 29;
        parameter.borderColor = null;
        parameter.borderWidth = 0;
        buttonFont = generator.generateFont(parameter);
        generator.dispose(); // avoid memory leaks, important


        // Init stage
        stage = new Stage(new ScreenViewport());
        // Set stage as InputProcessor
        Gdx.input.setInputProcessor(stage);


        // Load background image
        img = new Texture("menubackground.jpg");

        // Load sound for button hover
        //btnHoverSound = Gdx.audio.newSound(Gdx.files.internal("btn_hover.ogg"));

        // Init skin
        skin = new Skin(Gdx.files.internal("quantum/skin/quantum-horizon-ui.json"));

        // Init root table
        root = new Table();
        root.setFillParent(true);

        // Init title
        titleStyle = new Label.LabelStyle();
        titleStyle.font = titleFont;
        title = new Label("Robo Rally", titleStyle);

        // Init button font style
        buttonStyle = new Label.LabelStyle();
        buttonStyle.font = buttonFont;


        // Init buttons
        playButton = new TextButton("Play", skin);
        playButton.setLabel(new Label("Play", buttonStyle));
        playButton.getLabel().setAlignment(Align.center);
        optionsButton = new TextButton("Options", skin);
        optionsButton.setLabel(new Label("Options", buttonStyle));
        optionsButton.getLabel().setAlignment(Align.center);
        quitButton = new TextButton("Quit", skin);
        quitButton.setLabel(new Label("Quit", buttonStyle));
        quitButton.getLabel().setAlignment(Align.center);

        // Add hover and click listeners to buttons
        addListeners();

        // Add title and buttons to table
        root.add(title).padBottom(250.0f);
        root.row();
        root.add(playButton).prefWidth(200.0f).prefHeight(100.0f);
        root.row();
        root.add(optionsButton).prefWidth(200.0f).prefHeight(100.0f);
        root.row();
        root.add(quitButton).prefWidth(200.0f).prefHeight(100.0f);


        // Add everything to stage
        stage.addActor(root);
    }

    private void addListeners() {
        // Add play button listeners
        playButton.addListener(new ClickListener() {
            boolean playing = false;

            // Play sound on hover
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                if (!playing && (fromActor == null || fromActor instanceof TextButton)) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("btn_hover.ogg"));
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
                game.changeScreen(new GameScreen(game));
            }
        });

        // Add options button listeners
        optionsButton.addListener(new ClickListener() {
            boolean playing = false;

            // Play sound on hover
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                if (!playing && (fromActor == null || fromActor instanceof TextButton)) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("btn_hover.ogg"));
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
                game.changeScreen(new GameScreen(game)); //TODO: refactor and change to optionsScreen
            }
        });

        // Add listeners to quit button
        quitButton.addListener(new ClickListener() {
            boolean playing = false;

            // Play sound on hover
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                if (!playing && (fromActor == null || fromActor instanceof TextButton)) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("btn_hover.ogg"));
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
