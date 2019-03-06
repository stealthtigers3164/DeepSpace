package frc.robot;

import java.util.EnumMap;

import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSwitch {
    private DigitalInput limitSwitch;
    private OperatingMode oMode;
    private boolean defaultState;

    /** An enum which stores two values corresponding to the circuitry of the switch, NO (normally open) or NC (normally closed).
     * A normally open limit switch will return true until it is pressed, 
     * while a normally closed limit switch will return false until it is pressed.
     * */

    public enum OperatingMode {
        NO(true), NC(false);

        private boolean state;

        OperatingMode(boolean state) {
            this.state = state;
        }

        /** Gets the default reading of the limit switch based on its circuitry (NO or NC)
         * @return A boolean value (true for NO and false for NC) that describes the default input of the switch 
         */

        public boolean getDefaultState() {
            return this.state;
        }
    }

    /** Creates a new Java object which controls the behavior of a limit switch
    * @param DIOport an integer between 0-9 corresponding to the port location of the switch on the roboRIO
    * @param OperatingMode an enum, NO (normally open) or NC (normally closed), which describes the circuitry of the switch 
    */

    public LimitSwitch(int DIOport, OperatingMode oMode) {
        this.limitSwitch = new DigitalInput(DIOport);
        this.oMode = oMode;
    }

    /** Detects whether or not the limit switch is pressed
     *  @return True if the limit switch is pressed, otherwise false
     */
    
    public boolean isPressed() {
        defaultState = oMode.getDefaultState();
        if (limitSwitch.get() != defaultState) {
            return true;
        }
        else return false;
    }
}