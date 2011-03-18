

package edu.wpi.first.wpilibj.templates.Components;

import edu.wpi.first.wpilibj.templates.Components.XboxGamepad;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.templates.Components.XboxGamepad.Button;
import edu.wpi.first.wpilibj.templates.Controls;

/**
 *
 * @author GarrisonP
 */
public class Arm
{
    public Jaguar motor;
    XboxGamepad.Stick stick;
    int controllerType;
    private Button buttonForward;
    private Button buttonBackward;

    public Arm(int channel)
    {
        motor = new Jaguar(channel);
    }

    public void setController(XboxGamepad.Stick st){

        stick = st;
        controllerType = Controls.STICK;
        
    }

    public void setController(XboxGamepad.Button forward, XboxGamepad.Button backward){

        buttonForward = forward;
        buttonBackward = backward;
        controllerType = Controls.BUTTON;
        
    }

    public void update(){

        if(controllerType == Controls.STICK){
            motor.set( stick.getStickY() * Math.abs(stick.getStickY()) );
        }
        else if(controllerType == Controls.BUTTON){

            if(buttonForward.isPressed()){
                motor.set(1);
            }

            else if(buttonBackward.isPressed()){
                motor.set(-1);
            }

            else{
                motor.set(0);
            }

        }
        else{
            throw new RuntimeException("No controller selected");
        }
        
    }

}
