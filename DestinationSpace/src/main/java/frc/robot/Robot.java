/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.*;

public class Robot extends TimedRobot 
{
  private Compressor compressor;

  private LogitechGamepad gamepad1;
  private LogitechGamepad gamepad2;

  private TankDrive tank;
  
  private Limelight limelight;
  private LinearSlide linear;
  private Arm arm;
  private Intake intake;
  private Hatch hatch;
  private Climb climb;

  private AutoAlign alignment;

  @Override
  public void robotInit() 
  {
    // gamepad1 = new Gamepad(0);
    // gamepad2 = new Gamepad(1);

    gamepad1 = new LogitechGamepad(0);
    gamepad2 = new LogitechGamepad(1);

    limelight = new Limelight();

    tank = new TankDrive(0, 1, 3, 2, limelight, 2, 3);
    CameraServer.getInstance().startAutomaticCapture();

    linear = new LinearSlide(0, 1, 0, 1);
    arm = new Arm(4);
    intake = new Intake(3, 2);
    hatch = new Hatch(4, 5);
    compressor = new Compressor(0);
    compressor.setClosedLoopControl(true);
    climb = new Climb(0, 0, 0, 0, 0); //definitely change these
    alignment = new AutoAlign(linear, intake, hatch);
  }

  @Override
  public void robotPeriodic() 
  {
  }

  @Override
  public void autonomousInit() 
  {
  }

  @Override
  public void autonomousPeriodic() 
  {
  }

  @Override
  public void teleopPeriodic() 
  {
    hatch.reset();

    linear.update(gamepad2);
    arm.update(gamepad2);
    intake.update(gamepad2);
    hatch.update(gamepad2);
    alignment.update(gamepad1);
    tank.update(gamepad1, false);
  
    // climb.update(gamepad1);
  }

  Timer testTimer = null;
  int testIndex = 0;
  int testStagePointer = 0;

  @Override
  public void testPeriodic() 
  {
    if (testTimer == null) {
      testTimer = new Timer();
    }

    switch(testIndex)
    {
      case 0:
      {
        if (testStagePointer == 0) {
          testTimer.reset();
          testTimer.start();
          linear.setPower(.3);

          if (testTimer.get() >= 2) {
            testTimer.stop();
            testTimer.reset();
            testTimer.start();
    
            linear.setPower(-.3);
            ++testStagePointer;
          }
        }
        else if (testStagePointer == 1) {
          if (testTimer.get() >= 1) {
            testTimer.stop();
            testTimer.reset();
    
            linear.setPower(0);
            testStagePointer = 0;
          }
        }

      }break;
      case 1:
      {
        if (testStagePointer == 0) {
          testTimer.reset();
          testTimer.start();

          tank.setPower(-.5, .5);
          
          if (testTimer.get() >= 1) {
            testTimer.stop();
            testTimer.reset();
    
            tank.setPower(.5, -.5);
            ++testStagePointer;
          }
        }
        else if (testStagePointer == 1) {
          if (testTimer.get() >= 1) {
            testTimer.stop();
            testTimer.reset();
    
            tank.setPower(0, 0);
            testStagePointer = 0;
          }
        }
      }break;
      case 2:
      {
        if (testStagePointer == 0) {
          testTimer.reset();
          testTimer.start();

          arm.getMotor().set(.3);
          
          if (testTimer.get() >= .5) {
            testTimer.stop();
            testTimer.reset();
    
            arm.getMotor().set(-.3);
            ++testStagePointer;
          }
        }
        else if (testStagePointer == .5) {
          if (testTimer.get() >= 1) {
            testTimer.stop();
            testTimer.reset();
    
            arm.getMotor().set(0);
            testStagePointer = 0;
          }
        }
      }break;
      case 3:
      {
        if (testStagePointer == 0) {
          testTimer.reset();
          testTimer.start();

          intake.setPower(1);
          
          if (testTimer.get() >= .5) {
            testTimer.stop();
            testTimer.reset();
    
            intake.setPower(-1);
            ++testStagePointer;
          }
        }
        else if (testStagePointer == .5) {
          if (testTimer.get() >= 1) {
            testTimer.stop();
            testTimer.reset();
    
            intake.setPower(0);
            testStagePointer = 0;
          }
        }
      }break;
      default:
      {
        //NOTE: This is not good something went wrong in the test function
      }break;
    }
  }
}
