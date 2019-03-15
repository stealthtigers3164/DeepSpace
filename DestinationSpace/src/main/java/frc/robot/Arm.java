package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm {
    private static double minRange = 0;
    private static double maxRange = 75;
    
    private CANSparkMax max;
    private CANEncoder encoder;
    
    public Arm(int port) {
        max = new CANSparkMax(4, MotorType.kBrushless);
        max.restoreFactoryDefaults();
        encoder = max.getEncoder();
    }
    
    public void update(LogitechGamepad gamepad) {
        double position = encoder.getPosition();
        double power = Math.min(Math.abs(gamepad.getRightYAxis()), .5);//gamepad.sticks.RIGHT_Y.getRaw(), .5);
        power *= Math.signum(gamepad.getRightYAxis());

        if (position > minRange && position < maxRange) {
            max.set(power);
        } else {
            if ((position < minRange && power > 0) ||
                (position > maxRange && power < 0)) {
                max.set(power);
            } else {
                max.set(0); 
            }
        }
    }

    public CANSparkMax getMotor() {
        return max;
    }
}