package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author tylercarter
 */
public class GenericDisabled extends Disabled {

    /*
     * This method will be called initally
     */
    public void init(){
        throw new RuntimeException("Not Implemented");
    }

    /*
     * This method will be called continously
     */
    public void continous(){
        throw new RuntimeException("Not Implemented");
    }

    /*
     * This method will be called periodically
     */
    public void periodic(){
        throw new RuntimeException("Not Implemented");
    }

    /*
     * Sets the Main Robot
     */
    public void setRobot(MainRobot r){
        throw new RuntimeException("Not Implemented");
    }

}
