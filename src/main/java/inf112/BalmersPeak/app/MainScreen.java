package inf112.balmerspeak.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainScreen implements Screen {
    // Game object
    private GUI game;

    // The skin
    protected Skin skin;

    // Stage
    protected Stage stage;

    // Table to hold actors
    protected Table root;

    // Background image
    private Texture backgroundImage;

    // Title style and label
    protected Label.LabelStyle titleStyle;
    protected Label title;

    // Button label style
    protected Label.LabelStyle buttonStyle;


    // Constructor
    public void MainScreen(GUI game) {
        this.game = game;
    }

    // Get skin
    public Skin getSkin() {
        return this.skin;
    }

    // Get root table
    public Table getRoot() {
        return this.root;
    }

    // Get btn label
    public Label getBtnLabel(String text) {
        Label lbl = new Label(text, buttonStyle);
        lbl.setAlignment(Align.center);
        return lbl;
    }

    public MainScreen getScreen(Screens screen, GUI game) {
        switch(screen) {
            case MENU: return new MenuScreen(game);
            case OPTIONS: return new OptionsScreen(game);
            case RULES: return new RulesScreen(game);
            case GAME: return new GameScreen(game);
            default: return new MenuScreen(game);
        }
    }

    public void addNavigationButtonListeners(TextButton btn, GUI game, Screens screen) {
        btn.addListener(new ClickListener() {
            boolean playing = false;

            // Play sound on hover
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                if (!playing && (fromActor == null || fromActor instanceof TextButton)) {
                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/btn_hover.ogg"));
                    // Play at configured volume
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

            // Go to game screen when clicked
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.changeScreen(getScreen(screen, game));
            }
        });
    }

    public void addTitle(float padding) {
        root.add(title).padBottom(padding);
        root.row();
    }


    @Override
    public void show() {
        // Init stage
        stage = new Stage(new ScreenViewport());
        // Set stage as InputProcessor
        Gdx.input.setInputProcessor(stage);

        // Load background image
        backgroundImage = new Texture("images/menubackground.jpg");

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
        buttonStyle.font = skin.getFont("font");
        buttonStyle.font.getData().setScale(1.3f, 1.3f);


        // Add title to the root table

//        root.add(title).padBottom(250.0f);
//        root.row();

        // Add table to the stage
        stage.addActor(root);
    }

    @Override
    public void render(float v) {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.getBatch().begin();
        // Set color for the background
        stage.getBatch().setColor(skin.getColor("pressed"));
        stage.getBatch().draw(backgroundImage, 0, 0, stage.getWidth(), stage.getHeight());
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
