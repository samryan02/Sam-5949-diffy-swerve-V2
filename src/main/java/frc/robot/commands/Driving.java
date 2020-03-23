/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Driving extends Command {
  double FL1 = 0;
  double FL2 = 0;
  double FR1 = 0;
  double FR2 = 0;
  double BL1 = 0;
  double BL2 = 0;
  double BR1 = 0;
  double BR2 = 0;

  public Driving() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.LFmod);
    requires(Robot.LBmod);
    requires(Robot.FRmod);
    requires(Robot.BRmod);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }
  
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //double[] wheelAngles = Robot.calculations.wheelAngles();
    double[] wheelAngles = Robot.calculations.wheelAngles();
    double[] wheelSpeeds = Robot.calculations.wheelSpeeds();

    FL1 = FL1+ wheelSpeeds[0];
    FL2 = FL2- wheelSpeeds[0];
    Robot.LFmod.setAngle1((wheelAngles[0])+(FL1));
    Robot.LFmod.setAngle2((wheelAngles[0])+(FL2));
    
    BL1 = BL1+ wheelSpeeds[1];
    BL2 = BL2- wheelSpeeds[1];
    Robot.LBmod.setAngle1((wheelAngles[1])+(BL1));
    Robot.LBmod.setAngle2((wheelAngles[1])+(BL2));
    
    FR1 = FR1+ wheelSpeeds[2];
    FR2 = FR2- wheelSpeeds[2];
    Robot.FRmod.setAngle1((wheelAngles[2])+(FR1));
    Robot.FRmod.setAngle2((wheelAngles[2])+(FR2));

    BR1 = BR1+ wheelSpeeds[3];
    BR2 = BR2- wheelSpeeds[3];
    Robot.BRmod.setAngle1((wheelAngles[3])+(BR1));
    Robot.BRmod.setAngle2((wheelAngles[3])+(BR2));
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
