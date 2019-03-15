package frc.robot;

import frc.robot.motor.MotorSet;
import frc.robot.motor.Motor;

public class UnitSustain<T extends Motor> {
    private MotorSet<T> motors;
    private double minRange;
    private double maxRange;

    private double targetHeight;

    public UnitSustain(MotorSet<T> motors, double minRange, double maxRange) {
        this.motors = motors;
        this.minRange = minRange;
        this.maxRange = maxRange;

        targetHeight = 0; 
    }

    public void update(double currentHeight) {
        if (targetHeight == 0) {
            return;
        }

        double distance = targetHeight - currentHeight;
        double power = distance / maxRange;

        motors.power(power);
    }

    public void setHeight(double targetHeight) {
        this.targetHeight = targetHeight;

        if (minRange != 0 || maxRange != 0) {
            if (targetHeight < minRange) {
                targetHeight = minRange;
            }
            if (targetHeight > maxRange) {
                targetHeight = maxRange;
            }
        }
    }
}