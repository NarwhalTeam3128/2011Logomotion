package edu.wpi.first.wpilibj.templates.Logic;
import edu.wpi.first.wpilibj.templates.Components.DriveTrain;
import edu.wpi.first.wpilibj.templates.Components.LineSensorManager;
import edu.wpi.first.wpilibj.templates.Components.ForkLift;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
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
    double moves[][] = {
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0}
    };


    int count;

    public Timer timer = new Timer();


    public int state;
    double autonX;
    double autonY;
    double autonRot;
    Jaguar jags[] = new Jaguar[8];
    private Jaguar minibot;

    public void setRobot(MainRobot r){
        drive = r.getDrive();
        lineSensors = r.getLineSensors();
        forkLift = r.getForkLift();
        minibot = r.getMinibot();
        arm = r.getArm();
        timer.start();
        count = 0;
        jags[0] = drive.getFrontLeftJag();
        jags[1] = drive.getFrontRightJag();
        jags[2] = drive.getRearLeftJag();
        jags[3] = drive.getRearRightJag();
        jags[4] = forkLift.getForkLiftMotor1();
        jags[5] = forkLift.getForkLiftMotor2();
        jags[6] = arm.getMotor();
        jags[7] = minibot;
    }

    public void init(){

    }

    public void periodic(){
        // Compressor no longer in use
        //compressor.checkPressure();
    }

    public void continuous()
    {

        if(timer.get()>25000000)
        {
            for(int x = 0; x<jags.length;x++)
            {
                jags[x].set(moves[count][x]);
            }
            count++;
        }
        if(state == 0)
        {
            autonY=1;
            followLine();
        }
        else if(state == 1)
        {
            //driveBackwardsABit();
        }
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
