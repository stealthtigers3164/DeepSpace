package frc.robot;

import frc.robot.Gamepad.TopHatDir;

public class AutoAlign {
    private LinearSlide slide;
    private Intake intake;
    private Hatch hatch;
    
    private int targetHeight;
    private boolean isBall;
    
    public AutoAlign(LinearSlide slide, Intake intake, Hatch hatch) {
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
            double currentHeight = slide.getHeight();
            
            if (currentHeight < targetHeight){
                slide.setPower(1.0);
            } else {
                slide.setPower(0);
                
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