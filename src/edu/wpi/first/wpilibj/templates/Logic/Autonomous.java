package edu.wpi.first.wpilibj.templates.Logic;
import edu.wpi.first.wpilibj.templates.Components.DriveTrain;
import edu.wpi.first.wpilibj.templates.Components.LineSensorManager;
import edu.wpi.first.wpilibj.templates.Components.ForkLift;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.templates.MainRobot;

/**
 *
 * @author tylercarter
 */
public class Autonomous extends ICPProtocol{

    DriveTrain drive;
    LineSensorManager lineSensors;
    ForkLift forkLift;
    Gyro gyro;

    public int state;

    double autonX;
    double autonY;
    double autonRot;

    public void setRobot(MainRobot r){
        drive = r.getDrive();
        lineSensors = r.getLineSensors();
        forkLift = r.getForkLift();
    }

    public void init(){
        forkLift.HighDrive();
        drive.setDrive_Mecanum(autonX, autonY, autonRot, gyro.getAngle());
    }

    public void periodic(){
        // Compressor no longer in use
        //compressor.checkPressure();
    }

    public void continuous()
    {
        if(state == 0)
        {
            autonY=1;
            followLine();
        }
        else if(state == 1)
        {
            
        }
    }

    /*
     * This Function follows the line for quite a few feet.
     */
    public void followLine()
    {
        //Stop Spot
        if(lineSensors.getlinelocation()==0)
        {
            autonRot = 0.0;
            autonY = 0;
            state = 1;
        }
        //Left+Mid
        else if(lineSensors.getlinelocation()==1)
        {
            autonRot = -1;
        }
        //Right+Mid
        else if(lineSensors.getlinelocation()==2)
        {
            autonRot = 1;
        }
        //Left Only
        else if(lineSensors.getlinelocation()==3)
        {
            autonRot = -1;
        }
        //Right Only
        else if(lineSensors.getlinelocation()==4)
        {
            autonRot = 1;
        }
        //Mid Only
        else if(lineSensors.getlinelocation()==5)
        {
            autonRot = 0;
        }
        drive.setDrive_Mecanum(autonY,0,autonRot,gyro.getAngle());
    }

}
