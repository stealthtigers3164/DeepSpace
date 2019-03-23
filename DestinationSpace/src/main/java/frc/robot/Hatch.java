package frc.robot;

import frc.robot.pneumatics.DoubleSolenoid;

//Hatch mechanism
public class Hatch {
    private DoubleSolenoid hatchPiston;

    public Hatch(int forwardChannel, int reverseChannel) {
        hatchPiston = new DoubleSolenoid(forwardChannel, reverseChannel);
    }

    //Move the hatch piston in or out depending on whether or not it is extended
    public void hold(){
        hatchPiston.fire();
    }

    //Release the hatch
    public void release() {
        hatchPiston.retract();
    }

    //Set power to the intake motors based on the gamepad values
    public void update(LogitechGamepad gamepad) {
        if (gamepad.isRBDown()){
            hold();
        }
        if (gamepad.isLBDown()){//gamepad.buttons.BUTTON_LB.isOn()) {
            release();
        }
    }

    //Reset the hatch to the in position
    public void reset() {
        hatchPiston.reset();
    }
}