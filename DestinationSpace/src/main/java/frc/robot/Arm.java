package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Spark;

public class Arm {
    private static double minRange = 0;
    private static double maxRange = 1;
    
    private Spark motor;
    private CANEncoder encoder;

    private CANSparkMax max;
    
    public Arm(int port) {
        // motor = new Spark(port);

        max = new CANSparkMax(4, MotorType.kBrushless);
        max.restoreFactoryDefaults();
        encoder = max.getEncoder();
        
    }
    
    public void update(LogitechGamepad gamepad) {
        double position = encoder.getPosition();
        double power = Math.min(gamepad.getRightYAxis(), .5);//gamepad.sticks.RIGHT_Y.getRaw(), .5);

        if (position > minRange && position < maxRange) {
            max.set(power);
        } else {
            if ((position < minRange && power > 0) ||
                (position > maxRange && power < 0)) {
                motor.set(power);
            } else {
                motor.set(0); 
            }
        }
    }

    public Spark getMotor() {
        return motor;
    }
}