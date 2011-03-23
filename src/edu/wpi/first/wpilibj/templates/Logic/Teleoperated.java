package edu.wpi.first.wpilibj.templates.Logic;
import edu.wpi.first.wpilibj.Jaguar;
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
    private Jaguar minibot;
    private boolean placed = false;
    boolean a = false;
    boolean b = false;
    boolean c = false;
    boolean d = false;
    boolean done = false;
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

//        if(con1.A.isPressed() && !a)
//        {
//            drive.FCompValues[0] -=.01;
//            a = true;
//        }
//        else
//        {
//            a = false;
//        }
//        if(con1.B.isPressed() && !b)
//        {
//            drive.FCompValues[1] -=.01;
//            b = true;
//        }
//        else
//        {
//            b = false;
//        }
//        if(con1.X.isPressed() && !c)
//        {
//            drive.FCompValues[2] -=.01;
//            c = true;
//        }
//        else
//        {
//            c = false;
//        }
//        if(con1.Y.isPressed() && !d)
//        {
//            drive.FCompValues[3] -=.01;
//            d = true;
//        }
//        else
//        {
//            d = false;
//        }
//        if(con1.Start.isPressed())
//        {
//            System.out.println("FCompValue 0 =="+drive.FCompValues[0]);
//            System.out.println("FCompValue 1 =="+drive.FCompValues[1]);
//            System.out.println("FCompValue 2 =="+drive.FCompValues[2]);
//            System.out.println("FCompValue 3 =="+drive.FCompValues[3]);
//            done = true;
//        }
//        if(con1.Back.isPressed())
//        {
//            compressor.comp.start();
//        }
//        else
//        {
//            compressor.comp.stop();
//        }
        
        if(con2.LB.isPressed() && con2.RB.isPressed() && con2.A.isPressed())
        {
//            timer.reset();
//            timer.start();
//            if(timer.get()<24000000000.0)
//            {
                minibot.set(-1);
//                placed =true;
//            }
//            else if(timer.get()>24000000000.0)
//            {
//                minibot.set(0);
//            }
            
        }else{
            minibot.set(0);
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
        minibot = r.getMinibot();
    }

    /*
     * Sets the Wheels in the right direction
     */
    public void setDrive(){
        drive.update();
//        if(!done)
//            drive.setDrive_Mecanum(1, 0, 0, 0);
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
