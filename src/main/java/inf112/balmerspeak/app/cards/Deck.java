package inf112.balmerspeak.app.cards;

import java.io.Serializable;
import java.util.*;

public class Deck implements Serializable {

    public static final int hand_size = 9;

    public int deck_size = 84;

    public ArrayList<ProgramCard> deckInUse = new ArrayList<>();

    public Deck() {
        createDeck();
        shuffleDeck();
    }

    public void createDeck() {
        for (int i = 0; i < 18; i++) {
            deckInUse.add(new MovementCard(1,1,"Move one"));
            deckInUse.add(new RotationCard(2,Rotation.right,"Rotate right"));
            deckInUse.add(new RotationCard(2,Rotation.left,"Rotate left"));
        }
        for (int i = 0; i < 12; i++) {
            deckInUse.add(new MovementCard(2,2,"Move two"));
        }
        for (int i = 0; i < 6; i++) {
            deckInUse.add(new MovementCard(3,3,"Move three"));
            deckInUse.add(new MovementCard(2,-1,"Back up"));
            deckInUse.add(new RotationCard(2,Rotation.uturn,"U-turn"));
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(deckInUse);
    }

    public void clearDeck() {
        deckInUse.clear();
    }

    public ArrayList<ProgramCard> getCards(int amount) {
        ArrayList<ProgramCard> cards = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            cards.add(deckInUse.remove(0));
            if(deckInUse.size() == 0) {
                createDeck();
                shuffleDeck();
            }
        }
        return cards;
    }

    public ProgramCard getCard() {
        ProgramCard card = deckInUse.get(0);
        deckInUse.add(card);
        deckInUse.remove(card);
        return card;
    }
}
