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
        
        if (gamepad.isADown()) {/*ball level one height*/ 
            targetHeight = 0;
            isBall = true;
        } else if(gamepad.isBDown()) {/*ball level two height*/
            //NOTE: Get value during testing
            targetHeight = 0;
            isBall = true;
        } else if(gamepad.isYDown()) {/*ball level three height*/
            targetHeight = -23000;
            isBall = true;
        } else if(gamepad.getDPadDir() == TopHatDir.DOWN){/*hatch level one height*/
            targetHeight = 0;
            isBall = false;
        } else if(gamepad.getDPadDir() == TopHatDir.LEFT) {/*hatch level two height*/
            //NOTE: Get value during testing
            targetHeight = 0;
            isBall = false;
        } else if(gamepad.getDPadDir() == TopHatDir.UP) {/*hatch level three height*/
            targetHeight = -23000;
            isBall = false;
        }
                
        if (targetHeight > 0) {
            result = true;
            double currentHeight = slide.getHeight();
            if (targetHeight == 0) {
                //NOTE: The linear slide is already going to be on the bottom so there is no need to 
                //adjust it
                currentHeight = targetHeight;
            }

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