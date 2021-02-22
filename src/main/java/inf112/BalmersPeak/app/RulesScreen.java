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

public class RulesScreen extends MainScreen implements Screen {

    // Game object
    private GUI game;

    // Back to menu button
    TextButton backToMenu;

    // Constructor
    public RulesScreen(GUI game) { this.game = game; }

    @Override
    public void show() {
        super.show();

        // Get root table
        Table root = super.getRoot();

        // Get button label
        Label backbtnLabel = super.getBtnLabel("Back");

        // Load rules image
        Image rulesImage = new Image(new Texture("images/floor_guide.png"));

        // Init back to menu button
        backToMenu = new TextButton("Back", skin);
        backToMenu.setLabel(backbtnLabel);
        addNavigationButtonListeners(backToMenu, game, Screens.MENU);

        // Add title to table
        super.addTitle(50.0f);
        // Add rules image
        root.row();
        root.add(rulesImage).padBottom(50.0f);

        // Add back to main menu button
        root.row();
        root.add(backToMenu).colspan(3).center();
    }



    @Override
    public void render(float v) {
        super.render(v);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        super.dispose();
    }
}
