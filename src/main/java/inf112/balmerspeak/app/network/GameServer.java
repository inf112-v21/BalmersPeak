package inf112.balmerspeak.app.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.balmerspeak.app.menu.LobbyScreen;
import java.io.IOException;

public class GameServer extends Server {

    private String ipAddress;
    private LobbyScreen lobby;
    private String username;


    public GameServer(String username) throws IOException {
        super();
        this.start();
        this.bind(32500);
        this.ipAddress = IPFinder.get();
        this.username = username;

        // Adding listeners
        this.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                System.out.println("RECEIVED");
                System.out.println(object);
                String msg = (String) object;


                // Add client to lobby screen
                if (msg.startsWith("CONNECTED:")) {
                    displayConnectedClient(connection.getRemoteAddressTCP().toString(), msg);
                    // Punch hole first
                    connection.sendTCP("USERNAME:" + getUsername());
                }
            }
        });
    }

    public String getUsername() {
        return this.username;
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
