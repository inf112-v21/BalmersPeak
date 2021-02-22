package inf112.balmerspeak.app.Menu;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import inf112.balmerspeak.app.GUI;

public class RulesScreen extends MainScreen implements Screen {

    // Game object
    private GUI game;

    // Constructor
    public RulesScreen(GUI game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();

        // Get root table
        Table root = super.getRoot();

        // Get skin
        Skin skin = super.getSkin();

        // Get button label
        Label backbtnLabel = super.getBtnLabel("Back");

        // Load rules image
        Image rulesImage = new Image(new Texture("images/floor_guide.png"));

        // Init back to menu button
        // Back to menu button
        TextButton backToMenu = new TextButton("Back", skin);
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
    public void pause() {
        // Called when this screen is not focus.
    }

    @Override
    public void resume() {
        // Called when game resumes, and this screen is focus.
    }

    @Override
    public void hide() {
        // Called when this screen is no longer the current screen for the Game.
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
