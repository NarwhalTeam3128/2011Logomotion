/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;
/**
 *
 * @author MartinP
 */
public class LineSensorManager {
    LineSensor[] sensors;
    LineSensorManager(int[] channel){
        for (int i=0; i== channel.length - 1;i++){
            sensors[i]= new LineSensor(channel[i]);
        }
    }
    LineSensorManager(int[] slot, int[] channel){
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
    public String getlinelocation(){
        if (sensors[0].getValue()){
            return "left";
        }else if(sensors[1].getValue()){
            return "ahead";
        }else if(sensors[2].getValue()){
            return "right";
        } else {return "err";}


    }
}

