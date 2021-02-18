package inf112.balmerspeak.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class OptionsScreen implements Screen {

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

    // Title and button font
    BitmapFont titleFont;
    BitmapFont buttonFont;

    // Table containing title and buttons
    Table root;

    // The quantum skin
    Skin quantumSkin;
    // The orange skin (just for the checkbox)
    Skin orangeSkin;

    // Stage to add all components
    Stage stage;

    public OptionsScreen(GUI game) { this.game = game; }

    @Override
    public void show() {

        // Load title font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/pdark.ttf"));
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
        img = new Texture("images/menubackground.jpg");

        // Init skin
        quantumSkin = new Skin(Gdx.files.internal("skins/quantum/skin/quantum-horizon-ui.json"));
        orangeSkin = new Skin(Gdx.files.internal("skins/orange/skin/uiskin.json"));

        // Init root table
        root = new Table();
        root.setFillParent(true);

        // Init title
        titleStyle = new Label.LabelStyle();
        titleStyle.font = quantumSkin.getFont("title");
        titleStyle.font.getData().setScale(1.3f, 1.3f);
        title = new Label("Robo Rally", titleStyle);

        // Init button font style
        buttonStyle = new Label.LabelStyle();
        buttonStyle.font = quantumSkin.getFont("title");

        // Volume title
        volumeTitle = new Label("Volume", buttonStyle);

        // Add volume buttons and progress bar
        plusButton = new Button(quantumSkin.getDrawable("plus"));
        minusButton = new Button(quantumSkin.getDrawable("minus"));
        minusButton.setRotation(-90);

        // Add volume progress bar
        volumeBar = new ProgressBar(0, 10, 1, false, quantumSkin);
        volumeBar.setValue(5);

        // Add fullscreen button
       toggleFullscreen = new TextButton("Toggle Fullscreen", quantumSkin);

       // Add back to menu button
        TextButton backToMenu = new TextButton("Back", quantumSkin);


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


        stage.addActor(root);
    }

    public void addVolumeListeners() {
        // Add listener to minus button

    }

    public void addButtonListeners() {
//
    }

    @Override
    public void render(float v) {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.getBatch().begin();
        // Set color for the background
        stage.getBatch().setColor(quantumSkin.getColor("pressed"));
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
        quantumSkin.dispose();
    }
}
