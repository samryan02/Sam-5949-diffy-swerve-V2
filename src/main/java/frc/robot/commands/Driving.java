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
  double temp1 = 0;
  double temp2 = 0;
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
    
    double FL1  = Robot.LFmod.getAngle1();
    double FL2 = Robot.LFmod.getAngle2();
    double angle = Robot.LFmod.getAngle();
    temp1 = temp1 + wheelSpeeds[0];
    Robot.LFmod.setAngle1((wheelAngles[0]-angle)+(wheelSpeeds[0])+FL1);
    temp2 = temp2 + wheelSpeeds[0];
    Robot.LFmod.setAngle2((wheelAngles[0]-angle)+(-FL2-wheelSpeeds[0]));
    
    double BL1  = Robot.LBmod.getAngle1();
    double BL2 = Robot.LBmod.getAngle2();
    double angle2 = Robot.LBmod.getAngle();
    Robot.LBmod.setAngle1((wheelAngles[1]-angle2)+(wheelSpeeds[1]+BL1));
    Robot.LBmod.setAngle2((wheelAngles[1]-angle2)+(-BL2-wheelSpeeds[1]));
    
    double FR1  = Robot.FRmod.getAngle1();
    double FR2 = Robot.FRmod.getAngle2();
    double angle3 = Robot.FRmod.getAngle();
    Robot.FRmod.setAngle1((wheelAngles[3]-angle3)+(wheelSpeeds[3]+FR1));
    Robot.FRmod.setAngle2((wheelAngles[3]-angle3)+(-FR2-wheelSpeeds[3]));

    double BR1  = Robot.BRmod.getAngle1();
    double BR2 = Robot.BRmod.getAngle2();
    double angle4 = Robot.BRmod.getAngle();
    Robot.BRmod.setAngle1((wheelAngles[2]-angle4)+(wheelSpeeds[2]+BR1));
    Robot.BRmod.setAngle2((wheelAngles[2]-angle4)+(-BR2-wheelSpeeds[2]));
    
    //Robot.LFmod.setAngle1(6);
    //Robot.LFmod.setAngle2(6);


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
