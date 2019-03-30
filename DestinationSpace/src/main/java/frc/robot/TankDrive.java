package frc.robot;

import frc.robot.motor.SparkMotor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TankDrive 
{
    private SparkMotor m_frontLeft;
    private SparkMotor m_backLeft;
    private SparkMotor m_frontRight;
    private SparkMotor m_backRight;
    private double approximateDistance;
    private Limelight limeLight;
    private UltrasonicSensor rangeFinder;
    private double range;

    public TankDrive(int frontLeft, int backLeft, int frontRight, int backRight, 
                     Limelight limeLight, int channelA, int channelB)
    {
        m_frontLeft = new SparkMotor(frontLeft);
        m_backLeft = new SparkMotor(backLeft);
        m_frontRight = new SparkMotor(frontRight);
        m_backRight = new SparkMotor(backRight);
        this.limeLight = limeLight;
        this.rangeFinder = new UltrasonicSensor(channelA, channelB);

        this.range = 17;
    }

    public void update(LogitechGamepad gamepad, boolean shouldAlign)
    {
        double steering_adjust = 0;
        double distance_adjust = 0;
        approximateDistance = rangeFinder.getDistanceInches();

        SmartDashboard.putNumber("approximateDistance", approximateDistance);
        if(shouldAlign) {
            limeLight.setCameraMode(false);
            steering_adjust = Math.min(Math.abs(limeLight.getTX()) / 20, 1) ;
            steering_adjust *= Math.signum(limeLight.getTX());
            distance_adjust = Math.min(approximateDistance / 100, .5);


            if (distance_adjust > 1) {
                distance_adjust = 1;
            }

            if (approximateDistance <= range) {
                distance_adjust = 0;
                // steering_adjust = 0;
            }
        }

        double rightY = Math.signum(gamepad.getRightXAxis()) * Math.min(Math.abs(gamepad.getRightXAxis()), .75);
        double leftX = gamepad.getLeftYAxis();
      
        double leftPowerRaw = rightY - leftX;
        double rightPowerRaw = rightY + leftX;
        double leftPower = Math.signum(leftPowerRaw) * Math.min(Math.abs(leftPowerRaw), 1);
        double rightPower = Math.signum(rightPowerRaw) * Math.min(Math.abs(rightPowerRaw), 1);      

        double leftMotorPower = Math.min(leftPower + steering_adjust + distance_adjust, 1);
        double rightMotorPower = Math.min(rightPower + steering_adjust - distance_adjust, 1);

        setPower(leftMotorPower, rightMotorPower);
    }

    public void setPower(double left, double right) {
        m_frontLeft.set(left);
        m_backLeft.set(left);

        m_frontRight.set(right);
        m_backRight.set(right);
    }

    public double applyCurve(double x) {
        double in = x * 100;
        SmartDashboard.putNumber("in", in);
        double res = (1 / 1000) * (in * in);
        SmartDashboard.putNumber("res", res);
        res /= 10;
        SmartDashboard.putNumber("res", res);
        return res;
    }
}