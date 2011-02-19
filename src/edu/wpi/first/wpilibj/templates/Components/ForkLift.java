/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.Components;

import edu.wpi.first.wpilibj.templates.Components.XboxGamepad;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author Dennis Shen
 */
public class ForkLift {
    Jaguar ForkLiftMotor1;
    Jaguar ForkLiftMotor2;
    Encoder ForkLiftEncoder;
    double LowSettingEncodervalue;//Arbitrary encoder value for the Low Height 
    double MidSettingEncodervalue;//Arbitrary encoder value for the Mid Height
    double HighSettingEncodervalue;//Arbitrary encoder value for the High Height
    
    
    /*random channel for the motor. Change to whatever is configured
        * random encoder for the forklift
        */
    public ForkLift(Encoder NewEncoder)
    {
        ForkLiftMotor1 = new Jaguar(5);
        ForkLiftMotor2 = new Jaguar(6);
        ForkLiftEncoder = NewEncoder;
        ForkLiftEncoder.start();
        ForkLiftEncoder.reset();

    }
    /*
     * The method for the motor to lift the drawerslide to the low height setting
     * starts the motor at an arbitrary speed, when the encoder hits a test value,
     * the motor stops, and the encoder resets.
     * The same method is used for all other "Drive"
     */
    public void LowDrive()
    {
        if(ForkLiftEncoder.get()-LowSettingEncodervalue>3)
        {
            while(ForkLiftEncoder.get()-LowSettingEncodervalue>3)
            {
                ForkLiftMotor1.set(-.75);
                ForkLiftMotor2.set(-.75);//speed is arbitrary - can change if needed test value
            }
        }
        else if(ForkLiftEncoder.get()-LowSettingEncodervalue<-3)
        {
            while(ForkLiftEncoder.get()-LowSettingEncodervalue<-3)
            {
                ForkLiftMotor1.set(.75);
                ForkLiftMotor2.set(.75);//speed is arbitrary - can change if needed test value
            }
        }
        ForkLiftMotor1.set(0);
        ForkLiftMotor2.set(0);
    }
    public void MidDrive()
    {
        if(ForkLiftEncoder.get()-MidSettingEncodervalue>3)
        {
            while(ForkLiftEncoder.get()-MidSettingEncodervalue>3)
            {
                ForkLiftMotor1.set(-.75);
                ForkLiftMotor2.set(-.75);//speed is arbitrary - can change if needed test value
            }
        }
        else if(ForkLiftEncoder.get()-MidSettingEncodervalue<-3)
        {
            while(ForkLiftEncoder.get()-MidSettingEncodervalue<-3)
            {
                ForkLiftMotor1.set(.75);
                ForkLiftMotor2.set(.75);//speed is arbitrary - can change if needed test value
            }
        }
        ForkLiftMotor1.set(0);
        ForkLiftMotor2.set(0);
    }
    public void HighDrive()
    {
        if(ForkLiftEncoder.get()-HighSettingEncodervalue>3)
        {
            while(ForkLiftEncoder.get()-HighSettingEncodervalue>3)
            {
                ForkLiftMotor1.set(-.75);
                ForkLiftMotor2.set(-.75);//speed is arbitrary - can change if needed test value
            }
        }
        else if(ForkLiftEncoder.get()-HighSettingEncodervalue<-3)
        {
            while(ForkLiftEncoder.get()-HighSettingEncodervalue<-3)
            {
                ForkLiftMotor1.set(.75);
                ForkLiftMotor2.set(.75);//speed is arbitrary - can change if needed test value
            }
        }
        ForkLiftMotor1.set(0);
        ForkLiftMotor2.set(0);
    }

    public void setDrive(XboxGamepad.Stick s)
    {
        ForkLiftMotor1.set(s.getStickY());
        ForkLiftMotor2.set(s.getStickY());
    }
}
