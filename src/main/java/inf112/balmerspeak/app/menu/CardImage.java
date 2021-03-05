package inf112.balmerspeak.app.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import inf112.balmerspeak.app.cards.ProgramCard;

public class CardImage {

    private Image image;
    private ProgramCard card;

    public CardImage(Image image, ProgramCard card) {
        this.card = card;
        this.image = image;
    }

    public ProgramCard getCard() {
        return card;
    }

    public Image getImage() {
        return image;
    }
}
