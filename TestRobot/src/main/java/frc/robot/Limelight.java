package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight 
{

    private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    private double tx = table.getEntry("tx");
    private double KpAim = -0.1;
    private double min_command = 0.05;
    private double steering_adjust = 0.0;
    private boolean autoAlign = false;
    private TankDrive tank;

    public Limelight(TankDrive tankdrive)
    {
        tank = tankdrive;
    }

    public void update(Gamepad gamepad)
    {
        steering_adjust = 0.0;
        if(gamepad.buttons.BUTTON_X.isOn()){
            if(autoAlign){autoAlign = false;}
            else {autoAlign = true;}
        }
        if(autoAlign){align();}
    }

    public void align(){
        double heading_error = -tx;
        tank.adjust(30.0);
        if(tx > 1.0){
            steering_adjust = Kp * heading_error - min_command;
        }
        else if(tx < 1.0){
            steering_adjust = Kp * heading_error + min_command;
        }
    }

    public double getAdjustments() {
        return steering_adjust;
    }
}