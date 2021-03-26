package inf112.balmerspeak.app.network.messages;

import com.esotericsoftware.kryonet.Serialization;
import inf112.balmerspeak.app.cards.ProgramCard;

import java.io.Serializable;
import java.util.ArrayList;

public class HandMsg implements Serializable {

    // The list of cards chosen by player
    ArrayList<ProgramCard> cards;

    public HandMsg(ArrayList<ProgramCard> cards) {
        this.cards = cards;
    }

    public ArrayList<ProgramCard> getCards() {
        return this.cards;
    }


}
