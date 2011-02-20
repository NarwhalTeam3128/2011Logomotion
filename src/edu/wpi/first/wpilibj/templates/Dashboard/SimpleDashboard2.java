package edu.wpi.first.wpilibj.templates.Dashboard;

import edu.wpi.first.wpilibj.AnalogModule;
import edu.wpi.first.wpilibj.Dashboard;
import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SolenoidBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Watchdog;

/**
 *
 * @author tylercarter
 */
public class SimpleDashboard2 {

    AnalogDashboard analog;
    DigitalDashboard digital;

    public SimpleDashboard2(){
        analog = new AnalogDashboard();
        digital = new DigitalDashboard();
    }

    public void update() {

        // Grab the data
        Dashboard data = DriverStation.getInstance().getDashboardPackerLow();

        // Start with the parent
        data.addCluster();
        {
            // Add analog modules
            analogModules(data);

            // Add digital modules
            digitalModules(data);

            // Add solenoid data
            solenoidData(data);
            
        }

        data.finalizeCluster();
        data.commit(); 

    }

    private void analogModules(Dashboard data){
        analog.build(data);
    }

    private void digitalModules(Dashboard data){
        digital.build(data);
    }

    private void solenoidData(Dashboard data){

        // @todo Update to actually use Solenoid data
        SolenoidBase sol = new Solenoid(8);
        data.addByte(sol.getAll());
    }

}
