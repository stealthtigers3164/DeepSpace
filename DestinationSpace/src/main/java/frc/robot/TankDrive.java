package frc.robot;

import edu.wpi.first.wpilibj.Spark;

public class TankDrive 
{
    private Spark m_frontLeft;
    private Spark m_backLeft;
    private Spark m_frontRight;
    private Spark m_backRight;
    private Ultrasonic ultra;
    private LimeLight limeLight;

    public TankDrive(int frontLeft, int backLeft, int frontRight, int backRight, 
                     Ultrasonic ultrasonic, LimeLight limeLight)
    {
        m_frontLeft = new Spark(frontLeft);
        m_backLeft = new Spark(backLeft);
        m_frontRight = new Spark(frontRight);
        m_backRight = new Spark(backRight);
        ultra = ultrasonic;
        ultra.setAutomaticMode(true);
        this.limeLight = limeLight;
    }

    public void update(Gamepad gamepad, boolean encoderState/* double distance_adjust */)
    {
        float steering_adjust = 0;

        if(gamepad.buttons.BUTTON_X.isOn()){
            steering_adjust = lime.align(gamepad)
        }

        if(!encoderState){
            double distanceAdjust = ultra.adjustDistance();

            m_frontLeft.set((gamepad.sticks.LEFT_Y.getRaw() + steering_adjust + distance_adjust);
            m_backLeft.set((gamepad.sticks.LEFT_Y.getRaw() + steering_adjust + distance_adjust);

            m_frontRight.set((gamepad.sticks.RIGHT_Y.getRaw() - steering_adjust + distance_adjust);
            m_backRight.set((gamepad.sticks.RIGHT_Y.getRaw() - steering_adjust + distance_adjust);
        }
    }

    public void adjust()
    {
        //NOTE: Why did you pass in the distance when the ultrasonic is in here
        if (!(distance + 0.1 > range && distance - 0.1 < range)){
            double distanceAdjust = ultra.adjustDistance();
            m_backLeft.set(distanceAdjust);
            m_backRight.set(distanceAdjust);
            m_frontLeft.set(distanceAdjust);
            m_frontRight.set(distanceAdjust);
        } else {
            m_backLeft.set(0.0);
            m_backRight.set(0.0);
            m_frontLeft.set(0.0);
            m_frontRight.set(0.0);
        }
    }
}