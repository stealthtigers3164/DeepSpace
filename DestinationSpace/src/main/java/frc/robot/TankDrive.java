package frc.robot;

import edu.wpi.first.wpilibj.Spark;

public class TankDrive 
{
    private Spark m_frontLeft;
    private Spark m_backLeft;
    private Spark m_frontRight;
    private Spark m_backRight;
    private UltrasonicSensor ultra;
    private Limelight limeLight;
    private double range;

    public TankDrive(int frontLeft, int backLeft, int frontRight, int backRight, 
                     UltrasonicSensor ultrasonic, Limelight limeLight)
    {
        m_frontLeft = new Spark(frontLeft);
        m_backLeft = new Spark(backLeft);
        m_frontRight = new Spark(frontRight);
        m_backRight = new Spark(backRight);
        ultra = ultrasonic;
        this.limeLight = limeLight;

        this.range = 10;
    }

    public void update(Gamepad gamepad, boolean encoderState)
    {
        double steering_adjust = 0;

        if(gamepad.buttons.BUTTON_X.isOn()){
            steering_adjust = limeLight.align(gamepad);
        }

        if(!encoderState){
            double distanceAdjust = ultra.getDistance();

            m_frontLeft.set(gamepad.sticks.LEFT_Y.getRaw() + steering_adjust + distanceAdjust);
            m_backLeft.set(gamepad.sticks.LEFT_Y.getRaw() + steering_adjust + distanceAdjust);

            m_frontRight.set(gamepad.sticks.RIGHT_Y.getRaw() - steering_adjust + distanceAdjust);
            m_backRight.set(gamepad.sticks.RIGHT_Y.getRaw() - steering_adjust + distanceAdjust);
        }
    }

    public void adjust()
    {
        double distance = ultra.getDistanceInches();

        //NOTE: Why did you pass in the distance when the ultrasonic is in here
        if (distance > range){
            double distanceAdjust = ultra.getDistance();
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