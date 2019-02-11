package frc.robot;

import edu.wpi.first.wpilibj.Encoder;

public class Encoder{
    
    public Encoder encoder;
    public boolean outputting;
    private Linearslide linear;
    private InOut inout;
    private TankDrive tank;

    public Encoder(int aChannel, int bChannel, LinearSlide linearslide, InOut inputoutput, TankDrive tankdrive){
        encoder = new Encoder(aChannel, bChannel);
        linear = linearslide;
        inout = inputoutput;
        tank = tankdrive;
        //set distance to distance per output gear rotation divided by 5356.8
        encoder.setDistancePerPulse()
    }

    public void update(Gamepad gamepad){
        if(gamepad.buttons.BUTTON_A.isOn()){
            ballOutput(/*ball level one height*/);
        } else if(gamepad.buttons.BUTTON_B.isOn()){
            ballOutput(/*ball level two height*/);
        } else if(gamepad.buttons.BUTTON_Y.isOn()){
            ballOutput(/*ball level three height*/);
        } else if(gamepad.tophat.getDir() == TopHatDir.DOWN){
            hatchOutput(/*hatch level one height*/);
        } else if(gamepad.tophat.getDir() == TopHatDir.LEFT){
            hatchOutput(/*hatch level two height*/);
        } else if(gamepad.tophat.getDir() == TopHatDir.UP){
            hatchOutput(/*hatch level three height*/);
        }
    }

    public void ballOutput(int height){
        outputting = true;
        linear.update(1.0, 0.0);
        double currentHeight = encoder.getDistance();
        while(!(currentHeight => height)){
            currentHeight = encoder.getDistance();
        }
        linear.update(0.0, 0.0);
        tank.adjust(12.75);
        inout.ball(-1.0);
        outputting = false;
        }
    }

    public void hatchOutput(int height){
        outputting = true;
        linear.update(1.0, 0.0);
        double currentHeight = encoder.getDistance();
        while(!(currentHeight => height)){
            currentHeight = encoder.getDistance();
        }
        linear.update(0.0, 0.0);
        tank.adjust(/*hatch distance*/);
        inout.ball(-1.0);
        outputting = false;
    }

    public boolean getState(){
        return outputting;
    }
}