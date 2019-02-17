package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Gamepad.TopHatDir;

public class AutoAlign {
    private Encoder encoder;
    private LinearSlide slide;
    private Intake intake;
    private Hatch hatch;

    private int targetHeight;
    private boolean isBall;

    public AutoAlign(int channelA, int channelB, LinearSlide slide, Intake intake, Hatch hatch) {
        encoder = new Encoder(channelA, channelB);
        encoder.setDistancePerPulse(10);

        this.slide = slide;
        this.intake = intake;
        this.hatch = hatch;

        targetHeight = 0;
        isBall = false;
    }

    public boolean update(Gamepad gamepad){
        boolean result = false;

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
            result = true;
            double currentHeight = encoder.getDistance();

            if (currentHeight < targetHeight){
                slide.update(1.0, 0.0);
            } else {
                slide.update(0, 0.0);
                
                if (isBall) {
                    intake.outputBall();
                } else {
                    hatch.release();
                }
            }
        }

        return result;
    }
}