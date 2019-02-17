package frc.robot;

import edu.wpi.first.wpilibj.Spark;

public class LinearSlide
{
    private Spark m_right;
    private Spark m_left;

    public LinearSlide(int rightPort, int leftPort)
    {
        m_right = new Spark(rightPort);
        m_left = new Spark(leftPort);
    }

    public void update(Gamepad gamepad2)
    {//NOTE: the left bumper moves the left down and the right up
        double power = gamepad2.sticks.LEFT_Y.getRaw();
        m_right.set(power);
        m_left.set(power);
    }

    public void update(double x, double y) {
        m_right.set(x);
        m_left.set(x);
    }
}