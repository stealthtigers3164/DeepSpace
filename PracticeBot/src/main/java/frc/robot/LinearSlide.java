package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.motor.MotorSet;
import frc.robot.motor.SparkMotor;

public class LinearSlide
{
    private Encoder encoder;
    private SpeedControllerGroup motorSet;
    private Preferences corrections;

    public LinearSlide(int rightPort, int leftPort, int channelA, int channelB) {
        corrections = Preferences.getInstance();
        SparkMotor right = new SparkMotor(rightPort);
        SparkMotor left = new SparkMotor(leftPort);
        motorSet = new SpeedControllerGroup(right.getMotor(), left.getMotor());
        motorSet.setInverted(false);
        encoder = new Encoder(channelA, channelB, false);
        //inches per pulse, based on encoder's ppr and motors' reduced free speed & stall torque
        encoder.setDistancePerPulse(0.1461); 
    }

    public void update(LogitechGamepad gamepad) {
        double position = gamepad.getLeftYAxis();
        SmartDashboard.putNumber("Position of joystick", position);
        if((-0.006 < position) && (position < 0.006)) {
            // double height = encoder.getDistance();
            double height = encoder.getDistance();
            motorSet.set(0);
            SmartDashboard.putNumber("Encoder count", encoder.get());
            SmartDashboard.putNumber("Height of lift", height);
            //run the motors to sustain the last position while the joystick reading returns "0"
            do {
                double error = height - encoder.getDistance();
                SmartDashboard.putNumber("Error", error);
                double correction = corrections.getDouble("Motor power", .25);
                SmartDashboard.putNumber("Corrective power", correction);
                if (error != 0) {
                    motorSet.set(0.0125);
                }
            } while ((-0.006 < position) && (position < 0.006));
        } else {
            motorSet.set(position);
        }
    }

    public double correctPower(boolean enabled, double p, double i, double d, double setpoint) {
        PIDController pidController = new PIDController(p, i, d, this.encoder, this.motorSet);
        pidController.setSetpoint(setpoint); 
        pidController.setEnabled(enabled);
        return pidController.get();                                              
    }
}