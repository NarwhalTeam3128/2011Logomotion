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

    double autonX;
    double autonY;
    double autonRot;

    boolean ubertube_placed = false;

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

    public void continous(){
        
        if (ubertube_placed)
        {

        }
        else if(lineSensors.sensors[1].getValue() & lineSensors.sensors[2].getValue() & lineSensors.sensors[3].getValue())
        {
            placeUberTube();
        }
        else if(lineSensors.getlinelocation() == null ? "left" == null : lineSensors.getlinelocation().equals("left"))
        {
            autonY = -0.3;
        }
        else if (lineSensors.getlinelocation() == null ? "right" == null : lineSensors.getlinelocation().equals("right")){
            autonY = 0.3;
        }
        else if (lineSensors.getlinelocation() == null ? "ahead" == null : lineSensors.getlinelocation().equals("ahead")){
            autonX = 0.3;
            autonY = 0;
        }
        
    }

    /*
     * This Function places the ubertube in autoimus
     */
    public void placeUberTube()
    {
        //codehere
        ubertube_placed = true ;

    }

}
