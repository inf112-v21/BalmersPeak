package inf112.balmerspeak.app;

import java.util.ArrayList;

public class Game {

    private Player player;
    private ArrayList<Player> players;

    public Game(Player player) {
        this.player = player;
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player newPlayer) {
        this.players.add(newPlayer);
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

}
