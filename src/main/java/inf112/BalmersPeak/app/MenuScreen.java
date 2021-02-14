package inf112.balmerspeak.app;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen implements Screen {
    private GUI game;
    private SpriteBatch batch;
    private Texture img;


    private float f = 0;

    public MenuScreen(GUI game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        img = new Texture("logo.jpg");
    }

    @Override
    public void render(float v) {
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
        f += v;
        // Change after 10 seconds
        if (f > 10) {
            game.changeScreen(new GameScreen(game));
        }
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
