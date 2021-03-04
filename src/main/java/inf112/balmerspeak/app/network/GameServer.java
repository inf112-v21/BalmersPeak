package inf112.balmerspeak.app.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class GameServer extends Server {

    private String ipAddress;

    public GameServer() throws IOException {
        super();
        this.start();
        this.bind(32402);
        ipAddress = IPFinder.get();

        // Adding listeners
        this.addListener(new Listener() {
            public void received(Connection connection, String object) {
                System.out.println(object);
                connection.sendTCP("Hello");
            }
        });
    }

    public String getIPAddress() {
        return this.ipAddress;
    }
}
