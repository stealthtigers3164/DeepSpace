package frc.robot;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UltrasonicSensor{

    private Ultrasonic ultra;

    public UltrasonicSensor(int ping, int echo){
        ultra = new Ultrasonic(ping, echo);
        ultra.setEnabled(true);
        ultra.setAutomaticMode(true);
    }

    public double getDistanceInches() {
        double value = ultra.getRangeInches();
        SmartDashboard.putBoolean("Is above zero", value > 0);
        SmartDashboard.putNumber("inches", value);
        return value;
    }

    public Ultrasonic getUltra() {
        return ultra;
    }
}