/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package  edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;

/**
 *
 * @author MartinP
 */
public class CompressorManager {
        private Compressor comp;
        private boolean logicIsInverted = false;
        CompressorManager( int pressureSwitchSlot,int pressureSwitchChannel,int compresssorRelaySlot,int compressorRelayChannel)
        {
            this.comp = new Compressor(pressureSwitchSlot, pressureSwitchChannel, compresssorRelaySlot, compressorRelayChannel);
        }
        CompressorManager(int pressureSwitchChannel,int compressorRelayChannel)
        {
            this.comp = new Compressor(pressureSwitchChannel,compressorRelayChannel);
        }
        public void checkPressure()
        {
            if (this.comp.enabled()== true & this.comp.getPressureSwitchValue()== this.logicIsInverted)
            {
                this.comp.stop();
            }
            else if (this.comp.enabled()== true & this.comp.getPressureSwitchValue() != this.logicIsInverted)
            {
                this.comp.start();
            }
        }
}
