package frc.robot.mechanisms;

import frc.robot.LogitechGamepad;

import frc.robot.motor.MotorSet;
import frc.robot.motor.CANSpark;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.UnitSustain;

public class Arm {
    private static double minRange = 0;
    private static double maxRange = 75;
    
    private MotorSet<CANSpark> motor;
    private CANEncoder encoder;
    
    private UnitSustain<CANSpark> positionSustain;

    public Arm(int port) {
        motor = new MotorSet<CANSpark>();
        CANSpark sparkMax = new CANSpark(4, MotorType.kBrushless);
        motor.add(sparkMax);
        encoder = sparkMax.getEncoder();

        positionSustain = new UnitSustain(motor, minRange, maxRange);
    }
    
    public void update(LogitechGamepad gamepad) {
        double position = encoder.getPosition();
        double power = Math.min(Math.abs(gamepad.getRightYAxis()), .3);//gamepad.sticks.RIGHT_Y.getRaw(), .5);
        power *= -Math.signum(gamepad.getRightYAxis());

        SmartDashboard.putNumber("position", position);

        if (gamepad.isXDown()) {
            sustainPosition(25);
        }

        if (power != 0) {
            positionSustain.moveFree();
        }

        if (position > minRange && position < maxRange) {
            motor.power(power);
        } else {
            if ((position < minRange && power > 0) ||
                (position > maxRange && power < 0)) {
                motor.power(power);
            } else {
                motor.power(0); 
            }
        }

        SmartDashboard.putString("update", "update");
        positionSustain.update(position);
        SmartDashboard.putString("update", "test");

        SmartDashboard.putBoolean("gamepad.isXDown()", gamepad.isXDown());
    }

    public void sustainPosition(double position) {
        positionSustain.setHeight(position);
    }

    public CANSpark getMotor() {
        return motor.get(0);
    }
}