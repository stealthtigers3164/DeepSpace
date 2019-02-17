package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class LinearSlide
{
    private Encoder encoder;
    private Spark m_right;
    private Spark m_left;
    
    private double keepHeight;
    private boolean isAligning;
    
    public LinearSlide(int rightPort, int leftPort, int channelA, int channelB) {
        m_right = new Spark(rightPort);
        m_left = new Spark(leftPort);
        
        encoder = new Encoder(channelA, channelB);
        encoder.setDistancePerPulse(10);
        keepHeight = 0;
        isAligning = false;
    }
    
    public void update(Gamepad gamepad2) {//NOTE: the left bumper moves the left down and the right up
        double left_y = gamepad2.sticks.LEFT_Y.getRaw();
        double sign = Math.signum(left_y);
        double power = sign * Math.min(Math.abs(left_y), .5);
        m_right.set(power);
        m_left.set(power);
        
        double currentHeight = getHeight();
        
        //If the alignment code and the drivers are not moving the lift set the keepHeight
        if (power == 0 && keepHeight == 0 && 
            !isAligning) {
            keepHeight = currentHeight;
        }
        
        //If the driver or alignment code need to move it reset keepHeight
        if (power != 0 || isAligning) {
            keepHeight = 0;
        }
        
        SmartDashboard.putBoolean("Linearslide Keepup", keepHeight > 0);
        
        //If keepHeight is greater than 0 power the lift just enough to keep it
        //at the same height
        if (keepHeight > 0) {
            //NOTE: This value needs to change based on what the power requirement is
            //      to keep the linear slide at its current height
            
            //NOTE: This is disabled until the min_range and max_range are found so that this does not break the lift again
            
            
            //setPower(.125);
            
        }
        
        //If the lift is all the way at the bottom or top keep it where it is
        if (currentHeight < min_range ||
            currentHeight > max_rangle)
        {
            //WARNING: if the keepHeight power is too much and the keepHeight is set to keep it at the top then it could break it
            setPower(0);
        }
    }
    
    public void setPower(double x) {
        double power = Math.min(x, .5);
        m_right.set(power);
        m_left.set(power);
    }
    
    public double getHeight() {
        return encoder.getDistance();
    }
    
    public void setAligning(boolean a) {
        isAligning = a;
    }
}