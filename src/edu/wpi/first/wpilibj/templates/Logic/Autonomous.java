package edu.wpi.first.wpilibj.templates.Logic;
import edu.wpi.first.wpilibj.templates.Components.DriveTrain;
import edu.wpi.first.wpilibj.templates.Components.LineSensorManager;
import edu.wpi.first.wpilibj.templates.Components.ForkLift;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.templates.Components.Arm;
import edu.wpi.first.wpilibj.templates.Components.XboxGamepad.Button;
import edu.wpi.first.wpilibj.templates.MainRobot;

/**
 *
 * @author tylercarter
 */
public class Autonomous extends ICPProtocol{

    DriveTrain drive;
    LineSensorManager lineSensors;
    ForkLift forkLift;
    Arm arm;
    Gyro gyro;

    public double timer;
    public Timer timer2;

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
//        forkLift.HighDrive();
//        drive.setDrive_Mecanum(autonX, autonY, autonRot, gyro.getAngle());
//        timer = Timer.getFPGATimestamp();
//        timer2.start();
    }

    public void periodic(){
        // Compressor no longer in use
        //compressor.checkPressure();
    }

    public void continuous()
    {
//        if(state == 0)
//        {
//            if(timer2.get()<30000000000.0)//3 seconds it follows line after that it stops and sets state to 1
//            {
//                autonY=1;
//                followLine();
//            }
//            else{
//                drive.setDrive_Mecanum(0, 0, 0, 0);//stop
//                state=1;
//            }
//        }
//        else if(state == 1)//when state is 1 activate forklift
//        {
//            timer2.reset();
//            timer2.start();
//            if(timer2.get()<40000000000.0)//4 seconds
//            {
//                forkLift.ForkLiftMotor1.set(1);
//                forkLift.ForkLiftMotor2.set(-1);
//            }
//            else if(timer2.get()>40000000000.0 && timer2.get()<80000000000.0)//all values are pretty arbitrary
//            {
//                arm.motor.set(.25);
//            }
//
//        }
    }

    /*
     * This Function follows the line for quite a few feet.
     */
    public void followLine()
    {
        //goes forward
        if(lineSensors.getlinelocation()==0)
        {
            autonRot = 0.0;
            autonY = 1;
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
        //if no sensors are being called stop
        else if(lineSensors.getlinelocation()==6)
        {
            autonRot =0;
            autonY=0;

        }
        drive.setDrive_Mecanum(autonY,0,autonRot,gyro.getAngle());
    }



}
