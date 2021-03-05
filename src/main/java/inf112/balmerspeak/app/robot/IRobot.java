package inf112.balmerspeak.app.robot;

public interface IRobot {

    int getX();

    int getY();

    int getHealth();

    int getLives();

    Direction getDirection();

    void set(int x, int y);

    void setHealth(int x);

    void setLives(int x);

    boolean isAlive();

    void setDirection(Direction dir);
}
