package inf112.balmerspeak.app.robot;

public class Robot implements IRobot{

    private int x;
    private int y;
    private int hp = 8;
    private int ll = 3;
    private Direction direction;

    public Robot(int xCoord, int yCoord, Direction dir){
        x = xCoord;
        y = yCoord;
        direction = dir;
    }


    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getHealth() {
        return this.hp;
    }

    @Override
    public int getLives() {
        return this.ll;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setHealth(int x) {
        this.hp = x;
    }

    @Override
    public void setLives(int x) {
        this.ll = x;
    }

    @Override
    public boolean isAlive() {
        return (getHealth() == 0 || getLives() == 0);
    }
}
