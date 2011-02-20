/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class MainRobot extends IterativeRobot
{
    DriveTrain drive;
    CompressorManager compressor;

    // Sensors
    Gyro gyroZ;
    LineSensorManager lineSensors;

    // Controls
    XboxGamepad con1;
    XboxGamepad con2;
    ForkLift forkLift;
    Arm arm;
    
    // Encoders
    //Encoder frontLeftEncoder = new Encoder(1,1,2,2);
    //Encoder frontRightEncoder = new Encoder(3,3,4,4);
    //Encoder forkLiftEncoder = new Encoder(5,5,5,6);
    //EncoderMangager EncoderManagerMain = new EncoderMangager();

    int[] lineSensorChannels;
    double autonX;
    double autonY;
    double autonRot;
    boolean ubertube_placed = false;

    /**
     * Initialize the robots control parts
     */
    public MainRobot()
    {
        drive = new DriveTrain(1,3,2,4);
        //lineSensors = new LineSensorManager(lineSensorChannels);
        //compressor = new BlowholePnumaticCompressorManager();

        // Controllers
        con1 = new XboxGamepad(1);
        con1.controllerSetup();

        con2 = new XboxGamepad(2);
        con2.controllerSetup();

        // Forklift
        forkLift = new ForkLift();

        // Arm
        arm = new Arm(7);
        arm.setStick(con2.rStick);

        gyroZ = new Gyro(1);
        compressor = new CompressorManager(6,5,4,1);
    }
    

    /**
     * This function is run when the robot is first started up
     */
    public void robotInit()
    {
//        EncoderManagerMain.addEncoder(frontLeftEncoder);
//        EncoderManagerMain.addEncoder(frontRightEncoder);
//        EncoderManagerMain.addEncoder(rearLeftEnc);
//        EncoderManagerMain.addEncoder(rearRightEnc);

    }
    /**
     * This function is run when the robot enters disabled mode
     */
    public void disabledInit()
    {
        //dont care
    }

    /**
     * This function is run when the robot is first enters autonomous mode
     */
    public void autonomousInit()
    {
        forkLift.HighDrive();
        drive.setDrive_Mecanum(autonX, autonY, autonRot,gyroZ.getAngle());
    }

    /**
     * This function is run when the robot is first enters teleop
     */
    public void teleopInit()
    {


        //Relay r = new Relay(6,1);
        //r.set(Relay.Value.kForward);
        

        
    }

    /**
     * This function is called periodically(every 1-2 seconds) during disabled mode
     */
    public void disabledPeriodic()
    {
        //dont care
    }

    /**
     * This function is called periodically(every 1-2 seconds) during autonomous
     */
    public void autonomousPeriodic()
    {
        //compressor.comp.start();
    }

    /**
     * This function is called periodically(every 1-2 seconds) during teleop
     */
    public void teleopPeriodic()
    {
        //compressor.comp.start();
    }

    /**
     * This function is called continuously(really fast) during disabled mode
     */
    public void disabledContinuous()
    {
        //dont use
    }

    /**
     * This function is called continuously(really fast) during autonomous
     */
    public void autonomousContinuous()
    {
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

    /**
     * This function is called continuously(really fast) during teleop
     */

    public void teleopContinuous()
    {
        compressor.comp.start();
        forkLift.ForkLiftMotor1.set(con2.lStick.getStickY()*Math.abs(con2.lStick.getStickY())*-1);
        forkLift.ForkLiftMotor2.set(con2.lStick.getStickY()*Math.abs(con2.lStick.getStickY()));
        
        // Get X and Y Velocity from controller
        double xVelocity = con1.lStick.getStickX() * Math.abs(con1.lStick.getStickX());
        double yVelocity = con1.lStick.getStickY() * Math.abs(con1.lStick.getStickY());

        // Get Rotational Velocity from controller
        double rotationalVelocity = con1.rStick.getStickX() * Math.abs(con1.rStick.getStickX());

        // Get gyro angle
        double gyroAngle = gyroZ.getAngle();

        drive.setDrive_Mecanum(xVelocity, yVelocity, rotationalVelocity, gyroAngle);

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
