/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author patrickmartin
 */
public class DumbAutonomous extends ICPProtocol{
    boolean norepeat=true;
    DriveTrain drive;
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
        timer.reset();
        timer.start();
        forkLift.HighDrive();

    }

    public void periodic(){
        // Compressor no longer in use
        //compressor.checkPressure();
    }

    public void continuous()
    {
        if(timer.get()> 1000){
            if (norepeat=false){
                arm.getMotor().set(0);
                timer.stop();
                timer.reset();
            }
            double clock =timer.get();
            drive.setDrive_Mecanum(0,0,0,gyro.getAngle());
            if (timer.get()>clock & norepeat==true){
                timer.stop();
                timer.reset();
                timer.start();
                arm.getMotor().set(0.1);
                norepeat=false;

            }
            

        }else if (norepeat==false){arm.getMotor().set(-0.1);}
        else{drive.setDrive_Mecanum(0.3, 0, 0, gyro.getAngle());}
    }




}
