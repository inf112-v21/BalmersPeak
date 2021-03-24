package inf112.balmerspeak.app.network;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.balmerspeak.app.Game;
import inf112.balmerspeak.app.Player;
import inf112.balmerspeak.app.cards.ProgramCard;
import inf112.balmerspeak.app.menu.GameScreen;
import inf112.balmerspeak.app.menu.LobbyScreen;
import inf112.balmerspeak.app.network.messages.CardExecutedMsg;
import inf112.balmerspeak.app.network.messages.HandMsg;
import inf112.balmerspeak.app.network.messages.InitMsg;
import inf112.balmerspeak.app.network.messages.NumPlayers;
import inf112.balmerspeak.app.network.tools.CoordsResolver;
import inf112.balmerspeak.app.network.tools.IPFinder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameServer extends Server {

    private final String ipAddress;
    private LobbyScreen lobby;
    private final String username;
    private Map<Connection, InitMsg> clients;
    private Game game;
    private GameScreen gameScreen;


    public GameServer(String username) throws IOException {
        super(4096, 4096);
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
                // Check for init message
                if (object instanceof InitMsg) {
                    handleInitMsg(connection, (InitMsg) object, username);
                }

                // Check for hand ready message
                else if (object instanceof HandMsg) {
                    // Set players hand
                    game.getPlayerById(connection.getID()).setHand(((HandMsg) object).getCards());
                    // Set player to ready
                    game.getPlayerById(connection.getID()).setHandReady(true);
                    // Check if all players are ready
                    if (game.getAllPlayersReady())
                        Gdx.app.postRunnable(() -> game.startRound());
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

    public void setGameScreen(GameScreen screen) {
        this.gameScreen = screen;
    }

    private void handleInitMsg(Connection connection, InitMsg initMsg, String username) {
        // Add client to connections list
        clients.put(connection, initMsg);
        // Add client to lobby screen
        lobby.addConnectedClient(initMsg.getIP(), initMsg.getUsername());
        // Respond with own init message
        connection.sendTCP(new InitMsg(ipAddress, username));
    }

    // Send the card that was executed to all clients
    public void sendCardExecuted(ProgramCard card) {
        for (Connection client : clients.keySet()) {
            client.sendTCP(new CardExecutedMsg(card.getPlayer().getId(), card));
        }
    }

    public void sendStartMessage() {
        // Send number of players first to every client
        for (Connection client : clients.keySet()) {
            client.sendTCP(new NumPlayers(clients.size()+1));
        }
        // Coords resolver
        CoordsResolver resolver = new CoordsResolver();
        // Instantiate game with own player object
        Player myPlayer = new Player(resolver.getCoordsPair(), username, ipAddress, 0); // host is always id 0
        game = new Game(myPlayer, this.gameScreen, this);

        // Construct player object for every client and add to game
        for (Connection client : clients.keySet()) {
            Player clientPlayer = new Player(resolver.getCoordsPair(), clients.get(client).getUsername(), clients.get(client).getIP(), client.getID());
            // Add to game
            game.addPlayer(clientPlayer);
        }

        // Now send every player to every client
        for (Connection client : clients.keySet()) {
            for (Player player : game.getPlayers()) {
                client.sendTCP(player);
            }
            // Send host player
            client.sendTCP(myPlayer);
        }

        // Now start the server game loop
        game.gameLoop();
    }



    public void registerClasses() {
        Kryo kryo = this.getKryo();
        kryo.register(InitMsg.class, new JavaSerializer());
        kryo.register(Player.class, new JavaSerializer());
        kryo.register(NumPlayers.class, new JavaSerializer());
        kryo.register(HandMsg.class, new JavaSerializer());
        kryo.register(CardExecutedMsg.class, new JavaSerializer());
    }


    public void setLobby(LobbyScreen screen) {
        this.lobby = screen;
    }

    public String getIPAddress() {
        return this.ipAddress;
    }

}
