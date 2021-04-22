package inf112.balmerspeak.app.network.messages;

import inf112.balmerspeak.app.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class AllPlayersMsg implements Serializable {

    private ArrayList<Player> allPlayers;

    public AllPlayersMsg(ArrayList<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }

    public ArrayList<Player> get() {
        return this.allPlayers;
    }
}
