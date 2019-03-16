package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Limelight 
{
    private NetworkTable table;
    private double steering_adjust = 0.0;

    private static double h = 41;
    private static double half_h = 20.5;
    private static double offset = 0;

    public Limelight()
    {
        table = null;
    }

    public double getTX() {
        if (table == null) {
            table = NetworkTableInstance.getDefault().getTable("limelight");
        }
        double tx = (table.getEntry("tx").getDouble(0));
        //NOTE: Getting the tx variable from network tables is not a one time thing
        //Once the value changes you have to get it again
        return tx;
    }

    public double getTY() {
        if (table == null) {
            table = NetworkTableInstance.getDefault().getTable("limelight");
        }
        double ty = table.getEntry("ty").getDouble(0);
        SmartDashboard.putNumber("Ty: ", ty);
        //NOTE: Getting the ty variable from network tables is not a one time thing
        //Once the value changes you have to get it again
        return ty;
    }

    public double getTA() {
        if (table == null) {
            table = NetworkTableInstance.getDefault().getTable("limelight");
        }
        double ta = table.getEntry("ta").getDouble(0);
        //NOTE: Getting the ta variable from network tables is not a one time thing
        //Once the value changes you have to get it again
        return ta;
    }

    public double getTV() {
        if (table == null) {
            table = NetworkTableInstance.getDefault().getTable("limelight");
        }
        double tv = table.getEntry("tv").getDouble(0);
        //NOTE: Getting the tv variable from network tables is not a one time thing
        //Once the value changes you have to get it again
        return tv;
    }

    public double getAdjustments() {
        return steering_adjust;
    }

//Method to get approximated distance, in inches, of the robot from its target using basic trigonometry and the estimated ty value from the limelight.
//The target is assumed to be the vision tape corresponding to the lowest rocket hatch and all cargo ship bays;
//The distance algorithm below will not accurately return the distance of the robot from the rocket when it is facing the rocket ship cargo bays.
    // public double getDistance() {
    // /* Double a1 stores the value of tx, which acts as ty due to the limelight's vertical orientation; 
    // ty approximates the angle (in radians) from the camera to the target */
    //     double a1 = Math.abs((Math.sin(Math.toRadians(getTY()))));
    //     //Calculate the height in inches from the limelight to the target
    //     //Subtract the distance from the top of the target to the floor from the distance of the limelight to the floor
    //     double h1 = 40.5-31.5;
    //     double distance = h1*a1;
    //     SmartDashboard.putNumber("Approximate distance of robot to target", distance);
    //     return distance;
    // }

    public double getDistance() {
        return (getTY() + half_h) / h;
    }

    public void setCameraMode(boolean mode) {
        if (table == null) {
            table = NetworkTableInstance.getDefault().getTable("limelight");
        }
        if (mode) {
            table.getEntry("ledMode").setNumber(1);
            table.getEntry("camMode").setNumber(1);
        } else {
            table.getEntry("camMode").setNumber(0);
            table.getEntry("ledMode").setNumber(3);
        }
        // SmartDashboard.putBoolean("camMode on?", table.getEntry("camMode"));
    }

}