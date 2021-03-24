package inf112.balmerspeak.app.network.messages;

import inf112.balmerspeak.app.cards.ProgramCard;

import java.util.ArrayList;

public class HandMsg {

    // The list of cards chosen by player
    ArrayList<ProgramCard> cards;

    public HandMsg(ArrayList<ProgramCard> cards) {
        this.cards = cards;
    }

    public ArrayList<ProgramCard> getCards() {
        return this.cards;
    }


}
