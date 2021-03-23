package inf112.balmerspeak.app;

import inf112.balmerspeak.app.menu.GameScreen;

import java.util.ArrayList;

public class Game {

    private Player myPlayer;
    private ArrayList<Player> players;
    private GameScreen gameScreen;


    public Game(Player player, GameScreen gameScreen) {
        this.myPlayer = player;
        this.players = new ArrayList<>();
        this.gameScreen = gameScreen;
    }

    public Game(GameScreen screen) {
        this.players = new ArrayList<>();
        this.gameScreen = screen;
    }

    // The game loop which executes all stages of the game and exits when a winner is determined
    public void gameLoop() {
        // First, place robots
        placeRobotsAtStart();

    }

    // Places all players robots at their starting position
    public void placeRobotsAtStart() {
        // Place own robot
        gameScreen.getBoard().placeRobot(myPlayer.getCoords().getValue0(), myPlayer.getCoords().getValue1());
        // Place everyone else's
        for (Player player : players) {
            gameScreen.getBoard().placeRobot(player.getCoords().getValue0(), player.getCoords().getValue1());
        }
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

    public Player getMyPlayer() {
        return this.myPlayer;
    }

    public void setGameScreen(GameScreen screen) {
        this.gameScreen = screen;
    }

}
