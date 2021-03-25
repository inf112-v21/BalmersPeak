package inf112.balmerspeak.app;

import com.badlogic.gdx.Gdx;
import inf112.balmerspeak.app.cards.ProgramCard;
import inf112.balmerspeak.app.menu.GameScreen;
import inf112.balmerspeak.app.network.GameClient;
import inf112.balmerspeak.app.network.GameServer;

import java.util.ArrayList;
import java.util.Collections;


public class Game {

    private Player myPlayer;
    private ArrayList<Player> players;
    private GameScreen gameScreen;
    private GameClient client;
    private GameServer server;
    private boolean roundInProgress;


    public Game(Player player, GameScreen gameScreen, GameServer server) {
        this.myPlayer = player;
        this.players = new ArrayList<>();
        this.gameScreen = gameScreen;
        this.server = server;
        gameScreen.setGame(this);
        gameScreen.setMyPlayer(myPlayer);
        roundInProgress = false;
    }

    public Game(GameScreen screen) {
        this.players = new ArrayList<>();
        this.gameScreen = screen;
        gameScreen.setGame(this);
    }

    public GameScreen getGameScreen() {
        return this.gameScreen;
    }

    public boolean isRoundInProgress() {
        return this.roundInProgress;
    }


    // The game loop which executes all stages of the game and exits when a winner is determined
    public void gameLoop() {
        // First, place robots
        placeRobotsAtStart();
        // Set game screen player to this player
        myPlayer.dealHand(9);
    }

    // All hands are ready, sort after priority and execute them
    public void startRound() {
        // Set round in progress
        roundInProgress = true;
        // Get sorted cards for the round
        ArrayList<ProgramCard> sortedCards = getCardOrder();

        for (ProgramCard card : sortedCards) {
            // Execute the movement and send to all clients
            System.out.println("Executing player " + card.getPlayer().getId() + "'s card, " + card);
            gameScreen.executeCard(card, card.getPlayer());
            // Send to all clients
            server.sendCardExecuted(card);
        }
    }

    // Places all players robots at their starting position
    public void placeRobotsAtStart() {
        // Place own robot
        gameScreen.getBoard().placeRobot(myPlayer.getRobot().getX(), myPlayer.getRobot().getY());
        // Place everyone else's
        for (Player player : players) {
            gameScreen.getBoard().placeRobot(player.getRobot().getX(), player.getRobot().getY());
        }
    }

    // Alert server that this player is ready
    public void handIsReady(ArrayList<ProgramCard> cards) {
        // if host, set player to ready
        if (myPlayer.getId() == 0) {
            // Set own hand to received cards
            myPlayer.setHand(cards);
            // Set this player to ready
            myPlayer.setHandReady(true);
            // Check if all players are ready
            if (getAllPlayersReady())
                Gdx.app.postRunnable(this::startRound);
        } else
            client.alertServerPlayerIsReady(cards);
    }

    public void addPlayer(Player newPlayer) {
        this.players.add(newPlayer);
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public void setMyPlayer(Player myPlayer) {
        this.myPlayer = myPlayer;
    }

    public boolean getAllPlayersReady() {
        boolean othersReady = true;
        for (Player player : players) {
            if (!player.isHandReady())
                othersReady = false;
        }
        return myPlayer.isHandReady() && othersReady;
    }


    public Player getPlayerById(int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }

    public void setClient(GameClient client) {
        this.client = client;
    }

    // Computes the order of cards for the whole round
    public ArrayList<ProgramCard> getCardOrder() {
        // Collect each first card and connect them to the player
        ArrayList<ProgramCard> cards = new ArrayList<>();
        for (Player player : players) {
            ProgramCard card = player.getHand().remove(0);
            // Set owner of this card
            card.setPlayer(player);
            cards.add(card);
        }
        // Set host card
        ProgramCard myCard = myPlayer.getHand().remove(0);
        myCard.setPlayer(myPlayer);
        cards.add(myCard);

        Collections.sort(cards);

        return cards;
    }


}
