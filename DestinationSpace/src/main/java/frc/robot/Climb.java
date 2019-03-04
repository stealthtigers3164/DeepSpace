package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
public class Climb {
    private DoubleSolenoid frontPiston;
    private Solenoid backPiston;

    public Climb(int f_moduleNum, int f_forward, int f_reverse, int b_moduleNum, int b_forward) {
        frontPiston = new DoubleSolenoid(f_moduleNum, f_forward, f_reverse); //accepts module number, forward port on PCM, reverse port on PCM
        backPiston = new Solenoid(b_moduleNum, b_forward); //accepts module number, port on PCM
    }

    public void update(LogitechGamepad gamepad) {
        boolean hasBeenUsed = false;
        boolean d_forward = gamepad.isLeftTriggerDown(true);
        boolean d_reverse = gamepad.isRightTriggerDown(true);
        boolean s_out = gamepad.isLBDown();
        if (d_forward && hasBeenUsed) {
            frontPiston.set(Value.kForward);
            hasBeenUsed = true;
        }
        if (d_reverse && !(hasBeenUsed)) {
            frontPiston.set(Value.kReverse);
            hasBeenUsed = true;
        }
        if (s_out && !(hasBeenUsed)) {
            backPiston.set(true);
            hasBeenUsed = true;
        }
        else {
            frontPiston.set(Value.kOff);
            backPiston.set(false);
            hasBeenUsed = false;
        }
    }

//OKAAAAY DO WE EVEN HAVE GAMEPAD SPACE ANYMORE :~)
//per colleen's suggestion which is actually objectively correct:
//drive & climb on gamepad 1, everything else on gamepad 2

}