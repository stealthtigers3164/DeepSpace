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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.mechanisms.LinearSlide;
import frc.robot.mechanisms.Arm;

public class Robot extends TimedRobot 
{
  private Compressor compressor;

  private LogitechGamepad gamepad1;
  private LogitechGamepad gamepad2;

  private TankDrive tank;
  
  private Limelight limelight;
  private LinearSlide linear;
  private Preferences prefs;
  private double slidePower;
  private Arm arm;
  private Intake intake;
  private Hatch hatch;
  // private Climb climb;

  private AutoAlign alignment;
  private CameraServer camera;

  @Override
  public void robotInit() 
  {
    gamepad1 = new LogitechGamepad(0);
    gamepad2 = new LogitechGamepad(1);

    limelight = new Limelight();
    limelight.ledOn(false);

    tank = new TankDrive(6, 7, 8, 9, limelight, 2, 3);//2310 for practice pneumatic bot
    camera.getInstance().startAutomaticCapture(0);
    // camera.getInstance().startAutomaticCapture(1);

    linear = new LinearSlide(0, 1, true, 0, 1, true, 0.1461, 4);
    prefs = Preferences.getInstance(); 
    slidePower = prefs.getDouble("Slide Power", 0.0125);

    arm = new Arm(4);
    intake = new Intake(3, 2);
    hatch = new Hatch(4, 5);
    compressor = new Compressor(0);
    compressor.setClosedLoopControl(true);
    // climb = new Climb(0, 1, 2); //definitely change these
    alignment = new AutoAlign(linear, intake, hatch, arm);
  }

  @Override
  public void robotPeriodic() {
    // limelight.ledOn(false);
  }

  @Override
  public void autonomousInit() 
  {
  }

  public void update() {
    hatch.reset();

    linear.update(gamepad2);
    // linear.update(gamepad2, true, slidePower);
    // SmartDashboard.putString("update", "update");    
    arm.update(gamepad2);

    intake.update(gamepad2);
    hatch.update(gamepad2);
    alignment.update(gamepad1);
    // climb.update(gamepad1);
    limelight.setCameraMode(true);
    tank.update(gamepad1, gamepad1.isXDown());
  }

  @Override
  public void autonomousPeriodic() 
  {
    update();
  }

  @Override
  public void teleopPeriodic() 
  {
    update();
  }

  Timer testTimer = null;
  int testIndex = 0;
  int testStagePointer = 0;

  @Override
  public void testPeriodic() {
  }
}
