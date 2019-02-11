/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include "Robot.h"

#include <iostream>

void Robot::RobotInit() {
  frontLeft = new frc::Spark(2);
  backLeft = new frc::Spark(3);
  frontRight = new frc::Spark(0);
  backRight = new frc::Spark(1);

  camera = frc::CameraServer::GetInstance()->StartAutomaticCapture(0);
  camera.SetResolution(640, 480);
  // cvSink = frc::CameraServer::GetInstance()->GetVideo();
  // outputStreamStd = frc::CameraServer::GetInstance()->PutVideo("Gray", 640, 480);
  
  gamepad = new Gamepad(0);
}

/**
 * This function is called every robot packet, no matter the mode. Use
 * this for items like diagnostics that you want ran during disabled,
 * autonomous, teleoperated and test.
 *
 * <p> This runs after the mode specific periodic functions, but before
 * LiveWindow and SmartDashboard integrated updating.
 */
void Robot::RobotPeriodic() {}

/**
 * This autonomous (along with the chooser code above) shows how to select
 * between different autonomous modes using the dashboard. The sendable chooser
 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
 * remove all of the chooser code and uncomment the GetString line to get the
 * auto name from the text box below the Gyro.
 *
 * You can add additional auto modes by adding additional comparisons to the
 * if-else structure below with additional strings. If using the SendableChooser
 * make sure to add them to the chooser code above as well.
 */
void Robot::AutonomousInit() {
}

void Robot::AutonomousPeriodic() {
}

void Robot::TeleopInit() {}

#include <math.h>

void Robot::TeleopPeriodic() {
  double rightY = gamepad->sticks->LEFT_X->getRaw();
  double leftX = -gamepad->sticks->RIGHT_Y->getRaw();

  double leftPowerRaw = rightY - leftX;
  double rightPowerRaw = rightY + leftX;
  double leftPower = signum(leftPowerRaw) * min(abs(leftPowerRaw), 1);
  double rightPower = signum(rightPowerRaw) * min(abs(rightPowerRaw), 1);

  frontRight->Set(rightPower * .50);
  backRight->Set(rightPower * .50);

  frontLeft->Set(leftPower * .50);
  backLeft->Set(leftPower * .50);

  // cvSink.GrabFrame(source);
  // cvtColor(source, output, cv::COLOR_BGR2GRAY);
  // outputStreamStd.PutFrame(output);
}

void Robot::TestPeriodic() {}

#ifndef RUNNING_FRC_TESTS
int main() { return frc::StartRobot<Robot>(); }
#endif
