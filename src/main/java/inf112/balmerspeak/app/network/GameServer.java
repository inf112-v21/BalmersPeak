package inf112.balmerspeak.app.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.balmerspeak.app.menu.LobbyScreen;
import inf112.balmerspeak.app.network.messages.InitMsg;
import inf112.balmerspeak.app.network.messages.serializers.InitMsgSerializer;

import java.io.IOException;
import java.util.ArrayList;

public class GameServer extends Server {

    private final String ipAddress;
    private LobbyScreen lobby;
    private final String username;
    private ArrayList<Connection> clients;


    public GameServer(String username) throws IOException {
        super();
        // Register classes here
        this.registerClasses();
        // Start server and bind to port
        this.start();
        this.bind(32500);
        this.ipAddress = IPFinder.get();
        this.username = username;
        // Initialize clients list
        clients = new ArrayList<>();



        // Adding listeners
        this.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                // Check which type of msg this is
                if (object instanceof InitMsg) {
                    // Init message received containing username and IP, cast it
                    InitMsg initMsg = (InitMsg) object;
                    // Add client to connections list
                    clients.add(connection);
                    // Add client to lobby screen
                    lobby.addConnectedClient(initMsg.getIP(), initMsg.getUsername());
                    // Respond with own init message
                    connection.sendTCP(new InitMsg(ipAddress, username));
                }
            }
        });
    }

    public void registerClasses() {
        Kryo kryo = this.getKryo();
        kryo.register(InitMsg.class, new InitMsgSerializer());
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
