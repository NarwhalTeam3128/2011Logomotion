/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.Components;
import edu.wpi.first.wpilibj.*;
/**
 *
 * @author MartinP
 */
public class LineSensorManager {
    public LineSensor[] sensors;
    public LineSensorManager(int[] channel){

        if(channel.length == 0){
            throw new java.lang.IllegalArgumentException("Channel Sensors have not been set.");
        }

        for (int i=0; i== channel.length - 1;i++){
            sensors[i]= new LineSensor(channel[i]);
        }
        
    }
    public LineSensorManager(int[] slot, int[] channel){
        for (int i=0; i== channel.length - 1;i++){
            sensors[i]= new LineSensor(slot[i],channel[i]);
        }
    }
    public class LineSensor extends DigitalInput{
        LineSensor(int channel){
            super(channel);
        }
        LineSensor(int slot, int channel){
            super(slot,channel);
        }
        public boolean getValue(){
            return get();
        }
    }
    //0=Mid
    //1=Left
    //2=Right
    public int getlinelocation()
    {
        if (sensors[0].getValue() && sensors[1].getValue() && sensors[2].getValue())
        {
            return 0;//hits all sensors
        }
        else if(sensors[0].getValue() && sensors[1].getValue() && !sensors[2].getValue())
        {
            return 1;//only the left and middle sensors
        }
        else if(sensors[0].getValue() && !sensors[1].getValue() && sensors[2].getValue())
        {
            return 2;//only middle and right sensors
        }
        else if(!sensors[0].getValue() && sensors[1].getValue() && !sensors[2].getValue())
        {
            return 3;//only the left sensors
        }
        else if(!sensors[0].getValue() && !sensors[1].getValue() && sensors[2].getValue())
        {
            return 4;//only the right sensor
        }
        else if(sensors[0].getValue() && !sensors[1].getValue() && !sensors[2].getValue())
        {
            return 5;//only middle sensor
        }
        else
        {
            return 6;//no sensor is being called
        }


    }
}

