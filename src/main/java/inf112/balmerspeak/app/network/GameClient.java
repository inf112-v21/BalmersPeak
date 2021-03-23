package inf112.balmerspeak.app.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.balmerspeak.app.Game;
import inf112.balmerspeak.app.Player;
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
                // Check type of incoming message
                if (object instanceof  InitMsg) {
                    // Cast and print ip and username
                    InitMsg initMsg = (InitMsg) object;
                    // Send username and IP to lobby screen to display
                    setHostNameAndIP(initMsg.getUsername(), initMsg.getIP());
                }

                // check for player
                else if (object instanceof Player) {
                    Player player = (Player) object;
                    // If there is no game object, instantiate it
                    if (game == null) {
                        game = new Game(player);
                        // tell lobby to start the game as well
                        lobby.startGame();
                    }
                    // if there is a game object, this object is another player
                    else {
                        game.addPlayer(player);
                    }
                }

                else if (object instanceof NumPlayers) {
                    // Means every player has been received, can start the game loop
                    NumPlayers num = (NumPlayers) object;
                    if (num.getNumPlayers() == game.getPlayers().size()) {
                        System.out.println("All players received");
                    }
                    // Starting the game here
                    game.gameLoop();
                }

            }
        });
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
