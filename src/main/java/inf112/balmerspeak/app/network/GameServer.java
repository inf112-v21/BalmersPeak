package inf112.balmerspeak.app.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.balmerspeak.app.menu.LobbyScreen;

import java.io.IOException;
import java.util.ArrayList;

public class GameServer extends Server {

    private String ipAddress;
    private LobbyScreen lobby;
    private ArrayList<Connection> clients;

    public GameServer() throws IOException {
        super();
        this.start();
        this.bind(32402);
        ipAddress = IPFinder.get();

        // Adding listeners
        this.addListener(new Listener() {
            public void received(Connection connection, String object) {
                System.out.println(object);
                // Add client to lobby screen
                if (object.startsWith("CONNECTED:")) {
                    displayConnectedClient(connection.getRemoteAddressTCP().toString(), object);
                    // TODO: send username back
                    connection.sendTCP("Hello");
                }
            }
        });
    }

    public void setLobby(LobbyScreen screen) {
        this.lobby = screen;
    }

    public String getIPAddress() {
        return this.ipAddress;
    }

    public void displayConnectedClient(String ipAddress, String username) {
        lobby.addConnectedClient(ipAddress, username);
    }
}
