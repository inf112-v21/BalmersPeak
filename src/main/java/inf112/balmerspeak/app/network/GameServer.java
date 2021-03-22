package inf112.balmerspeak.app.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.balmerspeak.app.menu.LobbyScreen;
import inf112.balmerspeak.app.network.messages.InitMsg;
import inf112.balmerspeak.app.network.messages.StartMsg;
import inf112.balmerspeak.app.network.messages.serializers.InitMsgSerializer;
import inf112.balmerspeak.app.network.messages.serializers.StartMsgSerializer;
import org.javatuples.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameServer extends Server {

    private final String ipAddress;
    private LobbyScreen lobby;
    private final String username;
    private Map<Connection, InitMsg> clients;


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
        clients = new HashMap<>();



        // Adding listeners
        this.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                // Check which type of msg this is
                if (object instanceof InitMsg) {
                    // Init message received containing username and IP, cast it
                    InitMsg initMsg = (InitMsg) object;
                    // Add client to connections list
                    clients.put(connection, initMsg);
                    // Add client to lobby screen
                    lobby.addConnectedClient(initMsg.getIP(), initMsg.getUsername());
                    // Respond with own init message
                    connection.sendTCP(new InitMsg(ipAddress, username));
                }
            }

            @Override
            public void disconnected(Connection connection) {
                super.disconnected(connection);
                // Remove from GUI lobby screen
                lobby.removeConnectedClient(clients.get(connection).getIP());
                // Remove connection from server list
                clients.remove(connection);
            }
        });
    }

    public void sendStartMessage() {
        // Send the starting coordinates to all connections
        // TODO: send acrual cooridnates, not the same for every player
        System.out.println("called");
        System.out.println(clients.keySet().size());
        for (Connection player : clients.keySet()) {
            System.out.println("sending");
            player.sendTCP(new StartMsg(new Pair(2, 7)));
        }

    }

    public void registerClasses() {
        Kryo kryo = this.getKryo();
        kryo.register(InitMsg.class, new InitMsgSerializer());
        kryo.register(StartMsg.class, new StartMsgSerializer());
    }


    public void setLobby(LobbyScreen screen) {
        this.lobby = screen;
    }

    public String getIPAddress() {
        return this.ipAddress;
    }

}
