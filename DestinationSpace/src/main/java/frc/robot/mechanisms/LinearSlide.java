package frc.robot.mechanisms;

import frc.robot.LogitechGamepad;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.motor.MotorSet;
import frc.robot.motor.SparkMotor;
import frc.robot.LogitechGamepad;
import frc.robot.mechanisms.Arm;

public class LinearSlide
{
    private Encoder encoder;
    private MotorSet<SparkMotor> motorSet;

    private double minRange;
    private double maxRange;
    
    public LinearSlide(int rightPort, int leftPort, int channelA, int channelB, double minRange, double maxRange) {
        motorSet = new MotorSet<SparkMotor>();
        motorSet.add(new SparkMotor(rightPort));
        motorSet.add(new SparkMotor(leftPort));

        encoder = new Encoder(channelA, channelB);
        encoder.setDistancePerPulse(10);

        this.minRange = minRange;//getHeight();
        this.maxRange = maxRange;//-23000;
    }
    
    private void power(double in_power) {
        double sign = Math.signum(in_power);
        double power = sign * Math.min(Math.abs(in_power), .5);
        
        motorSet.power(power);

        double currentHeight = getHeight();
        
        SmartDashboard.putNumber("currentHeight", currentHeight);

        // If the lift is all the way at the bottom or top keep it where it is
        if (currentHeight > minRange &&
            currentHeight < maxRange)
        {
            motorSet.power(power);
        }
        else {
            if ((currentHeight < minRange && power < 0) || (currentHeight > maxRange && power > 0)) {
                motorSet.power(power);
            } else {
                motorSet.power(0);
            }
        }
    }

    public void update(LogitechGamepad gamepad2) {
        //NOTE: the left bumper moves the left down and the right up
        power(-gamepad2.getLeftYAxis());
    }
    
    public void setPower(double in_power) {
        power(in_power);
    }
    
    public double getHeight() {
        return encoder.getDistance();
    }
}