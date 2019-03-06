package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import frc.robot.LimitSwitch.OperatingMode;
import edu.wpi.first.wpilibj.DigitalInput;
public class Intake {
    //Intake left motor
    private Spark left;
    //Intake right motor
    private Spark right;
    private LimitSwitch limitSwitch;
    private double leftPower;
    private double rightPower;

    public Intake(int leftSpark, int rightSpark) {
        left = new Spark(leftSpark);
        right = new Spark(rightSpark);
        limitSwitch = new LimitSwitch(0, OperatingMode.NC);
    }

    public void outputBall() {
        left.set(-1);
        right.set(1);
    }

    public void intakeBall() {
        left.set(1);
        right.set(-1);
    }

    public void setPower(double power) {
        left.set(power);
        right.set(-power);
    }

    public void update(LogitechGamepad gamepad) {
        // smh this is a disaster : (

        leftPower = gamepad.getLeftTriggerValue() - gamepad.getRightTriggerValue();
        rightPower = gamepad.getRightTriggerValue() - gamepad.getLeftTriggerValue();
        if (limitSwitch.isPressed()) {
            //Note: Check to make sure that the direction on both wheels are going outward
            left.set(Math.min(0, leftPower));
            right.set(Math.min(0, -rightPower));
        } else {
            left.set(leftPower);
            right.set(rightPower);
        }
    }
}