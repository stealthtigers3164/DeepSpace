package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class InOut{

    private Spark left;
    private Spark right;
    private DoubleSolenoid hatchPiston;
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

    public void hatch(){
        if(extended){
            extended = false;
            hatchPiston.set(DoubleSolenoid.Value.kReverse);
        } else {
            extended = true;
            hatchPiston.set(DoubleSolenoid.Value.kForward);
        }
    }

    public void update(Gamepad gamepad, boolean outputting){
        if(!outputting){
            if(gamepad.triggers.getRightPressed() && !gamepad.triggers.getLeftPressed()){ball(-1.0);}
            else if(!gamepad.triggers.getRightPressed() && gamepad.triggers.getLeftPressed()){ball(1.0);}
            else if(gamepad.triggers.getRightPressed() && gamepad.triggers.getLeftPressed()){hatch();}
        }
    }
}