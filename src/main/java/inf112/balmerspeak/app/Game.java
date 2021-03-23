package inf112.balmerspeak.app;

import inf112.balmerspeak.app.board.Board;

import java.util.ArrayList;

public class Game {

    private Player player;
    private ArrayList<Player> players;
    private Board board;

    public Game(Player player) {
        this.player = player;
        this.players = new ArrayList<>();
        this.board = new Board("assets/map/map.tmx");
    }

    // The game loop which executes all stages of the game and exits when a winner is determined
    public void gameLoop() {
        // First, place robots
        placeRobotsAtStart();

    }

    // Places all players robots at their starting position
    public void placeRobotsAtStart() {
        // Place own robot
        board.placeRobot(player.getCoords().getValue0(), player.getCoords().getValue1());
        for (Player player : players) {
            board.placeRobot(player.getCoords().getValue0(), player.getCoords().getValue1());
        }
    }

    public void addPlayer(Player newPlayer) {
        this.players.add(newPlayer);
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

}
