package edu.wpi.first.wpilibj.templates.Logic;
import edu.wpi.first.wpilibj.templates.Logic.Teleoperated;
import edu.wpi.first.wpilibj.templates.Components.DriveTrain;
import edu.wpi.first.wpilibj.templates.Components.XboxGamepad;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.templates.Components.CompressorManager;
import edu.wpi.first.wpilibj.templates.Components.ForkLift;
import edu.wpi.first.wpilibj.templates.MainRobot;

/**
 *
 * @author tylercarter
 */
public class Teleoperated extends ICPProtocol{

    DriveTrain drive;
    ForkLift forkLift;
    CompressorManager compressor;

    // Sensors
    Gyro gyro;

    // Controls
    XboxGamepad con1;
    XboxGamepad con2;

    /*
     * This method will be called initally
     */
    public void init(){

        compressor.comp.start();
        forkLift.ForkLiftMotor1.set(con2.lStick.getStickY()*Math.abs(con2.lStick.getStickY())*-1);
        forkLift.ForkLiftMotor2.set(con2.lStick.getStickY()*Math.abs(con2.lStick.getStickY()));

        // Get X and Y Velocity from controller
        double xVelocity = con1.lStick.getStickX() * Math.abs(con1.lStick.getStickX());
        double yVelocity = con1.lStick.getStickY() * Math.abs(con1.lStick.getStickY());

        // Get Rotational Velocity from controller
        double rotationalVelocity = con1.rStick.getStickX() * Math.abs(con1.rStick.getStickX());

        // Get gyro angle
        double gyroAngle = gyro.getAngle();

        drive.setDrive_Mecanum(xVelocity, yVelocity, rotationalVelocity, gyroAngle);



    }

    /*
     * This method will be called continously
     */
    public void continous(){

    }

    /*
     * This method will be called periodically
     */
    public void periodic(){
        //compressor.checkPressure();
    }

    /*
     * Sets the Main Robot
     */
    public void setRobot(MainRobot r){
        drive = r.getDrive();
        con1 = r.getController1();
        con2 = r.getController2();
        gyro = r.getGyro();
        forkLift = r.getForkLift();
        compressor = r.getCompressor();
    }
}
