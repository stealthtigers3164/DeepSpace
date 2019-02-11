package frc.robot;

import edu.wpi.frc.wpilibj.Ultrasonic;

public class UltrasonicSensor{

    private Ultrasonic ultra;

    public Ultrasonic(int one, int two){
        ultra = new Ultrasonic(one, two)
    }

    public double adjustDistance(double distance){
        double range = ultra.getRangeInches();
        distance_adjust = (range - distance) * 0.1;
        return distance_adjust;
    }
}