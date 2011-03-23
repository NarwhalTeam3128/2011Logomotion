/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.Components;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author MichelleX
 */
public class Calibrator 
{
    private Encoder frontLeftEnc;
    private Encoder frontRightEnc;
    private Encoder backLeftEnc;
    private Encoder backRightEnc;
    private DriveTrain drive;
    private int lowestSpeed;
    int state = 0;
    public Timer timer;
    public boolean forward = true;

    public Calibrator(Encoder fl, Encoder fr, Encoder bl, Encoder br, DriveTrain dt)
    {
        frontLeftEnc = fl;
        frontRightEnc = fr;
        backLeftEnc = bl;
        backRightEnc = br;
        drive = dt;
        lowestSpeed = 0;
        timer.start();
    }

    public void calibrate()
    {
        boolean t = true;
        boolean b = true;
        if(timer.get()<=120000000000.0)//120 secs
        {
            if(b)
            {
                frontLeftEnc.stop();
                frontRightEnc.stop();
                backLeftEnc.stop();
                backRightEnc.stop();
                frontLeftEnc.reset();
                frontRightEnc.reset();
                backLeftEnc.reset();
                backRightEnc.reset();
                frontLeftEnc.start();
                frontRightEnc.start();
                backLeftEnc.start();
                backRightEnc.start();
                b=false;
            }

            drive.setDrive_Mecanum(0, 1, 0, 0);
            if(timer.get()>=10000000000.0 && timer.get()<1100000000000.0)//10 secs to 11 secs
            {
                getLowest();
            }
            else if(timer.get()>=1100000000000.0)
            {
                checkSpeeds();
            }
        }
        else if(timer.get()>120000000000.0)
        {
            if(t)
            {
                frontLeftEnc.stop();
                frontRightEnc.stop();
                backLeftEnc.stop();
                backRightEnc.stop();
                frontLeftEnc.reset();
                frontRightEnc.reset();
                backLeftEnc.reset();
                backRightEnc.reset();
                frontLeftEnc.start();
                frontRightEnc.start();
                backLeftEnc.start();
                backRightEnc.start();
                forward = false;
                t=false;
            }
            drive.setDrive_Mecanum(0, -1, 0, 0);
            
            if(timer.get()>=130000000000.0 && timer.get()<13100000000000.0)//10 secs to 11 secs
            {
                getLowest();
            }
            else if(timer.get()>=13100000000000.0)
            {
                checkSpeeds();
            }
        }
        else
        {
            drive.setDrive_Mecanum(0,0,0,0);
            timer.stop();
            timer.reset();
            System.out.println("FCompValue 0 =="+drive.FCompValues[0]);
            System.out.println("FCompValue 1 =="+drive.FCompValues[1]);
            System.out.println("FCompValue 2 =="+drive.FCompValues[2]);
            System.out.println("FCompValue 3 =="+drive.FCompValues[3]);
            System.out.println("RCompValue 0 =="+drive.FCompValues[0]);
            System.out.println("RCompValue 1 =="+drive.FCompValues[1]);
            System.out.println("RCompValue 2 =="+drive.FCompValues[2]);
            System.out.println("RCompValue 3 =="+drive.FCompValues[3]);
        }
    }

    public void checkSpeeds()
    {
        double flspeed = Math.abs(frontLeftEnc.getRate());
        double frspeed = Math.abs(frontRightEnc.getRate());
        double blspeed = Math.abs(backLeftEnc.getRate());
        double brspeed = Math.abs(backRightEnc.getRate());

        double[] encoderspeeds = {flspeed, frspeed, blspeed, brspeed};
        for(int x = 0; x < encoderspeeds.length; x++)
        {
            if(x!= lowestSpeed)
            {
                if(Math.abs(encoderspeeds[x]-encoderspeeds[lowestSpeed])>2.5)
                {
                    if(forward)
                    {
                        drive.FCompValues[x] -= .01;
                    }
                    else
                    {
                        drive.RCompValues[x] -= .01;
                    }
                }
            }
        }
    }

    public void getLowest()
    {
        double flspeed = Math.abs(frontLeftEnc.getRate());
        double frspeed = Math.abs(frontRightEnc.getRate());
        double blspeed = Math.abs(backLeftEnc.getRate());
        double brspeed = Math.abs(backRightEnc.getRate());

        double[] encoderspeeds = {flspeed, frspeed, blspeed, brspeed};
        lowestSpeed = 0;
        for(int x = 0; x < encoderspeeds.length; x++)
        {
            if(encoderspeeds[x]<encoderspeeds[lowestSpeed])
            {
                lowestSpeed = x;
            }
        }
    }

    public double getLowestSpeed()
    {
        return lowestSpeed;
    }
}
