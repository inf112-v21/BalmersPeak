package inf112.balmerspeak.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class MapHandlerTest {

    private MapHandler mapHandler;

    @Test
    public void getMapShouldReturnMap(){
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Robo Rally");
        cfg.setWindowedMode(1920, 1080);
        new Lwjgl3Application(new GUI(), cfg);
        mapHandler = new MapHandler();
        assertTrue(mapHandler.getMap() != null);
    }

}