package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid;

//Intake and the Hatch mechanism
public class InOut{

    //Intake left motor
    private Spark left;
    //Intake right motor
    private Spark right;
    //Hatch piston
    private DoubleSolenoid hatchPiston;
    //Whether or not the hatch piston is extended or not
    private boolean extended = false;
    //This is to make sure the hatch is not open and closed in one run of the loop
    //If this is not done then sometimes the hatch will not even activate the piston
    private boolean hasBeenUsed;

    public InOut(int forwardChannel, int reverseChannel, int leftSpark, int rightSpark){
        hatchPiston = new DoubleSolenoid(forwardChannel, reverseChannel);
        left = new Spark(leftSpark);
        right = new Spark(rightSpark);
        hasBeenUsed = false;
    }

    public void ball(double power){
        left.set(power);
        right.set(power);
    }

    //Move the hatch piston in or out depending on whether or not it is extended
    public void hatch(){
        if (hasBeenUsed) {
            return;
        } else {
            hasBeenUsed = true;
        }
        
        if(extended){
            extended = false;
            hatchPiston.set(DoubleSolenoid.Value.kReverse);
        } else {
            extended = true;
            hatchPiston.set(DoubleSolenoid.Value.kForward);
        }
    }

    //Set power to the intake motors based on the gamepad values
    public void update(Gamepad gamepad){
        if(gamepad.trigger.getRightPressed(true) && !gamepad.trigger.getLeftPressed(true)) {
            ball(-1.0);
        }
        else if(!gamepad.trigger.getRightPressed(true) && gamepad.trigger.getLeftPressed(true)) {
            ball(1.0);
        }
        else if(gamepad.trigger.getRightPressed(true) && gamepad.trigger.getLeftPressed(true)) {
            hatch();
        }
    }

    public void resetHatch() {
        hasBeenUsed = false;
    }
}