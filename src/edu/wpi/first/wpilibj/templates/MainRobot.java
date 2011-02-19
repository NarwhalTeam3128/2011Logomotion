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
    Gyro gyro;
    LineSensorManager lineSensors;

    // Controls
    XboxGamepad con1;
    XboxGamepad con2;
    ForkLift forkLift;
    Arm arm;
    
    // Encoders
    Encoder frontLeftEncoder = new Encoder(1,1,2,2);
    Encoder frontRightEncoder = new Encoder(3,3,4,4);
    Encoder forkLiftEncoder = new Encoder(5,5,6,6);
    EncoderMangager EncoderManagerMain = new EncoderMangager();

    int[] lineSensorChannels;

    Autonomous autonomous;

    /**
     * Initialize the robots control parts
     */
    public MainRobot()
    {
        drive = new DriveTrain(1,3,2,4);
        lineSensors = new LineSensorManager(lineSensorChannels);
        //compressor = new BlowholePnumaticCompressorManager();

        // Controllers
        con1 = new XboxGamepad(1);
        con1.controllerSetup();

        con2 = new XboxGamepad(2);
        con2.controllerSetup();

        // Forklift
        forkLift = new ForkLift(forkLiftEncoder);

        // Arm
        arm = new Arm(7);
        arm.setStick(con2.rStick);

        // Autonomous Code
        autonomous = new PatrickAutonomous(this);
    }

    public DriveTrain getDrive(){
        return drive;
    }

    public LineSensorManager getLineSensors(){
        return lineSensors;
    }

    public XboxGamepad getController1(){
        return con1;
    }

    public XboxGamepad getController2(){
        return con2;
    }

    public ForkLift getForkLift(){
        return forkLift;
    }

    public Arm getArm(){
        return arm;
    }

    public Gyro getGyro(){
        return gyro;
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

    

    /**
     * This function is run when the robot is first enters teleop
     */
    public void teleopInit()
    {
        // Get X and Y Velocity from controller
        double xVelocity = con1.lStick.getStickX() * Math.abs(con1.lStick.getStickX());
        double yVelocity = con1.lStick.getStickY() * Math.abs(con1.lStick.getStickY());

        // Get Rotational Velocity from controller
        double rotationalVelocity = con1.rStick.getStickX() * Math.abs(con1.rStick.getStickX());

        // Get gyro angle
        double gyroAngle = gyro.getAngle();
        
        drive.setDrive_Mecanum(xVelocity, yVelocity, rotationalVelocity, gyroAngle);
    }

    public void teleopPeriodic()
    {
        compressor.checkPressure();
    }

    public void teleopContinuous()
    {

    }

    /*
     * Autonomous
     */

    public void autonomousInit()
    {
        autonomous.init();
    }

    public void autonomousContinuous()
    {
        autonomous.continous();

    }

    public void autonomousPeriodic()
    {
        autonomous.periodic();
    }
    

    public void disabledInit()
    {
        //dont care
    }

    public void disabledPeriodic()
    {
        //dont care
    }

    public void disabledContinuous()
    {
        //dont use
    }

    
    
}
