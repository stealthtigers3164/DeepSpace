package frc.robot.motor;

import edu.wpi.first.wpilibj.Spark;

public class SparkMotor extends Motor<Spark> {
    private Spark spark;

    public SparkMotor(int port) {
        spark = new Spark(port);
    }

    @Override
    public void set(double power) {
        spark.set(power);
    }

    @Override
    public Spark getMotor(){
        return spark;
    }
}