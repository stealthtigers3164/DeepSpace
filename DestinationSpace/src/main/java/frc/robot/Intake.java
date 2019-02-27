package frc.robot;

import edu.wpi.first.wpilibj.Spark;

public class Intake {
    //Intake left motor
    private Spark left;
    //Intake right motor
    private Spark right;

    public Intake(int leftSpark, int rightSpark) {
        left = new Spark(leftSpark);
        right = new Spark(rightSpark);
    }

    public void outputBall() {
        left.set(-1);
        right.set(1);
    }

    public void intakeBall() {
        left.set(1);
        right.set(-1);
    }

    public void update(Gamepad gamepad) {
        left.set(gamepad.trigger.getLeftVal() - gamepad.trigger.getRightVal());
        right.set(gamepad.trigger.getRightVal() - gamepad.trigger.getLeftVal());
    }
}