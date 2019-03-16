package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
public class Climb {
    private DoubleSolenoid frontPiston;
    private Solenoid backPiston;

    public Climb(int f_forward, int f_reverse, int b_forward) {
        frontPiston = new DoubleSolenoid(f_forward, f_reverse); //accepts module number, forward port on PCM, reverse port on PCM
        backPiston = new Solenoid(b_forward); //accepts module number, port on PCM
    }

    public void update(LogitechGamepad gamepad) {
        boolean hasBeenUsed = false;

        boolean a_forward = gamepad.isLeftTriggerDown(true);
        boolean a_reverse = gamepad.isRightTriggerDown(true);
        boolean b_out = gamepad.isLBDown();
        if(hasBeenUsed) {
            return; 
        } else {
            if (a_forward) {
                frontPiston.set(Value.kForward);
            }
            if (a_reverse) {
                frontPiston.set(Value.kReverse);
            }
            if (b_out) {
                backPiston.set(true);
            } else {
                frontPiston.set(Value.kOff);
                backPiston.set(false);
                hasBeenUsed = true;
            }
        }
    }

//OKAAAAY DO WE EVEN HAVE GAMEPAD SPACE ANYMORE :~)
//per colleen's suggestion which is actually objectively correct:
//drive & climb on gamepad 1, everything else on gamepad 2

}