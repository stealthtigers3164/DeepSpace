package frc.robot;

import java.util.Set;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
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
}