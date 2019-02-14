package frc.robot;

public class Intake
{
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
        right.set(-1);
    }

    public void intakeBall() {
        left.set(1);
        right.set(1);
    }

    public void update(Gamepad gamepad) {
        if(gamepad.trigger.getRightPressed(true) && !gamepad.trigger.getLeftPressed(true)) {
            outputBall();
        }
        else if(!gamepad.trigger.getRightPressed(true) && gamepad.trigger.getLeftPressed(true)) {
            intakeBall();
        }
    }
}