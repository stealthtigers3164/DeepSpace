package frc.robot;

import frc.robot.motor.MotorSet;
import frc.robot.motor.Motor;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UnitSustain<T extends Motor> {
    private MotorSet<T> motors;
    private double minRange;
    private double maxRange;

    private double targetHeight;
    private boolean free;

    public UnitSustain(MotorSet<T> motors, double minRange, double maxRange) {
        this.motors = motors;
        this.minRange = minRange;
        this.maxRange = maxRange;
    
        targetHeight = 0; 
        free = true;
    }

    public void update(double currentHeight) {
        SmartDashboard.putBoolean("free", free);
        if (!free) {
            double distance = targetHeight - currentHeight;
            double power = distance / maxRange;

            SmartDashboard.putNumber("the targetheight", targetHeight);
            SmartDashboard.putNumber("the currentHeight", currentHeight); 
            SmartDashboard.putNumber("the power", power);

            motors.power(power);
        }

    }

    public void moveFree() {
        free = true;
    }

    public void setHeight(double targetHeight) {
        this.targetHeight = targetHeight;
        free = false;
        // if (minRange != 0 || maxRange != 0) {
        //     if (targetHeight < minRange) {
        //         targetHeight = minRange;
        //     }
        //     if (targetHeight > maxRange) {
        //         targetHeight = maxRange;
        //     }
        // }
    }
}