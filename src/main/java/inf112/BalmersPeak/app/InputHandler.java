package inf112.balmerspeak.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class InputHandler extends InputAdapter {

    public boolean wPressed;
    public boolean aPressed;
    public boolean sPressed;
    public boolean dPressed;

    public InputHandler() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        // Key was pressed, set corresponding bool to true
        switch(keycode) {
            case Input.Keys.W:
                wPressed = true;
                break;
            case Input.Keys.A:
                aPressed = true;
                break;
            case Input.Keys.S:
                sPressed = true;
                break;
            case Input.Keys.D:
                dPressed = true;
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // Key was released, set corresponding bool to false
        switch(keycode) {
            case Input.Keys.W:
                wPressed = false;
                break;
            case Input.Keys.A:
                aPressed = false;
                break;
            case Input.Keys.S:
                sPressed = false;
                break;
            case Input.Keys.D:
                dPressed = false;
                break;
            default:
                break;
        }
        return false;
    }

    public void clear() {
        wPressed = false;
        aPressed = false;
        sPressed = false;
        dPressed = false;
    }


}
