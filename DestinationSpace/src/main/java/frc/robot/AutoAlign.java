package frc.robot;

import frc.robot.mechanisms.LinearSlide;
import frc.robot.mechanisms.Arm;

public class AutoAlign {
    private LinearSlide slide;
    private Intake intake;
    private Hatch hatch;
    private Arm arm;
    
    public AutoAlign(LinearSlide slide, Intake intake, Hatch hatch, Arm arm) {
        this.slide = slide;
        this.intake = intake;
        this.hatch = hatch;
        this.arm = arm;
    } 
    
    public void update(LogitechGamepad gamepad){
        if (gamepad.isADown()) {/*ball level one height*/ 
            // arm.sustainPosition(25);
        } else if(gamepad.isBDown()) {/*ball level two height*/
            // slide.sustainHeight(-10000);
            // arm.sustainPosition(25);
        } else if(gamepad.isYDown()) {/*ball level three height*/
            // slide.sustainHeight(-23000);
            // arm.sustainPosition(75);
        } else if(gamepad.getDPadDir() == TopHatDir.DOWN){/*hatch level one height*/
            // arm.sustainPosition(25);
        } else if(gamepad.getDPadDir() == TopHatDir.LEFT) {/*hatch level two height*/
            // slide.sustainHeight(-10000);
            // arm.sustainPosition(25);
        } else if(gamepad.getDPadDir() == TopHatDir.UP) {/*hatch level three height*/
            // slide.sustainHeight(-23000);
            // arm.sustainPosition(75);
        }
    }
}