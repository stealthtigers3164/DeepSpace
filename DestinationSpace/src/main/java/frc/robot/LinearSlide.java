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

    public void update(double rightBumper, double leftBumper)
    {//NOTE: the left bumper moves the left down and the right up
        double power = rightBumper - leftBumper;
        m_right.set(power);
        m_left.set(power);
    }
}