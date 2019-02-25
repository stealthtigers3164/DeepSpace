/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot 
{
  private Gamepad gamepad1;
  private Gamepad gamepad2;

  private TankDrive tank;
  
  private Limelight limelight;
  private LinearSlide linear;
  private Arm arm;
  private Intake intake;
  private Hatch hatch;

  private AutoAlign alignment;

  @Override
  public void robotInit() 
  {
    gamepad1 = new Gamepad(0);
    gamepad2 = new Gamepad(1);
    
    // limelight = new Limelight();

    tank = new TankDrive(9, 8, 7, 6, limelight);
    CameraServer.getInstance().startAutomaticCapture();

    linear = new LinearSlide(0, 1, 0, 1);
    arm = new Arm(4);
    intake = new Intake(3, 2);
    hatch = new Hatch(4, 5);
    // alignment = new AutoAlign(linear, intake, hatch);
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
    intake.update(gamepad1);
    hatch.update(gamepad2);
    // alignment.update(gamepad1);
    tank.update(gamepad1, false);
  }

  @Override
  public void testPeriodic() 
  {
  }
}
