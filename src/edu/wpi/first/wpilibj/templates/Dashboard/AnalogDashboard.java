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
public class AnalogDashboard {

    public void build(Dashboard data){

        data.addCluster();
        {
            buildSlot1(data);
            buildSlot2(data);
        }

        data.finalizeCluster();
    }

    public void buildSlot1(Dashboard data){
        
        // Add the cluster for Analog Module 0 (Slot One on the cRio)
        data.addCluster();
        {
            // Here we just iterate through the analog ports, adding
            // float values containing the average voltage for the given
            // port.
            for (int i = 1; i <= 8; i++) {
                data.addFloat((float) AnalogModule.getInstance(1).getAverageVoltage(i));
            }
        }
        // Finished with Analog Module 0, finalize the cluster
        data.finalizeCluster(); // finalize analog module 0
    }

    public void buildSlot2(Dashboard data){

        /// Add the cluster for Analog Module 1 (Slot Two on the cRio)
        data.addCluster();
        {
            // And again, iterate through the voltages for each port
            // on the module
            for (int i = 1; i <= 8; i++) {
                data.addFloat((float) AnalogModule.getInstance(2).getAverageVoltage(i));
            }
        }
        // Finished with Analog Module 1, finalize the cluster.
        data.finalizeCluster(); // finalize analog module 1
        
    }

}
