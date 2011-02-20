package edu.wpi.first.wpilibj.templates.Logic;

import edu.wpi.first.wpilibj.templates.MainRobot;

/**
 *
 * @author tylercarter
 */
public abstract class ICPProtocol {

    /*
     * This method will be called initally
     */
    public abstract void init();

    /*
     * This method will be called continously
     */
    public abstract void continuous();

    /*
     * This method will be called periodically
     */
    public abstract void periodic();

    /*
     * Sets the Main Robot
     */
    public abstract void setRobot(MainRobot r);
}
