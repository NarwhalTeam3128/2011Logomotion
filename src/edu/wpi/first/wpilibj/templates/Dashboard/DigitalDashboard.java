package edu.wpi.first.wpilibj.templates.Dashboard;

import edu.wpi.first.wpilibj.AnalogModule;
import edu.wpi.first.wpilibj.Dashboard;
import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Watchdog;

/**
 *
 * @author tylercarter
 */
public class DigitalDashboard {

    public void build(Dashboard data){
        
        data.addCluster();
        {
            buildSlot4(data);
            buildSlot6(data);
        }
        // And that completes the entire Digital Cluster
        data.finalizeCluster(); // finalize digital cluster
    }

    public void buildSlot4(Dashboard data){

        // Add the cluster for Digital SubCluster 1 (slot 4 on the cRio)
        data.addCluster();
        {
            // The digital module itself contains two sub modules.
            // The numbering system is screwey, but it's taken straight
            // from the LabView code...

            // Add the cluster Digital Module 0 in SubCluster 1
            data.addCluster();
            {
                int module = 4; // this specifies the cRio slot

                // And the next 4 lines handle relays
                data.addByte(DigitalModule.getInstance(module).getRelayForward());
                data.addByte(DigitalModule.getInstance(module).getRelayForward());
                data.addShort(DigitalModule.getInstance(module).getAllDIO());
                data.addShort(DigitalModule.getInstance(module).getDIODirection());

                // Add the cluster for PWM module 1 - I know for a fact
                // that these are the 10 PWM connections on the digital
                // sidecar hooked to the module in cRio Slot 4 :)
                data.addCluster();
                {
                    // Iterate through the PWM values for each port
                    for (int i = 1; i <= 10; i++) {
                        data.addByte((byte) DigitalModule.getInstance(module).getPWM(i));
                    }
                }
                // Finished with PWM Module 1, finalize the cluster.
                data.finalizeCluster(); // finalize PWM 1
            }
            // And finished with Digital Module 0
            data.finalizeCluster(); // finalize digital module 0
        }
        // And with Digital SubCluster 1
        data.finalizeCluster(); // finalize subcluster 1
        
    }

    public void buildSlot6(Dashboard data){
        
        // Add the cluster for Digital SubCluster 2 (slot 6 on the cRio)
        data.addCluster();
        {
            // Add the cluster for Digital Module 1 in SubCluster 2
            data.addCluster();
            {
                int module = 6; // cRio slot 6

                // And again, load the relay data
                data.addByte(DigitalModule.getInstance(module).getRelayForward());
                data.addByte(DigitalModule.getInstance(module).getRelayReverse());
                data.addShort(DigitalModule.getInstance(module).getAllDIO());
                data.addShort(DigitalModule.getInstance(module).getDIODirection());

                // Add the cluster for PWM Module 2
                data.addCluster();
                {
                    // iterate through all 10 PWM ports
                    for (int i = 1; i <= 10; i++) {
                        data.addByte((byte) DigitalModule.getInstance(module).getPWM(i));
                    }
                }
                // Finished with PWM Module 2, finalize it.
                data.finalizeCluster(); // finalize PWM 2
            }
            // Finished with Digital Module 2
            data.finalizeCluster(); // finalize digital 2
        }
        // And done with Digital SubCluster 2
        data.finalizeCluster(); // finalize subcluster 2
        
    }

}
