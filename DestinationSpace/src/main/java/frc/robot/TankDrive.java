package frc.robot;

import edu.wpi.first.wpilibj.Spark;

public class TankDrive 
{
    private Spark m_frontLeft;
    private Spark m_backLeft;
    private Spark m_frontRight;
    private Spark m_backRight;

    public TankDrive(int frontLeft, int backLeft, int frontRight, int backRight)
    {
        m_frontLeft = new Spark(frontLeft);
        m_backLeft = new Spark(backLeft);
        m_frontRight = new Spark(frontRight);
        m_backRight = new Spark(backRight);
    }

    public void update(Gamepad gamepad)
    {
        m_frontLeft.set(gamepad.sticks.LEFT_Y.getRaw());
        m_backLeft.set(gamepad.sticks.LEFT_Y.getRaw());

        m_frontRight.set(-gamepad.sticks.RIGHT_Y.getRaw());
        m_backRight.set(-gamepad.sticks.RIGHT_Y.getRaw());
    }
}