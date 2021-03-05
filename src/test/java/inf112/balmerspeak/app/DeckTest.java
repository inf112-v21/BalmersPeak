package inf112.balmerspeak.app;

import inf112.balmerspeak.app.cards.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DeckTest {

    Deck deck = new Deck();

    @Test
    public void deckIs84cards() {
        assertEquals(84, deck.deckInUse.size());
    }

    @Test
    public void createsNewDeckIfEmpty() {
        List<ProgramCard> cards = deck.getCards(84);
        List<ProgramCard> getCard = deck.getCards(1);

        assertEquals(83,deck.deckInUse.size());
    }

    @Test
    public void deckContainsCorrectCards() {
        int movementCards = 0;
        int rotationCards = 0;
        for(ProgramCard card : deck.getCards(84)) {
            if(card instanceof MovementCard) movementCards++;
            else if(card instanceof RotationCard) rotationCards++;
        }
        assertEquals(42, movementCards);
        assertEquals(42, rotationCards);
    }
}
