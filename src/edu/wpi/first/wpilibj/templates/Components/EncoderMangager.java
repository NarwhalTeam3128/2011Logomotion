/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.Components;
import edu.wpi.first.wpilibj.*;
/**
 *
 * @author ShenD
 */
public class EncoderMangager {
    private Encoder EncoderList[];
    private String  wheelsetting;
    /**
     *
     * @return
     */
    public double getDistance()
    {
        double distance = 0;
        for (int i =0; i<12; i++)
        {
            distance += EncoderList[i].getDistance();
        }
        distance = distance/EncoderList.length;
        return distance;
    }
    /**
     *
     * @param encoder
     */
    public void addEncoder(Encoder encoder){
        EncoderList[EncoderList.length]= encoder;
    }
    /**
     *
     * @param wheeltype
     */
    public void setWheelConfig(String wheeltype){
        wheelsetting=wheeltype;
    }
}
