package inf112.balmerspeak.app.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.balmerspeak.app.Game;
import inf112.balmerspeak.app.Player;
import inf112.balmerspeak.app.menu.LobbyScreen;
import inf112.balmerspeak.app.network.messages.InitMsg;
import inf112.balmerspeak.app.network.messages.StartMsg;
import inf112.balmerspeak.app.network.serializers.InitMsgSerializer;
import inf112.balmerspeak.app.network.serializers.StartMsgSerializer;
import inf112.balmerspeak.app.network.tools.CoordsResolver;
import inf112.balmerspeak.app.network.tools.IPFinder;
import org.javatuples.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameServer extends Server {

    private final String ipAddress;
    private LobbyScreen lobby;
    private final String username;
    private Map<Connection, InitMsg> clients;
    private Game game;


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
        // Resolve coordinates object
        CoordsResolver resolver = new CoordsResolver();
        // Make own player object with coords from resolver
        Player thisPlayer = new Player(resolver.getCoordsPair());
        // Instantiate new game
        game = new Game(thisPlayer);

        // Send start coordinates to every other client/player
        for (Connection connection : clients.keySet()) {
            // Add a player object to game with coordinates
            Player tmpPlayer = new Player(resolver.getCoordsPair());
            game.addPlayer(tmpPlayer);
            // Send coords to relevant client
            connection.sendTCP(new StartMsg(tmpPlayer.getCoords()));
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
