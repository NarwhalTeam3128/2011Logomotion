

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author GarrisonP
 */
public class Arm
{
    Jaguar motor;
    public Arm(int channel)
    {
        motor = new Jaguar(channel);
    }

    public void setStick(XboxGamepad.Stick st)
    {
        motor.set(st.getStickY());
    }
}
