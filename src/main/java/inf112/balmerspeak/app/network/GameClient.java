package inf112.balmerspeak.app.network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class GameClient extends Client {

    private String username;

    public GameClient(String ipAddress, String username) throws IOException {
        super();
        this.start();
        this.connect(5000, ipAddress, 32402);
        this.username = username;

        // Send username to host on creation
        sendRequest("CONNECTED: " + username);

        // Add listeners
        this.addListener(new Listener() {
            public void received(Connection connection, String text) {
                System.out.println(text);
                sendRequest("hello");
            }
        });
    }

    public void sendRequest(String message) {
        this.sendTCP(message);
    }


}
