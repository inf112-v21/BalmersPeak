package inf112.balmerspeak.app.menu;

import inf112.balmerspeak.app.cards.CardGetter;
import inf112.balmerspeak.app.cards.ProgramCard;

import java.util.ArrayList;
import java.util.List;

public class HandGetter {

    private int max_hand_size = 9;

    private CardImage[] selection;

    private CardGetter programmingCards;

    public HandGetter() {
        selection = new CardImage[5];

    }

    public void createCardButtons() {
        ArrayList<ArrayList<ProgramCard>> cards = programmingCards.getCardList();

        for (int i = 0; i < cards.size(); i++) {
            ArrayList<ProgramCard> card = cards.get(i);
        }
    }

    public CardImage createCardButton(ProgramCard card) {
        //get texture
        //Image image = new Image(cardTexture);
        //return new CardImage(image,card)
        return null;
    }
}
