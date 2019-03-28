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
    private boolean encoderDirection;
    private Preferences corrections;

    public LinearSlide(int rightPort, int leftPort, boolean isMotorInverted, int channelA, int channelB, boolean isEncoderInverted, double distancePerPulse) {
        SparkMotor right = new SparkMotor(rightPort);
        SparkMotor left = new SparkMotor(leftPort);
        motorSet = new SpeedControllerGroup(right.getMotor(), left.getMotor());
        motorSet.setInverted(isMotorInverted);
        encoder = new Encoder(channelA, channelB);
        setEncoderDirection(isEncoderInverted);
        //inches per pulse, based on encoder's ppr and motors' reduced free speed & stall torque
        encoder.setDistancePerPulse(distancePerPulse); //distance per pulse = 0.1461
    }

    public void update(LogitechGamepad gamepad, double maxDistance, double correctivePower) {
        double position = gamepad.getLeftYAxis();
        SmartDashboard.putNumber("Position of joystick", position);
        setLimit(maxDistance);
        absoluteMode(position, true);
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
                SmartDashboard.putNumber("Corrective power", correctivePower);
                if (error != 0) {
                    motorSet.set(correctivePower);
                }
            } while ((-0.006 < position) && (position < 0.006));
        } else {
            motorSet.set(position);
        }
    }

    public void setLimit(double maxDistance) {
        if((Math.abs(encoder.getDistance())) == (Math.abs(maxDistance))) {
            encoder.reset();
        }
    }
    //this won't work to actually limit the slide, will reset() whenever value is hit UNLESS decrement method works

    public void absoluteMode(double direction, boolean enabled) {
        boolean isEncoderInverted = getEncoderDirection();
        if(Math.signum(direction) < 0) {
            encoder.setReverseDirection(!isEncoderInverted);
        } else {
            encoder.setReverseDirection(isEncoderInverted);
        }
    }
    //i don't think this will actually decrement

    public void setEncoderDirection(boolean isEncoderInverted) {
        if(isEncoderInverted) {
            encoderDirection = true;
        } else {
            encoderDirection = false;
        }
        setEncoderDirection(encoderDirection);
    }

    public boolean getEncoderDirection() {
        return encoderDirection;
    }

    public double correctPower(boolean enabled, double p, double i, double d, double setpoint) {
        PIDController pidController = new PIDController(p, i, d, this.encoder, this.motorSet);
        pidController.setSetpoint(setpoint); 
        pidController.setEnabled(enabled);
        return pidController.get();                                              
    }
}