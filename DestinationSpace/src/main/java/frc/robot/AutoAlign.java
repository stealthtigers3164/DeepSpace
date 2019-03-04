package frc.robot;

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
    
    public boolean update(LogitechGamepad gamepad){
        boolean result = false;
        
        if (gamepad.isADown()){ //gamepad.buttons.BUTTON_A.isOn()){
            targetHeight = 0/*ball level one height*/;
            isBall = true;
        } else if(gamepad.isBDown()){//.buttons.BUTTON_B.isOn()){
            targetHeight = 0/*ball level one height*/;
            isBall = true;
        } else if(gamepad.isYDown()){//gamepad.buttons.BUTTON_Y.isOn()){
            targetHeight = 0/*ball level one height*/;
            isBall = true;
        } else if(gamepad.getDPadDir() == TopHatDir.DOWN){
            targetHeight = 0/*ball level one height*/;
            isBall = false;
        } else if(gamepad.getDPadDir() == TopHatDir.LEFT){
            targetHeight = 0/*ball level one height*/;
            isBall = false;
        } else if(gamepad.getDPadDir() == TopHatDir.UP){
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