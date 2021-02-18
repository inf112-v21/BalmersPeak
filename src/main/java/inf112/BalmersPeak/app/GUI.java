package inf112.balmerspeak.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;


public class GUI extends Game {

    public void changeScreen(Screen newScreen) {
        Screen oldScreen = getScreen();
        setScreen(newScreen);
        // Dispose the old screen to release resources
        if (oldScreen != null) {
            oldScreen.dispose();
        }
    }


    @Override
    public void create() {
        changeScreen(new MenuScreen(this));
    }


    @Override
    public void dispose() {
    }

    @Override
    public void render() {
        // Clear screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        //Render the current screen
        super.render();
    }



    /**
     * Called when the Application is resized. Can happen at any point during non-paused state, but never during create()
     * @param width - the new width in pixels
     * @param height - the new height in pixels
     */
    @Override
    public void resize(int width, int height) {
        // The resize method.
    }

    /**
     * Called when the Application is paused, usually when it's not active or visible on-screen. Always paused before it's destroyed
     */
    @Override
    public void pause() {
        // The pause method.
    }

    /**
     * Called when the Application is resumed from a paused state, usually when it regains focus
     */
    @Override
    public void resume() {
        // The resume method.
    }
}
