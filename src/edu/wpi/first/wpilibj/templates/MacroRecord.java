/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author tylercarter
 */
public class MacroRecord
{
    Jaguar motorControllers[];
    public MacroRecord(Jaguar motorControllers[])
    {
        this.motorControllers = motorControllers;
    }

    public void record()
    {
        String output = "{";
        for(int x = 0;x<motorControllers.length; x++)
        {
            if(x<motorControllers.length-1)
                output += ""+motorControllers[x].get()+",";
            else
                output += ""+motorControllers[x].get();
        }
        output+="},";
        System.out.println(output);
    }
}
