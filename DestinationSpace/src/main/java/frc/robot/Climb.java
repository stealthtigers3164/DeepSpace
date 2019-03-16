package frc.robot;

import frc.robot.pneumatics.DoubleSolenoid;
import frc.robot.pneumatics.Solenoid;
// import edu.wpi.first.wpilibj.DoubleSolenoid;
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
        frontPiston.reset();
        boolean a_forward = gamepad.isLeftTriggerDown(true);
        boolean a_reverse = gamepad.isRightTriggerDown(true);
        boolean b_out = gamepad.isLBDown();
        boolean b_in = gamepad.isRBDown();

        if (a_forward) {
            frontPiston.fire();
        }
        if (a_reverse) {
            frontPiston.retract();
        }
        if (b_out) {
            backPiston.fire();
        } 
        if (b_in) {
            backPiston.retract();
        }
    }

//OKAAAAY DO WE EVEN HAVE GAMEPAD SPACE ANYMORE :~)
//per colleen's suggestion which is actually objectively correct:
//drive & climb on gamepad 1, everything else on gamepad 2

}