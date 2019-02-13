package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight 
{
    private NetworkTable table;
    private double tx;
    private double KpAim = -0.1;
    private double min_command = 0.05;
    private double steering_adjust = 0.0;
    private boolean autoAlign = false;

    public Limelight()
    {
        table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = 0;
    }

    public double align(Gamepad gamepad){
        //NOTE: Getting the tx variable from network tables is not a one time thing
        //Once the value changes you have to get it again
        tx = table.getEntry("tx").getDouble(0);

        double heading_error = -tx;

        if(tx > 1.0){
            return tx * heading_error - min_command;
        }
        else if(tx < 1.0){
            return  tx * heading_error + min_command;
        }
        return 0;
    }

    public double getAdjustments() {
        return steering_adjust;
    }

    public double getTX() {
        return tx;
    }
}