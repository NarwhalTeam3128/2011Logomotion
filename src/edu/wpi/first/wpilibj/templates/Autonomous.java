package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author tylercarter
 */
public abstract class Autonomous {

    MainRobot robot;

    public Autonomous(MainRobot r){
        robot = r;
    }

    protected MainRobot getRobot(){
        return robot;
    }

    public abstract void init();

    /*
     * This method will be called continously
     */
    public abstract void continous();

    /*
     * This method will be called periodically
     */
    public abstract void periodic();


}
