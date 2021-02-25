package inf112.balmerspeak.app.cards;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CardSelector extends JFrame implements ActionListener {

    private ArrayList<ProgramCard> differentCards;
    private ArrayList<ProgramCard> activeDeck;

    public CardSelector() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        //JComboBox comboBox = new JComboBox(getCards());

        this.pack();
        this.setVisible(true);
    }

    public ArrayList<ProgramCard> getCards(int amount) {
        ArrayList<ProgramCard> cards = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            cards.add(getCard());
        }
        return cards;
    }

    public ProgramCard getCard() {
        ProgramCard card = differentCards.remove(0);
        activeDeck.add(card);
        return card;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
