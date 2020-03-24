/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.Driving;

/**
 * Add your docs here.
 */
public class LFmodule extends Subsystem {

  //Neo Objects
  private CANSparkMax FrontLeftNeo1;
  private CANSparkMax FrontLeftNeo2;
  //Pid controller objects
  private CANPIDController pidControllerFL1;
  private CANPIDController pidControllerFL2;
  //pid Varibles
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;

  public LFmodule() {
    //initialize neos
    FrontLeftNeo1 = new CANSparkMax(2, MotorType.kBrushless);
    FrontLeftNeo2 = new CANSparkMax(1, MotorType.kBrushless);
    //initialize motor contollers to coresponding motor controller
    pidControllerFL1 = FrontLeftNeo1.getPIDController();
    pidControllerFL2 = FrontLeftNeo2.getPIDController();
    // attach neos to encoders
    FrontLeftNeo1.getEncoder();
    FrontLeftNeo2.getEncoder();

    //define pid objects
    kP = 9e-2; 
    kI = 5e-4;
    kD = 6e-7; 
    kIz = .7; 
    kFF = 0.000015; 
    kMaxOutput = .5; 
    kMinOutput = -.5;
    maxRPM = 500;

    // attach  pid values to each contoller
    pidControllerFL1.setP(kP);
    pidControllerFL1.setI(kI);
    pidControllerFL1.setD(kD);
    pidControllerFL1.setIZone(kIz);
    pidControllerFL1.setFF(kFF);
    pidControllerFL1.setOutputRange(kMinOutput, kMaxOutput);

    pidControllerFL2.setP(kP);
    pidControllerFL2.setI(kI);
    pidControllerFL2.setD(kD);
    pidControllerFL2.setIZone(kIz);
    pidControllerFL2.setFF(kFF);
    pidControllerFL2.setOutputRange(kMinOutput, kMaxOutput);
    // set PID coefficients
    
  }
  //method for setting setpoint of motor 1
  public void setAngle1(double setPoint) {

    pidControllerFL1.setReference(setPoint, ControlType.kPosition);
    
  }
  // method for setting setpoint of motor 2
  public void setAngle2(double setPoint) {

    pidControllerFL2.setReference(setPoint, ControlType.kPosition);
  }
  
 
  //Unused method for getting angle of module (may use later)
  public double getAngle() {
    double first = FrontLeftNeo1.getEncoder().getPosition();
    double second  = FrontLeftNeo1.getEncoder().getPosition();
    return (first+second/2)/60;
  }
  public double getAngle1() {
    double first = FrontLeftNeo1.getEncoder().getPosition();
    return first/60;
  }
  public double getAngle2() {
    double second  = FrontLeftNeo2.getEncoder().getPosition();
    return second/60;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new Driving());
  }
}
