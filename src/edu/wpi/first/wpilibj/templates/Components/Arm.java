

package edu.wpi.first.wpilibj.templates.Components;

import edu.wpi.first.wpilibj.templates.Components.XboxGamepad;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.templates.Controls;

/**
 *
 * @author GarrisonP
 */
public class Arm
{
    Jaguar motor;
    XboxGamepad.Stick stick;
    int controllerType;

    public Arm(int channel)
    {
        motor = new Jaguar(channel);
    }

    public void setController(XboxGamepad.Stick st){

        stick = st;
        controllerType = Controls.STICK;
        
    }

    public void update(){

        if(controllerType == Controls.STICK){
            motor.set( stick.getStickY() * Math.abs(stick.getStickY()) );
        }
        
    }

}
