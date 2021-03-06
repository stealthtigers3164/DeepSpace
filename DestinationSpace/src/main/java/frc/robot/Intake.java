package frc.robot;

import frc.robot.motor.SparkMotor;
import frc.robot.LimitSwitch.OperatingMode;
import edu.wpi.first.wpilibj.DigitalInput;

public class Intake {
    private SparkMotor left;
    private SparkMotor right;
    private LimitSwitch limitSwitch;
    private double leftPower;
    private double rightPower;

    public Intake(int leftSpark, int rightSpark) {
        left = new SparkMotor(leftSpark);
        right = new SparkMotor(rightSpark);
        // limitSwitch = new LimitSwitch(0, OperatingMode.NC);
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
        leftPower = gamepad.getLeftTriggerValue() - gamepad.getRightTriggerValue();
        rightPower = gamepad.getRightTriggerValue() - gamepad.getLeftTriggerValue();
        
        // if (limitSwitch.isPressed()) {
        //     //Note: Check to make sure that the direction on both wheels are going outward
        //     if (leftPower < 0 && rightPower > 0) 
        //     {
        //         left.set(leftPower);
        //         right.set(rightPower);
        //     }
        // } else {
            left.set(leftPower);
            right.set(rightPower);
        // }
    }
}