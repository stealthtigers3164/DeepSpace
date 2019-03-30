package frc.robot.mechanisms;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.LogitechGamepad;
import frc.robot.motor.MotorSet;
import frc.robot.motor.SparkMotor;
import frc.robot.LimitSwitch;
import frc.robot.LimitSwitch.OperatingMode;

public class LinearSlide
{
    private Encoder encoder;
    private SpeedControllerGroup motorSet;

    private LimitSwitch bottom;
    private double minRange;
    private double maxRange;

    /**
     * Inititalize a linear slide object with two motors, an encoder, and the distance per pulse
     * @param rightPort The port for the right-most motor
     * @param leftPort The port for the left-most motor
     * @param isMotorInverted Whether the motor is inverted
     * @param channelA DIO port for encoder channel A
     * @param channelB DIO port for encoder channel B
     * @param isEncoderInverted Whether the encoder should invert its counts
     * @param distancePerPulse Distance per encoder pulse, in inches
     * @param bottomPort DIO port for limit switch
     */
    public LinearSlide(int rightPort, int leftPort, boolean isMotorInverted, int channelA, int channelB, boolean isEncoderInverted, double distancePerPulse, int bottomPort) {
        SparkMotor right = new SparkMotor(rightPort);
        SparkMotor left = new SparkMotor(leftPort);
        motorSet = new SpeedControllerGroup(right.getMotor(), left.getMotor());
        motorSet.setInverted(isMotorInverted);
        encoder = new Encoder(channelA, channelB, isEncoderInverted);
        // setEncoderDirection(isEncoderInverted);
        //inches per pulse, based on encoder's ppr and motors' reduced free speed & stall torque
        encoder.setDistancePerPulse(distancePerPulse); //distance per pulse = 0.1461

        bottom = new LimitSwitch(bottomPort, OperatingMode.NC);
        minRange = 0;
        maxRange = 100000000;
    }

    /**
     * Control the linear slide with a gamepad, using a relative encoder to sustain height when joystick pos returns "0"
     * @param gamepad Logitech gamepad object with which to control the slide
     * @param enableSustain Whether the slide should sustain its height when the joystick is neutral
     * @param counterPower The counter-power for sustaining slide height
     */
    public void update(LogitechGamepad gamepad, boolean enableSustain, /*double maxDistance,*/ double counterPower) {
        double position = gamepad.getLeftYAxis();
        SmartDashboard.putNumber("Position of joystick", position);
        // setLimit(maxDistance);
        // absoluteMode(position, true);
        if(((-0.006 < position) && (position < 0.006)) && enableSustain) {
            double height = encoder.getDistance();
            motorSet.set(0);
            SmartDashboard.putNumber("Encoder count", encoder.get());
            SmartDashboard.putNumber("Height of lift", height);
            //run the motors to sustain the last position while the joystick reading returns "0"
            // do {
                double error = height - encoder.getDistance();
                SmartDashboard.putNumber("Error", error);
                SmartDashboard.putNumber("Corrective power", counterPower);
                if (error != 0) {
                    motorSet.set(counterPower);
                }
            // } while ((-0.006 < position) && (position < 0.006));
        } else {
            motorSet.set(position);
        }
    }
    /**
     * Control the linear slide using a gamepad
     * @param gamepad
     */
    public void update(LogitechGamepad gamepad) {
        double position = gamepad.getLeftYAxis();
        SmartDashboard.putNumber("Position of joystick", position);
        double height = encoder.getDistance();
        SmartDashboard.putNumber("height", height);
        SmartDashboard.putBoolean("limitSwitch Value", bottom.isPressed());
        double power = Math.min(Math.abs(position), .75) * Math.signum(position);
        
        if ((!bottom.isPressed()) && (height < maxRange)) {
            motorSet.set(position);
        }
        else {
            if ((bottom.isPressed() && position < 0) || (height > maxRange && position > 0)) {
                motorSet.set(position);
            } else {
                motorSet.set(0);
            }
        }
    }

    //this won't work to actually limit the slide, will reset() whenever value is hit UNLESS decrement method works
    public void setLimit(double maxDistance) {
        if((Math.abs(encoder.getDistance())) == (Math.abs(maxDistance))) {
            encoder.reset();
        }
    }

    //this function does not decrement
    // public void absoluteMode(double direction, boolean enabled) {
    //     boolean isEncoderInverted = getEncoderDirection();
    //     if(Math.signum(direction) < 0) {
    //         encoder.setReverseDirection(!isEncoderInverted);
    //     } else {
    //         encoder.setReverseDirection(isEncoderInverted);
    //     }
    // }
}