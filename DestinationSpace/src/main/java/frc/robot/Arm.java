package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax.InputMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    
    public void update(Gamepad gamepad) {
        // double position = encoder.getPosition();
        
        double power = Math.min(gamepad.sticks.RIGHT_Y.getRaw(), .5);
        SmartDashboard.putBoolean("Is setting arm value in arm", power != 0);
        // motor.set(power);
        max.set(power);
        // if (position > minRange && position < maxRange) {
        // } else {
        //     motor.set(0);
        // }
    }

    public Spark getMotor() {
        return motor;
    }
}