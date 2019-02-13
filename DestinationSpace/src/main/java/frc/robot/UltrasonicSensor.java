package frc.robot;

import edu.wpi.first.wpilibj.Ultrasonic;

public class UltrasonicSensor{

    private Ultrasonic ultra;

    public UltrasonicSensor(int one, int two){
        ultra = new Ultrasonic(one, two);
        ultra.setAutomaticMode(true);
    }

    public double getDistance(){
        return ultra.getRangeInches() / 12;
    }

    public double getDistanceInches() {
        return ultra.getRangeInches();
    }
}