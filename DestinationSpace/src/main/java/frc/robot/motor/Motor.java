package frc.robot.motor;

public abstract class Motor<T> {
    public abstract void set(double power);
    public abstract T getMotor();
}