package inf112.balmerspeak.app.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.balmerspeak.app.menu.LobbyScreen;
import inf112.balmerspeak.app.network.messages.InitMsg;

import java.io.IOException;

public class GameClient extends Client {

    private String username;
    private String hostName;
    private LobbyScreen lobby;

    public GameClient(String ipAddress, String username) throws IOException {
        super();
        // Register classes here
        this.registerClasses();
        this.start();
        this.connect(5000, ipAddress, 32500);
        this.username = username;

        // Send the init message as soon as we are connected
        sendTCP(new InitMsg(ipAddress, username));

        // Add listeners
        this.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                // Check type of incoming message
                if (object instanceof  InitMsg) {
                    // Cast and print ip and username
                    InitMsg initMsg = (InitMsg) object;
                    System.out.println("Got init message from server");
                    System.out.println(initMsg.getIP() + initMsg.getUsername());
                }
            }
        });
    }

    public void registerClasses() {
        Kryo kryo = this.getKryo();
        kryo.register(InitMsg.class);
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
        this.sendTCP(message);
    }


}
