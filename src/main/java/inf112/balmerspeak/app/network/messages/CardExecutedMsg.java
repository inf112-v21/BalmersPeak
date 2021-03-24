package inf112.balmerspeak.app.network.messages;

import inf112.balmerspeak.app.cards.ProgramCard;

public class CardExecutedMsg {

    private int playerid;
    private ProgramCard card;

    public CardExecutedMsg(int playerid, ProgramCard card) {
        this.playerid = playerid;
        this.card = card;
    }

    public int getPlayerId() {
        return this.playerid;
    }

    public ProgramCard getCard() {
        return this.card;
    }
}
