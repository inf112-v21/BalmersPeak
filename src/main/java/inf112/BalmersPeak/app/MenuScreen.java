package inf112.balmerspeak.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class MenuScreen implements Screen {
    private GUI game;
    private SpriteBatch batch;
    private Texture img;

    BitmapFont font;


    public MenuScreen(GUI game) {
        this.game = game;
    }

    @Override
    public void show() {
        img = new Texture("menubackground.jpg");
        batch = new SpriteBatch();

        // Load font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("pdark.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12; // font size
        font = generator.generateFont(parameter);
        generator.dispose(); // avoid memory leaks, important
    }

    @Override
    public void render(float v) {
        batch.begin();
        // Draw background image
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // Draw title
        font.draw(batch, "Hello, World!", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 + 350);
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
