package inf112.balmerspeak.app;


import inf112.balmerspeak.app.robot.Robot;

import java.util.List;


public class Gear {

    private List<Robot> robots;
    private int x;
    private int y;
    private Rotate rotate;


    public Gear(int x, int y, Rotate rotate){
        this.x = x;
        this.y = y;
        this.rotate = rotate;
    }


    public int getX(){ return x; }

    public int getY(){ return y; }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void rotateGears() {
        for (Robot robot : robots) {
            if ((boolean) MapHandler.checkGears(robot)) {
                Rotate rotate = getRotate();
                if(rotate == Rotate.RIGHT) {
                    rotateRight();
                }
                else { rotateLeft();

                }

            }


        }
    }
    public Object rotateRight(){
        return null;
    }

    public Object rotateLeft(){
        return null;
    }

    public Rotate getRotate(){ return rotate; }
}
