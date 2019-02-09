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
  private TankDrive tank;
  private Gamepad gamepad;

  @Override
  public void robotInit() 
  {
    tank = new TankDrive(2, 3, 0, 1);
    gamepad = new Gamepad(0);
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
    tank.update(gamepad);
  }

  @Override
  public void testPeriodic() 
  {
  }
}
