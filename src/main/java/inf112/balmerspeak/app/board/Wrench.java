package inf112.balmerspeak.app.board;

import inf112.balmerspeak.app.robot.Robot;

public class Wrench {
    public int numberOfWrenches;
    public Wrench(int numberOfWrenches) {
        this.numberOfWrenches = numberOfWrenches;
    }
    public void fixDmg(Robot robot){
        if (robot.getHealth()<10) {
            robot.setHealth(robot.getHealth() + 1);
            System.out.println("You healed 1dmg");

        }else{
            System.out.println("Your Hp is full");
        }
    }



}
