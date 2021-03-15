package inf112.balmerspeak.app.board;

import inf112.balmerspeak.app.robot.Direction;
import inf112.balmerspeak.app.robot.Robot;

import java.util.ArrayList;

public class Pusher {
    private ArrayList<Integer> roundNumbers;
    private Direction pusherDirection;
    private int x;
    private int y;

    public Pusher(ArrayList<Integer> roundNumbers,Direction pusherDirection,int x, int y){
        this.roundNumbers = roundNumbers;
        this.pusherDirection = pusherDirection;
        this.x = x;
        this.y = y;
    }

    public void pushRobot(Robot robot){
        //Må ha ein && i if statements, som også sjekker om runder matcher et nummer i roundNumber
        if (pusherDirection.equals(Direction.NORTH)){
            robot.set(robot.getX(), robot.getY()+1);
            //Må kalle robotens move for å faktisk flytte den
        }else if (pusherDirection.equals(Direction.EAST)){
            robot.set(robot.getX()+1, robot.getY());
        }else if (pusherDirection.equals(Direction.SOUTH)){
            robot.set(robot.getX(), robot.getY()-1);
        }else if (pusherDirection.equals(Direction.WEST)){
            robot.set(robot.getX()-1, robot.getY());
        }
    }
}
