package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Limelight 
{
    private NetworkTable table;
    private double steering_adjust = 0.0;

    public Limelight()
    {
        table = null;
    }

    public double getTX() {
        if (table == null) {
            table = NetworkTableInstance.getDefault().getTable("limelight");
        }
        //NOTE: Getting the tx variable from network tables is not a one time thing
        //Once the value changes you have to get it again
        return table.getEntry("tx").getDouble(0) / 50.0;
    }

    public double getTA() {
        if (table == null) {
            table = NetworkTableInstance.getDefault().getTable("limelight");
        }
        //NOTE: Getting the tx variable from network tables is not a one time thing
        //Once the value changes you have to get it again
        return table.getEntry("ta").getDouble(0);
    }

    public double getTV() {
        if (table == null) {
            table = NetworkTableInstance.getDefault().getTable("limelight");
        }
        //NOTE: Getting the tx variable from network tables is not a one time thing
        //Once the value changes you have to get it again
        return table.getEntry("tv").getDouble(0);
    }

    public double getAdjustments() {
        return steering_adjust;
    }

//method to get approximated distance, in inches, of the robot from its target 
//using basic trigonometry and the estimated ty value from the limelight.
    public double getDistance() {
    //double a1 stores the value of tx, 
    //which acts as ty due to the limelight's vertical orientation; 
    //ty approximates the angle (in degrees) from the camera to the target 
        double a1 = Math.abs(Math.toDegrees(Math.sin(Math.toRadians(getTX()))));
        double h1 = 40.5; //insert height in inches to limelight;
        double distance = h1*a1;
        SmartDashboard.putNumber("Approximate distance of robot to target", distance);
        return distance;
    }
}