package frc.robot;

import edu.wpi.first.wpilibj.Spark;

public class TankDrive 
{
    private Spark m_frontLeft;
    private Spark m_backLeft;
    private Spark m_frontRight;
    private Spark m_backRight;
    private Ultrasonic ultra;

    public TankDrive(int frontLeft, int backLeft, int frontRight, int backRight, Ultrasonic ultrasonic)
    {
        m_frontLeft = new Spark(frontLeft);
        m_backLeft = new Spark(backLeft);
        m_frontRight = new Spark(frontRight);
        m_backRight = new Spark(backRight);
        ultra = ultrasonic;
        ultra.setAutomaticMode(true);
    }

    public void update(Gamepad gamepad, double steering_adjust, double distance_adjust)
    {
        m_frontLeft.set((gamepad.sticks.LEFT_Y.getRaw() + steering_adjust + distance_adjust);
        m_backLeft.set((gamepad.sticks.LEFT_Y.getRaw() + steering_adjust + distance_adjust);

        m_frontRight.set((gamepad.sticks.RIGHT_Y.getRaw() - steering_adjust + distance_adjust);
        m_backRight.set((gamepad.sticks.RIGHT_Y.getRaw() - steering_adjust + distance_adjust);
    }

    public void adjust(double distance)
    {
        while(!(distance + 0.1 > range && distance - 0.1 < range)){
            m_backLeft.set(ultra.adjustDistance());
            m_backRight.set(ultra.adjustDistance());
            m_frontLeft.set(ultra.adjustDistance());
            m_frontRight.set(ultra.adjustDistance());
        }
        m_backLeft.set(0.0);
        m_backRight.set(0.0);
        m_frontLeft.set(0.0);
        m_frontRight.set(0.0);
    }
}