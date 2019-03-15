package frc.robot.motor;

import java.util.ArrayList;

public class MotorSet<T extends Motor> {
    private ArrayList<T> motors;

    public MotorSet() {
        motors = new ArrayList<T>();
    }

    public void add(T Motor) {
        motors.add(Motor);
    }

    public void power(double power) {
        for (T motor : motors) {
            motor.set(power);
        }
    }

    public T get(int i) {
        return motors.get(i);
    }
}