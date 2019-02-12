package frc.robot;

import edu.wpi.first.wpilibj.Encoder;

public class Encoder{
    
    public Encoder encoder;
    private Linearslide linear;
    private InOut inout;
    private TankDrive tank;
    private int targetHeight;
    private boolean isBall;

    public Encoder(int aChannel, int bChannel, LinearSlide linearslide, InOut inputoutput, TankDrive tankdrive){
        encoder = new Encoder(aChannel, bChannel);
        linear = linearslide;
        inout = inputoutput;
        tank = tankdrive;
        //set distance to distance per output gear rotation divided by 5356.8
        encoder.setDistancePerPulse();

        targetHeight = 0;
        isBall = false;
    }

    public void update(Gamepad gamepad){
        if(gamepad.buttons.BUTTON_A.isOn()){
            targetHeight = 0/*ball level one height*/;
            isBall = true;
        } else if(gamepad.buttons.BUTTON_B.isOn()){
            targetHeight = 0/*ball level one height*/;
            isBall = true;
        } else if(gamepad.buttons.BUTTON_Y.isOn()){
            targetHeight = 0/*ball level one height*/;
            isBall = true;
        } else if(gamepad.tophat.getDir() == TopHatDir.DOWN){
            targetHeight = 0/*ball level one height*/;
            isBall = false;
        } else if(gamepad.tophat.getDir() == TopHatDir.LEFT){
            targetHeight = 0/*ball level one height*/;
            isBall = false;
        } else if(gamepad.tophat.getDir() == TopHatDir.UP){
            targetHeight = 0/*ball level one height*/;
            isBall = false;
        }

        if (targetHeight > 0) {
            if (isBall) {
                ballOutput(targetHeight);
            } else {
                hatchOutput(targetHeight);
            }
        }
    }

    public void ballOutput(int height){
        double currentHeight = encoder.getDistance();
        if (currentHeight < height){
            linear.update(1.0, 0.0);
            tank.adjust();
        } else {
            linear.update(0.0, 0.0);
            inout.ball(-1.0);
        }
    }

    public void hatchOutput(int height){
        double currentHeight = encoder.getDistance();
        if (currentHeight < height){
            linear.update(1.0, 0.0);
            tank.adjust();
        } else {
            linear.update(0.0, 0.0);
            inout.hatch();
        }
    }
}