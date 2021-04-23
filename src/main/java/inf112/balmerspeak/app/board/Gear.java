package inf112.balmerspeak.app.board;



import inf112.balmerspeak.app.cards.Rotation;



public class Gear {


    private int x;
    private int y;
    private Rotation rotation;


    public Gear(int x, int y, Rotation rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rotation getRotation() {
        return rotation;
    }

}