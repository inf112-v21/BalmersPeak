package inf112.balmerspeak.app.network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.balmerspeak.app.menu.LobbyScreen;
import com.dosse.upnp.UPnP;


import java.io.IOException;

public class GameClient extends Client {

    private String username;
    private String hostName;
    private LobbyScreen lobby;

    public GameClient(String ipAddress, String username) throws IOException {
        super();
        this.start();
        this.connect(5000, ipAddress, 32500);
        this.username = username;

        // Send username to host on creation
        System.out.println(this.username);
        sendRequest("CONNECTED: " + username);

        // Add listeners
        this.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                super.received(connection, object);
                String msg = (String) object;
                if (msg.startsWith("USERNAME:")) {
                    // Host has sent username
                    hostName = msg.substring(9);
                    setHostName(hostName);
                }
            }
        });
    }

    public void setLobby(LobbyScreen screen) {
        this.lobby = screen;
    }


    public void setHostName(String hostName) {
        this.hostName = hostName;
        // Notify lobby screen
        lobby.hostNameChanged(hostName);
    }

    public void sendRequest(String message) {
        // Punch hole first
        this.sendTCP(message);
    }


}
