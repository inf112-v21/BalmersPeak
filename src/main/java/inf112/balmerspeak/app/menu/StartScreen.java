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
    // IP Address field
    TextField ipField;

    // Username dialog
    Dialog usernameDialog;
    // username
    public String username;

    boolean isHost;


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
        addHostListener(hostBtn);
        addHostListener(joinBtn);





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

        TextButton continueDialogBtn = new TextButton("Continue", skin);

        usernameDialog.add(userField);
        usernameDialog.button(continueDialogBtn); //sends "true" as the result



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


        joinBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                usernameDialog.show(getStage());
                setIsHost(false);
            }
        });

        hostBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                usernameDialog.show(getStage());
                setIsHost(true);
            }
        });

        continueDialogBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (isHost) {
                    String IP = ipField.getText();
                    if (isHost)
                        try {
                            game.startGameClient(IP, username);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                } else {
                    try {
                        game.startGameServer(username);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                game.changeScreen(new LobbyScreen(game, isHost));
            }
        });
    }



    public void setIsHost(boolean isHost) {
        this.isHost = isHost;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Stage getStage() { return super.getStage(); }


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
