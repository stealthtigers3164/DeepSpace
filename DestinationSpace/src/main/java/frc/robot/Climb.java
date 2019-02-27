package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
public class Climb {
    private DoubleSolenoid frontPiston;
    private Solenoid backPiston;

    public Climb() {
        frontPiston = new DoubleSolenoid(0, 0, 0); //accepts module number, forward port on PCM, reverse port on PCM
        backPiston = new Solenoid(0, 0); //accepts module number, port on PCM
    }

//OKAAAAY DO WE EVEN HAVE GAMEPAD SPACE ANYMORE :~)
//per colleen's suggestion which is actually objectively correct:
//drive & climb on gamepad 1, everything else on gamepad 2

}