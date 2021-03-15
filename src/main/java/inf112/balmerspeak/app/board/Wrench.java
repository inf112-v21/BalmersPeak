package inf112.balmerspeak.app.board;

import inf112.balmerspeak.app.robot.Robot;

public class Wrench {
    public int numberOfWrenches;
    public int x;
    public int y;

    public Wrench(int numberOfWrenches, int x, int y) {
        this.numberOfWrenches = numberOfWrenches;
        this.x = x;
        this.y = y;
    }

    public void fixDmg(Robot player){
        if (player.getHealth()<10) {
            player.setHealth(player.getHealth() + 1);
            setNewSpawn(player);
            System.out.println("You healed 1dmg");
        }else{
            setNewSpawn(player);
            System.out.println("Your Hp is full");
        }
    }

    public void setNewSpawn(Robot player){ player.setSpawnCoordinates(x,y); }

    public int getX() {return x;}

    public int getY() {return y;}


}
