/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot 
{
  private UltrasonicSensor ultra;
  private TankDrive tank;
  private Gamepad gamepad;
  private Limelight limelight;
  private Encoder encoder;
  private Intake intake;
  private Hatch hatch;
  private LinearSlide linear;

  private AutoAlign alignment;

  @Override
  public void robotInit() 
  {
    // gamepad = new Gamepad(0);

    limelight = new Limelight();

    ultra = new UltrasonicSensor(0, 1);
    // tank = new TankDrive(2, 3, 0, 1, ultra, limelight);

    // linear = new LinearSlide(rightPort, leftPort);
    // intake = new Intake(leftSpark, rightSpark);
    // hatch = new Hatch(forwardChannel, reverseChannel);
    // encoder = new Encoder(inputoutput, linear, tank);
    alignment = new AutoAlign(0, 1);
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
    SmartDashboard.putNumber("tx", limelight.getTX());
    SmartDashboard.putNumber("distance", ultra.getDistance());
    SmartDashboard.putNumber("distance inches", ultra.getDistanceInches());

    // inputoutput.resetHatch();
    // alignment.update(gamepad, linear, intake, hatch);
    // tank.update(gamepad, encoder.getState());
    // inputoutput.update(gamepad, encoder.getState());
  }

  @Override
  public void testPeriodic() 
  {
  }
}
