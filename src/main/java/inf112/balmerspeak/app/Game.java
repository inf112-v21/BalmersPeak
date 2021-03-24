package inf112.balmerspeak.app;

import inf112.balmerspeak.app.cards.ProgramCard;
import inf112.balmerspeak.app.menu.GameScreen;
import inf112.balmerspeak.app.network.GameClient;

import java.util.ArrayList;
import java.util.Collections;

public class Game {

    private Player myPlayer;
    private ArrayList<Player> players;
    private GameScreen gameScreen;
    private GameClient client;


    public Game(Player player, GameScreen gameScreen) {
        this.myPlayer = player;
        this.players = new ArrayList<>();
        this.gameScreen = gameScreen;
        gameScreen.setGame(this);
        gameScreen.setPlayer(myPlayer);
    }

    public Game(GameScreen screen) {
        this.players = new ArrayList<>();
        this.gameScreen = screen;
        gameScreen.setGame(this);
    }

    public GameScreen getGameScreen() {
        return this.gameScreen;
    }


    // The game loop which executes all stages of the game and exits when a winner is determined
    public void gameLoop() {
        // First, place robots
        placeRobotsAtStart();
        // Set game screen player to this player
        myPlayer.getRobot().giveHand(9);
    }

    public void startRound() {
        // All cards have been sent, find list of first cards to execute
        ArrayList<ProgramCard> cardsForThisRound = getCardOrder();
        // Execute them in order and send to clients
        for (ProgramCard card : cardsForThisRound) {

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
            myPlayer.getRobot().setHand(cards);
            // Set this player to ready
            myPlayer.setHandReady(true);
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

    // Computes the order of cards for one round.
    public ArrayList<ProgramCard> getCardOrder() {
        // Fetch everyone's first card from deck
        ArrayList<ProgramCard> cards = new ArrayList<>();
        for (Player player : players) {
            cards.add(player.getRobot().getHand().remove(0));
        }
        // Add host players card
        cards.add(myPlayer.getRobot().getHand().remove(0));
        // Sort it for priority and return
        Collections.sort(cards);
        return cards;
    }

}
