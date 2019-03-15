package frc.robot;

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
    public void hold(){
        if (hasBeenUsed) {
            return;
        } else {
            hasBeenUsed = true;
        }

        hatchPiston.set(DoubleSolenoid.Value.kForward);
    }

    //Release the hatch
    public void release() {
        if (hasBeenUsed) {
            return;
        } else {
            hasBeenUsed = true;
        }

        hatchPiston.set(DoubleSolenoid.Value.kReverse);

    }

    //Set power to the intake motors based on the gamepad values
    public void update(LogitechGamepad gamepad) {
        if (gamepad.isRBDown()){
            hold();
        }
        if (gamepad.isLBDown()){
            release();
        }
    }

    //Reset the hatch to the in position
    public void reset() {
        hasBeenUsed = false;
        hatchPiston.set(DoubleSolenoid.Value.kOff);
    }
}