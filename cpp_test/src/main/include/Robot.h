/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#pragma once

#include <string>

#include "Gamepad.h"

#include <CameraServer.h>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/core/core.hpp>

#include <frc/TimedRobot.h>
#include <frc/Spark.h>
#include <frc/smartdashboard/SendableChooser.h>

class Robot : public frc::TimedRobot {
 public:
  void RobotInit() override;
  void RobotPeriodic() override;
  void AutonomousInit() override;
  void AutonomousPeriodic() override;
  void TeleopInit() override;
  void TeleopPeriodic() override;
  void TestPeriodic() override;

private:
  frc::Spark* frontLeft;
  frc::Spark* backLeft;
  frc::Spark* frontRight;
  frc::Spark* backRight;

  Gamepad* gamepad;

  cs::UsbCamera camera;
  cs::CvSink cvSink;
  cs::CvSource outputStreamStd;
  cv::Mat source;
  cv::Mat output;
};
