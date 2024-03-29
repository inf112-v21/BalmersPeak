package inf112.balmerspeak.app.network;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.balmerspeak.app.Game;
import inf112.balmerspeak.app.Player;
import inf112.balmerspeak.app.cards.ProgramCard;
import inf112.balmerspeak.app.cards.RotationCard;
import inf112.balmerspeak.app.menu.LobbyScreen;
import inf112.balmerspeak.app.network.messages.*;
import inf112.balmerspeak.app.network.tools.IPFinder;

import java.io.IOException;
import java.util.ArrayList;

public class GameClient extends Client {

    private String username;
    private String hostName;
    private LobbyScreen lobby;
    private Game game;
    private int expectedPlayers;



    public GameClient(String ipAddress, String username) throws IOException {
        super(4096, 4096);
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
                    Gdx.app.postRunnable(() -> handleReceivedNumPlayers((NumPlayers) object));
                }

                // check for allplayers msg
                else if (object instanceof AllPlayersMsg) {
                    AllPlayersMsg msg = (AllPlayersMsg) object;
                    handleReceivedAllPlayersMsg(msg);
                }

                else if (object instanceof CardExecutedMsg) {
                    CardExecutedMsg msg = (CardExecutedMsg) object;
                    if (msg.getCard() instanceof RotationCard)
                        Gdx.app.postRunnable(() -> game.getGameScreen().executeCard(msg.getCard(), msg.getCard().getPlayer()));

                }

                else if (object instanceof RoundOverMsg) {
                    // Set round in progress to false
                    game.setRoundInProgress(false);
                    game.getMyPlayer().setHandReady(false);
                    game.getMyPlayer().getHand().clear();
                    game.getMyPlayer().dealHand(9);
                    Gdx.app.postRunnable(() -> game.getGameScreen().show());
                    Gdx.app.postRunnable(() ->game.getGameScreen().clearQueuelist());
                }
            }
        });
    }

    private void handleReceivedNumPlayers(NumPlayers num) {
        // Check if all players were received, no error for now
        this.expectedPlayers = num.getNumPlayers();
        this.game = new Game(lobby.startGame());
        this.game.setClient(this);
    }

    private void handleReceivedPlayer(Player player) {
        // Check if this player is my own
        if (player.getId() == this.getID()) {
            game.setMyPlayer(player);
            game.getGameScreen().setMyPlayer(player);

        } else {
            game.addPlayer(player);
        }

        // Start game loop if number of players is correct
        if (expectedPlayers == game.getPlayers().size()+1)
            game.gameLoop();
    }

    // Given updated players, move relevant robots and update GUI in case of damage taken
    private void handleReceivedAllPlayersMsg(AllPlayersMsg msg) {
        for (Player player : msg.get()) {
            int dx;
            int dy;
            Player oldPlayer;

            // Check if this is "my player"
            if (player.getId() == game.getMyPlayer().getId()) {
                oldPlayer = game.getMyPlayer();
                dx = player.getRobot().getX() - oldPlayer.getRobot().getX();
                dy = player.getRobot().getY() - oldPlayer.getRobot().getY();

                // Also check if the robot was damaged
                if (oldPlayer.getRobot().getHealth() != player.getRobot().getHealth()) {
                    // Update my player
                    game.getMyPlayer().getRobot().takeDamage();
                    // Update GUI
                    Gdx.app.postRunnable(() -> game.getGameScreen().show());
                }
            } else {
                oldPlayer = game.getPlayerById(player.getId());
                dx = player.getRobot().getX() - oldPlayer.getRobot().getX();
                dy = player.getRobot().getY() - oldPlayer.getRobot().getY();
            }

            // Check if this player moved
            if (dx != 0 || dy != 0) {
                Gdx.app.postRunnable(() -> game.getGameScreen().getBoard().moveRobot(oldPlayer, dx, dy));

                // Update this player object
                game.updatePlayer(player);
            }

        }
        //displayPlayers();
    }

    public void displayPlayers() {
        // My player first
        System.out.println("My player is at: " + game.getMyPlayer().getRobot().getX() + "," + game.getMyPlayer().getRobot().getY());

        for (Player player : game.getPlayers()) {
            System.out.println("Player " + player.getId() + "is at " + player.getRobot().getX() + "," + player.getRobot().getY());
        }
    }




    public void alertServerPlayerIsReady(ArrayList<ProgramCard> cards) {
        sendTCP(new HandMsg(cards));
    }

    public void registerClasses() {
        Kryo kryo = this.getKryo();
        kryo.register(InitMsg.class, new JavaSerializer());
        kryo.register(Player.class, new JavaSerializer());
        kryo.register(NumPlayers.class, new JavaSerializer());
        kryo.register(HandMsg.class, new JavaSerializer());
        kryo.register(CardExecutedMsg.class, new JavaSerializer());
        kryo.register(AllPlayersMsg.class, new JavaSerializer());
        kryo.register(RoundOverMsg.class, new JavaSerializer());
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
