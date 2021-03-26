package inf112.balmerspeak.app.network.messages;

import java.io.Serializable;

public class NumPlayers implements Serializable {

    private final int numPlayers;

    public NumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int getNumPlayers() {
        return this.numPlayers;
    }


}
