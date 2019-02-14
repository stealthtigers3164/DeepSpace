package frc.robot;

import edu.wpi.first.wpilibj.Encoder;

public class AutoAlign {
    public Encoder encoder;
    private int targetHeight;
    private boolean isBall;

    public AutoAlign(int channelA, int channelB) {
        encoder = new Encoder(channelA, channelB);
        encoder.setDistancePerPulse(10);

        targetHeight = 0;
        isBall = false;
    }

    public void update(Gamepad gamepad, LinearSlide slide, Intake intake, Hatch hatch){
        if (gamepad.buttons.BUTTON_A.isOn()){
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
            tank.adjust();
            double currentHeight = encoder.getDistance();

            if (currentHeight < height){
                slide.update(1.0, 0.0);
            } else {
                slide.update(0, 0.0);
                
                if (isBall) {
                    intake.outputBall();
                } else {
                    hatch.output();
                }
            }
        }
    }
}