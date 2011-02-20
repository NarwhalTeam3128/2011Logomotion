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
public class SimpleDashboard {

    public void update() {

        // first, get an instance of the dashboard "packer" file.  This is
        // essentially a map of how the dashboard has the clusters configured.
        Dashboard lowDashData = DriverStation.getInstance().getDashboardPackerLow();

        // We now recreate the tree using the addCluster method, nesting
        // the individual display panels.

        // First, the overall container that holds everything else
        lowDashData.addCluster();  // overall container
        {
            // There are 3 clusters in the next level, the analog modules,
            // the digital modules, and the solenoids.

            // Add the cluster containing the analog modules.
            lowDashData.addCluster();
            {
                // The analog module cluster contains two analog modules, one
                // for each of the first two slots on the cRio.

                // Add the cluster for Analog Module 0 (Slot One on the cRio)
                lowDashData.addCluster();
                {
                    // Here we just iterate through the analog ports, adding
                    // float values containing the average voltage for the given
                    // port.
                    for (int i = 1; i <= 8; i++) {
                        lowDashData.addFloat((float) AnalogModule.getInstance(1).getAverageVoltage(i));
                    }
                }
                // Finished with Analog Module 0, finalize the cluster
                lowDashData.finalizeCluster(); // finalize analog module 0

                /// Add the cluster for Analog Module 1 (Slot Two on the cRio)
                lowDashData.addCluster();
                {
                    // And again, iterate through the voltages for each port
                    // on the module
                    for (int i = 1; i <= 8; i++) {
                        lowDashData.addFloat((float) AnalogModule.getInstance(2).getAverageVoltage(i));
                    }
                }
                // Finished with Analog Module 1, finalize the cluster.
                lowDashData.finalizeCluster(); // finalize analog module 1
            }
            // Done loading the data for the analog modules, so we finalize
            // the cluster that holds both of them.
            lowDashData.finalizeCluster(); // finalize analog cluster

            // Add the cluster containing the digital sub clusters.
            lowDashData.addCluster();
            {
                // The digital cluster contains two sub clusters, one for
                // slot 4 and one for slot 6 on the cRio.  Each of those
                // clusters contain modules for relays and for the PWM
                // slots.

                // Add the cluster for Digital SubCluster 1 (slot 4 on the cRio)
                lowDashData.addCluster();
                {
                    // The digital module itself contains two sub modules.
                    // The numbering system is screwey, but it's taken straight
                    // from the LabView code...

                    // Add the cluster Digital Module 0 in SubCluster 1
                    lowDashData.addCluster();
                    {
                        int module = 4; // this specifies the cRio slot

                        // And the next 4 lines handle relays
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
                        lowDashData.addShort(DigitalModule.getInstance(module).getAllDIO());
                        lowDashData.addShort(DigitalModule.getInstance(module).getDIODirection());

                        // Add the cluster for PWM module 1 - I know for a fact
                        // that these are the 10 PWM connections on the digital
                        // sidecar hooked to the module in cRio Slot 4 :)
                        lowDashData.addCluster();
                        {
                            // Iterate through the PWM values for each port
                            for (int i = 1; i <= 10; i++) {
                                lowDashData.addByte((byte) DigitalModule.getInstance(module).getPWM(i));
                            }
                        }
                        // Finished with PWM Module 1, finalize the cluster.
                        lowDashData.finalizeCluster(); // finalize PWM 1
                    }
                    // And finished with Digital Module 0
                    lowDashData.finalizeCluster(); // finalize digital module 0
                }
                // And with Digital SubCluster 1
                lowDashData.finalizeCluster(); // finalize subcluster 1

                // Add the cluster for Digital SubCluster 2 (slot 6 on the cRio)
                lowDashData.addCluster();
                {
                    // Add the cluster for Digital Module 1 in SubCluster 2
                    lowDashData.addCluster();
                    {
                        int module = 6; // cRio slot 6

                        // And again, load the relay data
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayReverse());
                        lowDashData.addShort(DigitalModule.getInstance(module).getAllDIO());
                        lowDashData.addShort(DigitalModule.getInstance(module).getDIODirection());

                        // Add the cluster for PWM Module 2
                        lowDashData.addCluster();
                        {
                            // iterate through all 10 PWM ports
                            for (int i = 1; i <= 10; i++) {
                                lowDashData.addByte((byte) DigitalModule.getInstance(module).getPWM(i));
                            }
                        }
                        // Finished with PWM Module 2, finalize it.
                        lowDashData.finalizeCluster(); // finalize PWM 2
                    }
                    // Finished with Digital Module 2
                    lowDashData.finalizeCluster(); // finalize digital 2
                }
                // And done with Digital SubCluster 2
                lowDashData.finalizeCluster(); // finalize subcluster 2
            }
            // And that completes the entire Digital Cluster
            lowDashData.finalizeCluster(); // finalize digital cluster

            // The solenoids, for some reason, are not in a cluster of their
            // own, but are instead sitting in the overall container.  As such,
            // we don't need to addCluster() for them, we just need to add
            // the data.

            // @todo Update to actually use Solenoid data
            byte thisBytes = 0;
            lowDashData.addByte(thisBytes);

            // If you are going to add instruments to the dashboard the
            // easiest place to put them is in the overall cluster, and then
            // load the values for them here the same way the solenoid was
            // done.  Makes me think the solenoid was an afterthought.
            // Actually I think the whole dashboard was designed haphazardly
            // instead of in a planned manner, which is why this whole structure
            // sucks so bad.
        }

        // Once all the inputs are read and loaded, we close the overall container
        lowDashData.finalizeCluster(); // finalize entire tree structure.

        // We are now ready to send our packed up data tree to the dashboard
        // for display.
        lowDashData.commit(); // commit changes to update dashboard

    } // end method updateDashboard()

}
