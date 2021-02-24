package inf112.balmerspeak.app.cards;

import java.util.*;

public class Deck {

    public static final int hand_size = 9;

    public int deck_size = 84;

    public ArrayList<ProgramCard> deckInUse;

    //Lage deck med å bruke: public Deck deck = new Deck();

    public Deck(List<ProgramCard> cards) {
        createDeck();
        //this.deckInUse = new ArrayList<>();
        //this.deckInUse.addAll(cards);
    }

    public void createDeck() {
        for (int i = 0; i < 18; i++) {
            deckInUse.add(new MovementCard(1,1,"Move one"));
            deckInUse.add(new RotationCard(2,Rotation.right,false,"Rotate right"));
            deckInUse.add(new RotationCard(2,Rotation.left, false,"Rotate left"));
        }
        for (int i = 0; i < 12; i++) {
            deckInUse.add(new MovementCard(1,2,"Move two"));
        }
        for (int i = 0; i < 6; i++) {
            deckInUse.add(new MovementCard(1,3,"Move three"));
            deckInUse.add(new MovementCard(2,-1,"Back up"));
            deckInUse.add(new RotationCard(2,Rotation.uturn, false,"U-turn"));
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(deckInUse);
    }

    public void clearDeck() {
        deckInUse.clear();
    }
}
