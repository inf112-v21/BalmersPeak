package inf112.balmerspeak.app.cards;

import java.util.*;

public class Deck {

    public static final int hand_size = 9;

    public int deck_size = 84;

    private int priorityMove1 = 490;
    private int priorityRotateRight = 80;
    private int priorityRotateLeft = 70;
    private int priorityMove2 = 670;
    private int priorityMove3 = 790;
    private int priorityBackup = 430;
    private int priorityUturn = 10;

    public ArrayList<ProgramCard> deckInUse = new ArrayList<>();

    public Deck() {
        createDeck();
        shuffleDeck();
    }

    public void createDeck() {
        for (int i = 0; i < 18; i++) {
            deckInUse.add(new MovementCard(priorityMove1,1,"1move"));
            deckInUse.add(new RotationCard(priorityRotateRight,Rotation.right,"rotateright"));
            deckInUse.add(new RotationCard(priorityRotateLeft,Rotation.left,"rotateleft"));
            priorityMove1+=10;
            priorityRotateRight+=20;
            priorityRotateLeft+=20;
        }
        for (int i = 0; i < 12; i++) {
            deckInUse.add(new MovementCard(priorityMove2,2,"2move"));
            priorityMove2+=10;
        }
        for (int i = 0; i < 6; i++) {
            deckInUse.add(new MovementCard(priorityMove3,3,"3move"));
            deckInUse.add(new MovementCard(priorityBackup,-1,"backup"));
            deckInUse.add(new RotationCard(priorityUturn,Rotation.uturn,"uturn"));
            priorityMove3+=10;
            priorityBackup+=10;
            priorityUturn+=10;

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
