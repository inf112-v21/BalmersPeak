package inf112.BalmersPeak.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("hello-world");
        cfg.setWindowedMode(500, 500);

        new Lwjgl3Application(new HelloWorld(), cfg);
    }
}