package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Arm {
    private static double minRange = 0;
    private static double maxRange = 1;
    
    private CANSparkMax sparkMax;
    private CANEncoder encoder;
    
    public Arm(int deviceID) {
        sparkMax = new CANSparkMax(deviceID, MotorType.kBrushless);
        sparkMax.restoreFactoryDefaults();
        encoder = sparkMax.getEncoder();
        
    }
    
    public void update(Gamepad gamepad) {
        double power = 1;
        double position = encoder.getPosition();
        
        if (position > minRange && position < maxRange) {
            sparkMax.set(power);
        } else {
            sparkMax.set(0);
        }
    }
}