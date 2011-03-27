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
import edu.wpi.first.wpilibj.templates.MacroRecord;
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
    XboxGamepad.Stick minibotdeploymentcontrols;

    Solenoid solenoid;
    Arm arm;
    Jaguar jags[] = new Jaguar[8];
    MacroRecord recorder;

    Timer timer = new Timer();
    public double zeroT;
    public double epicT;
    private Jaguar minibot;
    private boolean placed = false;

    boolean record = true;
    double lastTime = 0;

    /*
     * This method will be called initally
     */
    public void init(){
        setArm();
        setDrive();
        setForklift();
    }
    public void setminibotdeploymentstick(XboxGamepad.Stick minibotstick){
        minibotdeploymentcontrols = minibotstick;
    }

    /*
     * This method will be called continously
     */
    public void continuous()
    {
            timer.start();

//        if(con1.Back.isPressed())
//        {
//            compressor.comp.start();
//        }
//        else
//        {
//            compressor.comp.stop();
//        }
        
        if(con2.LB.isPressed() && con2.RB.isPressed() && con2.rStick.isStickPressed())
        {
//            timer.reset();
//            timer.start();
//            if(timer.get()<24000000000.0)
//            {
            arm.getMotor().set(0);
                minibot.set(minibotdeploymentcontrols.getStickX() * Math.abs(minibotdeploymentcontrols.getStickX())*2);
//                placed =true;
//            }
//            else if(timer.get()>24000000000.0)
//            {
//                minibot.set(0);
//            }
            
        }else{
            minibot.set(0);
            setArm();

        }
        
        setDrive();
        setForklift();

        if(this.record){
            if(timer.getFPGATimestamp()-lastTime>.1)
            {
                this.recorder.record();
            }
        }
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

        jags[0] = drive.getFrontLeftJag();
        jags[1] = drive.getFrontRightJag();
        jags[2] = drive.getRearLeftJag();
        jags[3] = drive.getRearRightJag();
        jags[4] = forkLift.getForkLiftMotor1();
        jags[5] = forkLift.getForkLiftMotor2();
        jags[6] = arm.getMotor();
        jags[7] = minibot;

        recorder = new MacroRecord(jags);
        timer.start();
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
        jags[7].set(.5);
    }
}
