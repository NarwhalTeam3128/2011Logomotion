package edu.wpi.first.wpilibj.templates.Logic;
import edu.wpi.first.wpilibj.templates.Logic.Teleoperated;
import edu.wpi.first.wpilibj.templates.Components.DriveTrain;
import edu.wpi.first.wpilibj.templates.Components.XboxGamepad;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.templates.Components.Arm;
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

    Solenoid solenoid;
    Arm arm;

    Timer timer = new Timer();
    public double zeroT;
    public double epicT;

    /*
     * This method will be called initally
     */
    public void init(){

        

        
        setArm();
        setDrive();
        setForklift();
    }


    /*
     * This method will be called continously
     */
    public void continuous(){
        con1.pad.getLeftRight();
        con1.pad.getUpDown();

        if(con1.A.isPressed()){
            solenoid.set(true);
        }else{
            solenoid.set(false);
        }
        if(con1.Back.isPressed())
        {
            compressor.comp.start();
        }
        else
        {
            compressor.comp.stop();
        }
        if(con2.LB.isPressed() && con2.RB.isPressed() && con2.B.isPressed())
        {
            try {
                timer.wait(300000000);
                deployMinibot();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        setArm();
        setDrive();
        setForklift();
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
        arm = r.getArm();
        solenoid = r.getSolenoid();
    }

    /*
     * Sets the Wheels in the right direction
     */
    public void setDrive(){

        // Get X and Y Velocity from controller
        double xVelocity = con1.lStick.getStickX() * Math.abs(con1.lStick.getStickX());
        double yVelocity = con1.lStick.getStickY() * Math.abs(con1.lStick.getStickY());

        // Get Rotational Velocity from controller
        double rotationalVelocity = con1.rStick.getStickX() * Math.abs(con1.rStick.getStickX());

        // Get gyro angle
        double gyroAngle = gyro.getAngle();

        drive.setDrive_Mecanum(xVelocity, yVelocity, rotationalVelocity, gyroAngle);

    }

    public void setForklift(){
        forkLift.update();
    }

    public void setArm(){
        arm.update();
    }

    public void deployMinibot()
    {
        
    }
}
