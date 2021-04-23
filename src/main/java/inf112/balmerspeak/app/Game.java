package inf112.balmerspeak.app;

import com.badlogic.gdx.Gdx;
import inf112.balmerspeak.app.cards.ProgramCard;
import inf112.balmerspeak.app.menu.GameScreen;
import inf112.balmerspeak.app.network.GameClient;
import inf112.balmerspeak.app.network.GameServer;
import inf112.balmerspeak.app.network.messages.AllPlayersMsg;
import inf112.balmerspeak.app.network.messages.RoundOverMsg;

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

    public void setRoundInProgress(boolean value) {
        this.roundInProgress = value;
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
        // Make players clone
        ArrayList<Player> playersClone = (ArrayList<Player>) this.players.clone();
        playersClone.add(myPlayer);
        // Set round in progress
        roundInProgress = true;

        // Loop five times, once for each card
        for (int i = 0; i <= 1; i++) {
            // Phase 1: robots move
            runPhase1();


            // Phase 2: board elements move
            // in order: conveyor belts, pushers (missing), gears (missing)
            runPhase2(playersClone);

            // Phase 4: touch checkpoints
        }

        handleRoundOver();
    }

    public void handleRoundOver() {
        // Set round in progress to false
        roundInProgress = false;
        // Deal new cards and set own hand to not ready
        myPlayer.setHandReady(false);
        myPlayer.getHand().clear();
        myPlayer.dealHand(9);
        gameScreen.show();
        gameScreen.clearQueuelist();
        // Tell all players to deal hands
        for (Player player : players) {
            server.sendMessageToClient(player.getId(), new RoundOverMsg());
            player.setHandReady(false);
        }
    }

    // Robots move
    private void runPhase1() {
        // Get sorted cards for the round
        ArrayList<ProgramCard> sortedCards = getCardOrder();

        // Phase 1: robots move
        for (ProgramCard card : sortedCards) {
            // Send to all clients
            server.sendCardExecuted(card);
            // Execute the movement and send to all clients
            gameScreen.executeCard(card, card.getPlayer());

        }
        sendUpdatedPlayers();
    }

    // Execute board elements
    private void runPhase2(ArrayList<Player> playersClone) {
        // Run conveyor belts
        gameScreen.getBoard().runBelt(playersClone);
        // Send updated coords to all players
        sendUpdatedPlayers();


        // Run gears
        gameScreen.getBoard().runGear(myPlayer);
        // Send updated coords/rotation
        sendUpdatedPlayers();


        // Run board lasers
        gameScreen.getBoard().fireBoardLasers(myPlayer, players, gameScreen);
        // Send updated players for damage update
        sendUpdatedPlayers();


        // Fire robot lasers
        gameScreen.getBoard().fireRobotLasers(playersClone, gameScreen);
        // Send updated players for damage update
        sendUpdatedPlayers();
    }

    public void sendUpdatedPlayers() {
        // Send all player objects to all clients to update coords
        ArrayList<Player> playerList = (ArrayList<Player>) this.players.clone();
        playerList.add(myPlayer);

        AllPlayersMsg msg = new AllPlayersMsg(playerList);
        for (Player player : playerList) {
            server.sendMessageToClient(player.getId(), msg);
        }
    }

    // Places all players robots at their starting position
    public void placeRobotsAtStart() {
        // Place own robot
        gameScreen.getBoard().placeRobot(myPlayer);
        // Place everyone else's
        for (Player player : players) {
            gameScreen.getBoard().placeRobot(player);
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
        } else {
            roundInProgress = true;
            client.alertServerPlayerIsReady(cards);
        }
    }

    public void addPlayer(Player newPlayer) {
        this.players.add(newPlayer);
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public void updatePlayer(Player player) {
        if (player.getId() == myPlayer.getId()) {
            this.players.remove(myPlayer);
            this.players.add(player);
            setMyPlayer(player);
            return;
        }
        int counter = 0;
        for (Player oldPlayer : players) {
            if (oldPlayer.getId() == player.getId()) {
                this.players.remove(counter);
                this.players.add(player);
                break;
            }
            counter += 1;
        }
    }

    public void setMyPlayer(Player myPlayer) {
        this.myPlayer = myPlayer;
    }

    public Player getMyPlayer() {
        return this.myPlayer;
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
            if (!player.getHand().isEmpty()){
                ProgramCard card = player.getHand().remove(0);
                // Set owner of this card
                card.setPlayer(player);
                cards.add(card);
            }
        }
        // Set host card
        if(!myPlayer.getHand().isEmpty()) {
            ProgramCard myCard = myPlayer.getHand().remove(0);
            myCard.setPlayer(myPlayer);
            cards.add(myCard);
        }


        Collections.sort(cards);

        return cards;
    }


}
