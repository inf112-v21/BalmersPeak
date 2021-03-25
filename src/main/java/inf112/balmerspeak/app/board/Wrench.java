package inf112.balmerspeak.app.board;

import inf112.balmerspeak.app.robot.Robot;

public class Wrench {
    public int x;
    public int y;
    public int numberOfWrenches;

    public Wrench(int x, int y, int numberOfWrenches) {
        this.x = x;
        this.y = y;
        this.numberOfWrenches = numberOfWrenches;
    }

    public void fixDmg(Robot player){
        if (player.getHealth()<10)
            player.setHealth(player.getHealth() + 1);
        setNewSpawn(player);
    }

    public void setNewSpawn(Robot player){ player.setSpawnCoordinates(x,y); }

    public int getX() {return x;}

    public int getY() {return y;}
}
