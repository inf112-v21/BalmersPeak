package inf112.balmerspeak.app.board;

import inf112.balmerspeak.app.robot.Robot;

public class Wrench {
    boolean isEmpty;
    public Wrench(){ }
    public void fixDmg(Robot robot){
        if (robot.getHealth()<10) {
            robot.setHealth(robot.getHealth() + 1);
            System.out.println("You healed 1dmg");

        }else{
            System.out.println("Your Hp is full");
        }
    }



}
