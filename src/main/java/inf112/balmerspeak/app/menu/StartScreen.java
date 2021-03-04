package inf112.balmerspeak.app.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.balmerspeak.app.GUI;

import java.io.IOException;


public class StartScreen extends MainScreen implements Screen {

    // Game object
    private GUI game;

    // Host button
    TextButton hostBtn;

    // Join button
    TextButton joinBtn;
    // IP Address fielde
    TextField ipField;

    // Username dialog
    Dialog usernameDialog;
    // username
    public String username;


    public StartScreen(GUI game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();

        // Get root table
        Table root = super.getRoot();

        // Get skin
        Skin skin = super.getSkin();

        // Get buttons label
        Label backBtnLabel = super.getBtnLabel("Back");
        Label hostBtnLabel = super.getBtnLabel("Host");
        Label joinbtnLabel = super.getBtnLabel("Join");
        Label seperatorLabel = super.getBtnLabel("__________________________ or __________________________ ");

        // Get title
        Label title = super.getTitleLabel("Robo Rally");

        // Add host button
        hostBtn = new TextButton("Host", skin);
        hostBtn.setLabel(hostBtnLabel);
        joinBtn = new TextButton("Join", skin);
        joinBtn.setLabel(joinbtnLabel);

        // Add listener to joinBtn to connect to a host
        addJoinListener();
        // Add listener to hostBtn to start a server
        addHostListener();





        // Add ip address text field
        ipField = new TextField("IP Address: ", skin);


        // Add back to menu button
        TextButton backToMenu = new TextButton("Back", skin);
        backToMenu.setLabel(backBtnLabel);

        // Add hover listeners
        addHoverListeners(hostBtn, game);
        addNavigationButtonListeners(hostBtn, game, Screens.LOBBY);
        addHoverListeners(joinBtn, game);
        addHoverListeners(backToMenu, game);
        addNavigationButtonListeners(backToMenu, game, Screens.MENU);

        // Add username dialog
        TextField userField = new TextField("Username: ", skin);
        usernameDialog = new Dialog("Choose username", skin, "dialog") {
            public void result(Object obj) {
                System.out.println("result "+obj);
                setUsername(userField.getText());
            }
        };

        usernameDialog.add(userField);
        usernameDialog.button("Continue", userField.getText()); //sends "true" as the result
        usernameDialog.key(Input.Keys.ENTER, userField.getText()); //sends "true" when the ENTER key is pressed



        // Add game title
        root.add(title).padBottom(100.0f).colspan(3).center();
        root.row();

        // Add host button
        root.add(hostBtn).prefWidth(200.0f).prefHeight(100.0f).padBottom(75.0f);
        root.row();
        root.add(seperatorLabel).padBottom(75.0f);
        root.row();
        root.add(ipField).prefWidth(350.0f).prefHeight(50.0f);
        root.row();
        root.add(joinBtn).prefWidth(200.0f).prefHeight(100.0f);
        root.row();
        root.add(backToMenu).prefWidth(200.0f).prefHeight(100.0f);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addHostListener() {
        hostBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // Start a game server
                try {
                    game.startGameServer();
                    game.changeScreen(new LobbyScreen(game, true));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Stage getStage() { return super.getStage(); }

    public void addJoinListener() {
        joinBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // Fetch ip from text field
                String IP = ipField.getText();
                // Start a Client connection
                try {
                    // Show username dialog
                    usernameDialog.show(getStage());
                    game.startGameClient(IP, username);
                    game.changeScreen(new LobbyScreen(game, false));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
