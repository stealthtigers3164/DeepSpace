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
        double tx = table.getEntry("tx").getDouble(0)/50.0;
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
public double getDistance() {
    /* Double a1 stores the value of ty
    ty approximates the angle (in radians) from the camera to the target */
        double angle = 0;
        while(table.getEntry("tv").getBoolean(false)) {
            angle = (Math.tan(Math.toRadians(getTY())));
            //Calculate the height in inches from the limelight to the target
            //Subtract the distance from the top of the target to the floor from the distance of the camera to the floor
            //double l_tape = 5.5/2;
            double l_floorToTape = 31.5;
            //double l_floorToTarget = l_floorToTape - l_tape;
            double l_targetToCam = 40.5-l_floorToTape;
            double distance = Math.abs(l_targetToCam*angle);
            SmartDashboard.putNumber("Approximated distance of robot to target: ", distance);
            return distance;
        }
        SmartDashboard.putString("Error getting distance: ", "Cannot compute distance if the target is not in sight");
        return angle;
    }

}