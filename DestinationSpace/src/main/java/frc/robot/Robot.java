/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot 
{
  private Ultrasonic ultra;
  private TankDrive tank;
  private Gamepad gamepad;
  private Limelight limelight;
  private Encoder encoder;
  private InOut inputoutput;
  private LinearSlide linear;

  @Override
  public void robotInit() 
  {
    gamepad = new Gamepad(0);

    limelight = new Limelight();
    ultra = new Ultrasonic(o0, 1);
    tank = new TankDrive(2, 3, 0, 1, ultra, limelight);

    linear = new LinearSlide(rightPort, leftPort);
    inputoutput = new InOut(forwardChannel, reverseChannel, leftSpark, rightSpark);
    encoder = new Encoder(inputoutput, linear, tank);
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
    inputoutput.resetHatch();

    tank.update(gamepad, encoder.getState());
    inputoutput.update(gamepad, encoder.getState());
  }

  @Override
  public void testPeriodic() 
  {
  }
}
