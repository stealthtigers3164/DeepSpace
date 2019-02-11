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

    public Output(int forwardChannel, int reverseChannel, int leftSpark, int rightSpark){
        hatchPiston = new DoubleSolenoid(forwardChannel, reverseChannel);
        left = new Spark(leftSpark);
        right = new Spark(rightSpark);
    }

    public void ball(double power){
        left.set(power);
        right.set(power);
    }

    //Move the hatch piston in or out depending on whether or not it is extended
    public void hatch(){
        if(extended){
            extended = false;
            hatchPiston.set(DoubleSolenoid.Value.kReverse);
        } else {
            extended = true;
            hatchPiston.set(DoubleSolenoid.Value.kForward);
        }
    }

    //Set power to the intake motors based on the gamepad values
    public void update(Gamepad gamepad, boolean outputting){
        if(!outputting){
            if(gamepad.triggers.getRightPressed() && !gamepad.triggers.getLeftPressed()){ball(-1.0);}
            else if(!gamepad.triggers.getRightPressed() && gamepad.triggers.getLeftPressed()){ball(1.0);}
            else if(gamepad.triggers.getRightPressed() && gamepad.triggers.getLeftPressed()){hatch();}
        }
    }
}