package inf112.balmerspeak.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import javax.swing.*;

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

    // Title and button font
    BitmapFont titleFont;
    BitmapFont buttonFont;

    // Table containing title and buttons
    Table root;

    // The quantum skin
    Skin skin;

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

        // Volume title
        volumeTitle = new Label("Volume", buttonStyle);

        // Add volume buttons and progress bar
        Button plusButton = new Button(skin.getDrawable("plus"));
        Button minusButton = new Button(skin.getDrawable("minus"));
        minusButton.setRotation(-90);

        ProgressBar volumeBar = new ProgressBar(0, 10, 1, false, skin);
        volumeBar.setValue(3);

        // Add game title
        root.add(title).padBottom(100.0f).colspan(3).center();
        root.row();
        // Add volume title
        root.add(volumeTitle).colspan(3).center();
        root.row();
        // Add volume bar and controls
        root.add(minusButton).size(20,20);
        root.add(volumeBar).size(400, 100);
        root.add(plusButton).size(20,20);

        

        //stage.setDebugAll(true);
        stage.addActor(root);
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
