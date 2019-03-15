package frc.robot;

public class AutoAlign {
    private LinearSlide slide;
    private Intake intake;
    private Hatch hatch;
    
    public AutoAlign(LinearSlide slide, Intake intake, Hatch hatch) {
        this.slide = slide;
        this.intake = intake;
        this.hatch = hatch;
    }
    
    public void update(LogitechGamepad gamepad){
        if (gamepad.isADown()) {/*ball level one height*/ 
            arm.sustainPosition(60);
        } else if(gamepad.isBDown()) {/*ball level two height*/
            slide.sustainHeight(-10000);
            arm.sustainPosition(60);
        } else if(gamepad.isYDown()) {/*ball level three height*/
            slide.sustainHeight(-23000);
            arm.sustainPosition(75);
        } else if(gamepad.getDPadDir() == TopHatDir.DOWN){/*hatch level one height*/
            arm.sustainPosition(60);
        } else if(gamepad.getDPadDir() == TopHatDir.LEFT) {/*hatch level two height*/
            slide.sustainHeight(-10000);
            arm.sustainPosition(60);
        } else if(gamepad.getDPadDir() == TopHatDir.UP) {/*hatch level three height*/
            slide.sustainHeight(-23000);
            arm.sustainPosition(75);
        }
    }
}