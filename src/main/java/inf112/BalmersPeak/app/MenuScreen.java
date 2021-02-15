package inf112.balmerspeak.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuScreen implements Screen {
    private GUI game;
    private SpriteBatch batch;
    private Texture img;

    // Play button
    TextButton playButton;
    TextButton.TextButtonStyle playButtonStyle;

    BitmapFont font;
    private GlyphLayout layout;

    Table table;

    Skin skin;
    TextureAtlas buttonAtlas;


    public MenuScreen(GUI game) {
        this.game = game;
    }

    @Override
    public void show() {
        img = new Texture("menubackground.jpg");
        batch = new SpriteBatch();

        // Load title font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("pdark.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 69; // font size
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 3;
        font = generator.generateFont(parameter);
        generator.dispose(); // avoid memory leaks, important


        // Init skin
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("quantum/skin/quantum-horizon-ui.atlas"));
        skin.addRegions(buttonAtlas);
        playButtonStyle = new TextButton.TextButtonStyle();
        playButtonStyle.font = font;
        playButtonStyle.up = skin.getDrawable("button");
        playButtonStyle.down = skin.getDrawable("button-pressed");
        playButton = new TextButton("Play!", playButtonStyle);




    }

    @Override
    public void render(float v) {
        batch.begin();
        // Draw background image
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Draw title
        layout = new GlyphLayout();
        String title = "Robo Rally";
        layout.setText(font, title);
        float width = layout.width;
        font.draw(batch, layout, (Gdx.graphics.getWidth() - width)/2, Gdx.graphics.getHeight() - 250);

        // Draw table
        playButton.draw(batch, 1.0F);


        
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
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

    }
}
