package edu.wpi.first.wpilibj.templates.Components;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Timer;
/**
 *
 * @author GarrisonP
 */
public class DriveTrain
{
    Jaguar frontLeftJag;
    Jaguar frontRightJag;
    Jaguar rearLeftJag;
    Jaguar rearRightJag;
    Encoder frontLeftEnc;
    Encoder frontRightEnc;
    Encoder rearLeftEnc;
    Encoder rearRightEnc;
    int driveType;

    final int MECANUM = 0;
    final int TANK = 1;
    final int ARCADE = 2;
    /**
     *
     * @param frontLeftMotor
     * @param frontRightMotor
     * @param rearLeftMotor
     * @param rearRightMotor
     */
    public DriveTrain(int frontLeftMotor, int frontRightMotor, int rearLeftMotor, int rearRightMotor)
    {
        frontLeftJag = new Jaguar(frontLeftMotor);
        frontRightJag = new Jaguar(frontRightMotor);
        rearLeftJag = new Jaguar(rearLeftMotor);
        rearRightJag = new Jaguar(rearRightMotor);
    }

    /**
     *
     * @param leftMotor
     * @param rightMotor
     */
    public DriveTrain(int leftMotor, int rightMotor)
    {
        frontLeftJag = null;
        frontRightJag = null;
        rearLeftJag = new Jaguar(leftMotor);
        rearRightJag = new Jaguar(rightMotor);
    }

    /**
     *
     * @param frontLeftMotor
     * @param frontRightMotor
     * @param rearLeftMotor
     * @param rearRightMotor
     * @param frontLeftEncoder
     * @param frontRightEncoder
     * @param rearLeftEncoder
     * @param rearRightEncoder
     */
    public DriveTrain(int frontLeftMotor, int frontRightMotor, int rearLeftMotor, int rearRightMotor,
            Encoder frontLeftEncoder, Encoder frontRightEncoder, Encoder rearLeftEncoder, Encoder rearRightEncoder)
    {
        frontLeftJag = new Jaguar(frontLeftMotor);
        frontRightJag = new Jaguar(frontRightMotor);
        rearLeftJag = new Jaguar(rearLeftMotor);
        rearRightJag = new Jaguar(rearRightMotor);
        frontLeftEnc = frontLeftEncoder;
        frontRightEnc = frontRightEncoder;
        rearLeftEnc = rearLeftEncoder;
        rearRightEnc = rearRightEncoder;
    }

    /**
     *
     * @param frontLeftMotor
     * @param frontRightMotor
     * @param rearLeftMotor
     * @param rearRightMotor
     * @param leftEncoder
     * @param rightEncoder
     */
    public DriveTrain(int frontLeftMotor, int frontRightMotor, int rearLeftMotor, int rearRightMotor,
            Encoder leftEncoder, Encoder rightEncoder)
    {
        frontLeftJag = new Jaguar(frontLeftMotor);
        frontRightJag = new Jaguar(frontRightMotor);
        rearLeftJag = new Jaguar(rearLeftMotor);
        rearRightJag = new Jaguar(rearRightMotor);
        frontLeftEnc = leftEncoder;
        frontRightEnc = rightEncoder;
        rearLeftEnc = leftEncoder;
        rearRightEnc = rightEncoder;
    }

    /**
     *
     * @param leftMotor
     * @param rightMotor
     * @param leftEncoder
     * @param rightEncoder
     */
    public DriveTrain(int leftMotor, int rightMotor,
            Encoder leftEncoder, Encoder rightEncoder)
    {
        frontLeftJag = null;
        frontRightJag = null;
        rearLeftJag = new Jaguar(leftMotor);
        rearRightJag = new Jaguar(rightMotor);
        frontLeftEnc = null;
        frontRightEnc = null;
        rearLeftEnc = leftEncoder;
        rearRightEnc = rightEncoder;
    }

    /**
     *
     * @param power
     * @param curve
     */
    public void drive(double power, double curve)
    {
        //more stuff here
        setJags(power,power);
    }

    /**
     *
     * @param state
     * @param distance
     * @param power
     */
    public void drive(int state, double distance, double power)
    {
        //more stuff here
        setJags(power,power);
    }

    /**
     *
     * @param leftSpeed
     * @param rightSpeed
     */
    public void setJags(double leftSpeed, double rightSpeed)
    {
        if(frontLeftJag!=null)
            frontLeftJag.set(leftSpeed);
        if(frontRightJag!=null)
            frontRightJag.set(rightSpeed);
        if(rearLeftJag!=null)
            rearLeftJag.set(leftSpeed);
        if(rearRightJag!=null)
            rearRightJag.set(rightSpeed);
    }

    /**
     *
     * @param iPower
     * @param fPower
     * @param tTime
     * @param steps
     * @param sTime
     * @param lTime
     * @param iTime
     */
    public void stepInto(double iPower, double fPower, double tTime, double steps, double sTime, double lTime, double iTime)
    {
        if(iTime == 0)
        {
            iTime = (Timer.getUsClock() / 1000000.0);
        }
        double cTime = (Timer.getUsClock() / 1000000.0);
        if(sTime == 0)
        {
            sTime = tTime/steps;
        }
        if(cTime - (sTime) >= (lTime+sTime+iTime) && lTime <= tTime+iTime)
        {
            if(iPower <= fPower)
            {
                setJags(iPower,iPower);
            }
            iPower += fPower/steps;
            lTime += sTime;
            stepInto(iPower,fPower,tTime,steps,sTime,lTime,iTime);
        }
        stepInto(iPower,fPower,tTime,steps,sTime,lTime,iTime);
    }

    /**
     *
     * @param fPower
     * @param tTime
     * @param steps
     */
    public void stepInto(double fPower, double tTime, double steps)
    {
        stepInto(0,fPower,tTime,steps,0,0,0);
    }

    public void setDrive_Mecanum(double xVel, double yVel, double rotVel, double gyroAngle)
    {
        driveType = MECANUM;
        double xIn = xVel;
        double yIn = yVel;

        // Compenstate for gyro angle.
        double rotated[] = rotateVector(xIn, yIn, gyroAngle);
        xIn = rotated[0];
        yIn = rotated[1];

        double wheelSpeeds[] = new double[4];
        wheelSpeeds[0] = xIn + yIn + rotVel;
        wheelSpeeds[1] = -xIn + yIn - rotVel;
        wheelSpeeds[2] = -xIn + yIn + rotVel;
        wheelSpeeds[3] = xIn + yIn - rotVel;

        wheelSpeeds = compensate(wheelSpeeds);

        wheelSpeeds = normalize(wheelSpeeds);

        frontLeftJag.set(wheelSpeeds[0]);
        frontRightJag.set(wheelSpeeds[1]*-1);
        rearLeftJag.set(wheelSpeeds[2]);
        rearRightJag.set(wheelSpeeds[3]*-1);
    }

    /**
     * Normalize all wheel speeds if the magnitude of any wheel is greater than 1.0.
     */
    protected static double[] normalize(double wheelSpeeds[]) {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);
        int i;
        for (i=1; i<4; i++) {
            double temp = Math.abs(wheelSpeeds[i]);
            if (maxMagnitude < temp) maxMagnitude = temp;
        }
        if (maxMagnitude > 1.0) {
            for (i=0; i<4; i++) {
                wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
            }
        }
        return wheelSpeeds;
    }

    /**
     * Compensate for odd motor issues
     */
    protected static double[] compensate(double wheelSpeeds[])
    {
        for(int i = 0;i<wheelSpeeds.length;i++)
        {
            if(i==0 || i==2)
            {
                if(wheelSpeeds[i]>0)
                    wheelSpeeds[i] = wheelSpeeds[i] * .8;
                else
                    wheelSpeeds[i] = wheelSpeeds[i] * 1.2;
            }
            else if(i==1 || i==3)
            {
                if(wheelSpeeds[i]>0)
                    wheelSpeeds[i] = wheelSpeeds[i] * 1.2;
                else
                    wheelSpeeds[i] = wheelSpeeds[i] * .8;
            }
        }
        return wheelSpeeds;
    }

    /**
     * Rotate a vector in Cartesian space.
     */
    protected static double[] rotateVector(double x, double y, double angle) {
        double cosA = Math.cos(angle * (3.14159 / 180.0));
        double sinA = Math.sin(angle * (3.14159 / 180.0));
        double out[] = new double[2];
        out[0] = x * cosA - y * sinA;
        out[1] = x * sinA + y * cosA;
        return out;
    }
}
