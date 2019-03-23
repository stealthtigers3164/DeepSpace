/* package frc.robot.motor;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class CANSpark extends Motor<CANSparkMax> {
    private CANSparkMax sparkMax;
    private CANEncoder encoder;

    public CANSpark(int CANPort, MotorType type) {
        sparkMax = new CANSparkMax(CANPort, type);
        sparkMax.restoreFactoryDefaults();
        encoder = sparkMax.getEncoder();
    }

    @Override
    public void set(double power) {
        sparkMax.set(power);
    }

    @Override
    public CANSparkMax getMotor() {
        return sparkMax;
    }

    public CANEncoder getEncoder() {
        return encoder;
    }
} */