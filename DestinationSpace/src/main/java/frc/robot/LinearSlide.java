package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class LinearSlide
{
    private Encoder encoder;
    private Spark m_right;
    private Spark m_left;

    private double minRange;
    private double maxRange;
    
    public LinearSlide(int rightPort, int leftPort, int channelA, int channelB) {
        m_right = new Spark(rightPort);
        m_left = new Spark(leftPort);
        
        encoder = new Encoder(channelA, channelB);
        encoder.setDistancePerPulse(10);

        minRange = 0;
        maxRange = 0;
    }
    
    private void power(double in_power) {
        double sign = Math.signum(in_power);
        double power = sign * Math.min(Math.abs(in_power), .5);
        m_right.set(power);
        m_left.set(power);
        
        double currentHeight = getHeight();
        
        SmartDashboard.putNumber("currentHeight", currentHeight);

        //If the lift is all the way at the bottom or top keep it where it is
        if (currentHeight < minRange ||
            currentHeight > maxRange)
        {
            if ((currentHeight < minRange && power > 0) ||
                (currentHeight > maxRange && power < 0)) {
                m_right.set(power);
                m_left.set(power);
            } else {
                m_right.set(power);
                m_left.set(power);
            }
        }        
    }

    public void update(LogitechGamepad gamepad2) {
        //NOTE: the left bumper moves the left down and the right up        
        power(gamepad2.getLeftYAxis());//gamepad2.sticks.LEFT_Y.getRaw());
    }
    
    public void setPower(double in_power) {
        power(in_power);
    }
    
    public double getHeight() {
        return encoder.getDistance();
    }
}