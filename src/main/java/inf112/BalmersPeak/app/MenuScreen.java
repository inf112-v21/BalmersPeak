package inf112.balmerspeak.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MenuScreen implements Screen {
    private GUI game;
    private SpriteBatch batch;
    private Texture img;
    private TextureRegion mainBackground;


    private float f = 0;

    public MenuScreen(GUI game) {
        this.game = game;
    }

    @Override
    public void show() {
        img = new Texture("menubackground.jpg");
        mainBackground = new TextureRegion(img, 0, 0, 2000, 2000);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float v) {
        batch.begin();
        batch.draw(mainBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
//        f += v;
//        // Change after 10 seconds
//        if (f > 10) {
//            game.changeScreen(new GameScreen(game));
//        }
    }

    @Override
    public void resize(int i, int i1) {

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
