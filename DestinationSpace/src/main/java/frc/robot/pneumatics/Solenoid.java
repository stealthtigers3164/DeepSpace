package frc.robot.pneumatics;

public class Solenoid {
    private edu.wpi.first.wpilibj.Solenoid piston;

    public Solenoid(int port) {
        piston = new edu.wpi.first.wpilibj.Solenoid(port);
    }

    public void fire() {
        piston.set(true);
    }

    public void retract() {
        piston.set(false);
    }

    public edu.wpi.first.wpilibj.Solenoid getPiston() {
        return piston;
    }
}