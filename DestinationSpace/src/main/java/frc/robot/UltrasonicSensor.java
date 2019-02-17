package frc.robot;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UltrasonicSensor{

    private Ultrasonic ultra;

    public UltrasonicSensor(int one, int two){
        ultra = new Ultrasonic(one, two);
        ultra.setAutomaticMode(true);
        ultra.setEnabled(true);
    }

    public double getDistance() {
        double value = ultra.getRangeInches() / 12;
        return value;
    }

    public double getDistanceInches() {
        double value = ultra.getRangeInches();
        SmartDashboard.putBoolean("Is above zero", value > 0);
        return value;
    }

    public Ultrasonic getUltra() {
        return ultra;
    }
}