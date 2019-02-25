package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TankDrive 
{
    private Spark m_frontLeft;
    private Spark m_backLeft;
    private Spark m_frontRight;
    private Spark m_backRight;
    private double approximateDistance;
    private Limelight limeLight;
    private double range;

    public TankDrive(int frontLeft, int backLeft, int frontRight, int backRight, 
                     Limelight limeLight)
    {
        m_frontLeft = new Spark(frontLeft);
        m_backLeft = new Spark(backLeft);
        m_frontRight = new Spark(frontRight);
        m_backRight = new Spark(backRight);
        this.limeLight = limeLight;

        this.range = 17;
    }

    public void update(Gamepad gamepad, boolean shouldAlign)
    {
        double steering_adjust = 0;
        double distance_adjust = 0;
        approximateDistance = limeLight.getDistance();

        if(shouldAlign) {
            steering_adjust = Math.min(limeLight.getTX(), .5) ;
            distance_adjust = Math.min(approximateDistance / 100, .5);

            if (distance_adjust > 1) {
                distance_adjust = 1;
            }

            if (approximateDistance <= range) {
                distance_adjust = 0;
                steering_adjust = 0;
            }
        }

        double rightY = gamepad.sticks.RIGHT_X.getRaw();
        double leftX = -gamepad.sticks.LEFT_Y.getRaw();
      
        double leftPowerRaw = rightY - leftX;
        double rightPowerRaw = rightY + leftX;
        double leftPower = Math.signum(leftPowerRaw) * Math.min(Math.abs(leftPowerRaw), 1);
        double rightPower = Math.signum(rightPowerRaw) * Math.min(Math.abs(rightPowerRaw), 1);      

        double leftMotorPower = Math.min(leftPower + steering_adjust + distance_adjust, 1);
        double rightMotorPower = Math.min(rightPower + steering_adjust - distance_adjust, 1);

        m_frontLeft.set(leftMotorPower);
        m_backLeft.set(leftMotorPower);

        m_frontRight.set(rightMotorPower);
        m_backRight.set(rightMotorPower);
    }
}