package inf112.balmerspeak.app.network;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.balmerspeak.app.Game;
import inf112.balmerspeak.app.Player;
import inf112.balmerspeak.app.menu.GameScreen;
import inf112.balmerspeak.app.menu.LobbyScreen;
import inf112.balmerspeak.app.network.messages.InitMsg;
import inf112.balmerspeak.app.network.messages.NumPlayers;
import inf112.balmerspeak.app.network.serializers.InitMsgSerializer;
import inf112.balmerspeak.app.network.serializers.NumPlayersSerializer;
import inf112.balmerspeak.app.network.serializers.PlayerSerializer;
import inf112.balmerspeak.app.network.tools.IPFinder;

import java.io.IOException;

public class GameClient extends Client {

    private String username;
    private String hostName;
    private LobbyScreen lobby;
    private Game game;
    private int expectedPlayers;


    public GameClient(String ipAddress, String username) throws IOException {
        super();
        // Register classes here
        this.registerClasses();
        this.start();
        this.connect(5000, ipAddress, 32500);
        this.username = username;

        // Send the init message as soon as we are connected
        sendTCP(new InitMsg(IPFinder.get(), username));

        // Add listeners
        this.addListener(new Listener() {


            @Override
            public void received(Connection connection, Object object) {

                // Check for InitMsg message
                if (object instanceof  InitMsg) {
                    // Cast and print ip and username
                    InitMsg initMsg = (InitMsg) object;
                    // Send username and IP to lobby screen to display
                    setHostNameAndIP(initMsg.getUsername(), initMsg.getIP());
                }

                // check for player message
                else if (object instanceof Player) {
                    // Hand off to gdx thread
                    Gdx.app.postRunnable(() -> handleReceivedPlayer((Player) object));
                }

                // check for NumPlayers message
                else if (object instanceof NumPlayers) {
                    handleReceivedNumPlayers((NumPlayers) object);
                }
            }
        });
    }

    private void handleReceivedNumPlayers(NumPlayers num) {
        // Check if all players were received, no error for now
        this.expectedPlayers = num.getNumPlayers();
    }

    private void handleReceivedPlayer(Player player) {
        // If there is no game object, instantiate it
        if (game == null) {
            // Tell lobby to start game and save returned game screen
            GameScreen screen = lobby.startGame();
            // Create game object with this screen
            game = new Game(player, screen);
        }
        // if there is a game object, this object is another player
        else {
            game.addPlayer(player);
        }

        // Check if all players are received, and start game loop if so
        if (game.getPlayers().size() == this.expectedPlayers)
            game.gameLoop();
    }

    public void registerClasses() {
        Kryo kryo = this.getKryo();
        kryo.register(InitMsg.class, new InitMsgSerializer());
        kryo.register(Player.class, new PlayerSerializer());
        kryo.register(NumPlayers.class, new NumPlayersSerializer());
    }

    public void setLobby(LobbyScreen screen) {
        this.lobby = screen;
    }

    public void setHostNameAndIP(String hostName, String IP) {
        this.hostName = hostName;
        // Notify lobby screen
        lobby.setStatusLabel(hostName, IP);
    }
}
