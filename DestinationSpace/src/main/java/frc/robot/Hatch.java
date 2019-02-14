package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid;

//Hatch mechanism
public class Hatch {
    //Hatch piston
    private DoubleSolenoid hatchPiston;
    //This is to make sure the hatch is not open and closed in one run of the loop
    //If this is not done then sometimes the hatch will not even activate the piston
    private boolean hasBeenUsed;

    public Hatch(int forwardChannel, int reverseChannel) {
        hatchPiston = new DoubleSolenoid(forwardChannel, reverseChannel);
        hasBeenUsed = false;
    }

    //Move the hatch piston in or out depending on whether or not it is extended
    public void hatch(){
        if (hasBeenUsed) {
            return;
        } else {
            hasBeenUsed = true;
        }

        hatchPiston.set(DoubleSolenoid.Value.kForward);
    }

    //Set power to the intake motors based on the gamepad values
    public void update(Gamepad gamepad){
        if (gamepad.trigger.getRightPressed(true) && gamepad.trigger.getLeftPressed(true)) {
            hatch();
        }
    }

    //Reset the hatch to the in position
    public void resetHatch() {
        if (hasBeenUsed) {
            hatchPiston.set(DoubleSolenoid.Value.kReverse);
        }

        hasBeenUsed = false;
    }
}