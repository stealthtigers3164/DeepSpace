package frc.robot.pneumatics;

public class DoubleSolenoid {
    private edu.wpi.first.wpilibj.DoubleSolenoid piston;
    private boolean hasBeenUsed;

    public DoubleSolenoid(int a, int b) {
        piston = edu.wpi.first.wpilibj.DoubleSolenoid(a, b);
        hasBeenUsed = false;
    }

    public void fire() {
        if (hasBeenUsed) {
            return;
        } else {
            hasBeenUsed = true;
        }

        piston.set(edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward);
    }

    public void retract() {
        if (hasBeenUsed) {
            return;
        } else {
            hasBeenUsed = true;
        }

        piston.set(edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse);
    }

    public void reset() {
        hasBeenUsed = false;
        piston.set(edu.wpi.first.wpilibj.DoubleSolenoid.Value.kOff);
    }

    public edu.wpi.first.wpilibj.DoubleSolenoid getPiston() {
        return piston;
    }
}