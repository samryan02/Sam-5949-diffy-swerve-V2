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
public class LBmodule extends Subsystem {
  private CANSparkMax BackLeftNeo1;
  private CANSparkMax BackLeftNeo2;
  private CANPIDController pidControllerBL1;
  private CANPIDController pidControllerBL2;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;

  public LBmodule() {
    BackLeftNeo1 = new CANSparkMax(6, MotorType.kBrushless);
    BackLeftNeo2 = new CANSparkMax(5, MotorType.kBrushless);
    pidControllerBL1 = BackLeftNeo1.getPIDController();
    pidControllerBL2 = BackLeftNeo2.getPIDController();
    BackLeftNeo1.getEncoder();
    BackLeftNeo2.getEncoder();
    kP = 9e-2;
    kI = 5e-4;
    kD = 6e-7;
    kIz = .7;
    kFF = 0.000015;
    kMaxOutput = .5;
    kMinOutput = -.5;
    maxRPM = 500;

    pidControllerBL1.setP(kP);
    pidControllerBL1.setI(kI);
    pidControllerBL1.setD(kD);
    pidControllerBL1.setIZone(kIz);
    pidControllerBL1.setFF(kFF);
    pidControllerBL1.setOutputRange(kMinOutput, kMaxOutput);

    pidControllerBL2.setP(kP);
    pidControllerBL2.setI(kI);
    pidControllerBL2.setD(kD);
    pidControllerBL2.setIZone(kIz);
    pidControllerBL2.setFF(kFF);
    pidControllerBL2.setOutputRange(kMinOutput, kMaxOutput);
    // set PID coefficients

  }

  public void setAngle1(double setPoint) {

    pidControllerBL1.setReference(setPoint, ControlType.kPosition);

  }

  public void setAngle2(double setPoint) {

    pidControllerBL2.setReference(setPoint, ControlType.kPosition);
  }

  public void setSpeed1(double setPoint) {

    pidControllerBL1.setReference(setPoint, ControlType.kVelocity);

  }

  public void setSpeed2(double setPoint) {

    pidControllerBL2.setReference(setPoint, ControlType.kVelocity);
  }

  public double getAngle() {
    double first = BackLeftNeo1.getEncoder().getPosition();
    double second = BackLeftNeo2.getEncoder().getPosition();
    return (first + second / 2) / 60;
  }

  public double getAngle1() {
    double first = BackLeftNeo1.getEncoder().getPosition();
    return first/60;
  }

  public double getAngle2() {
    double second = BackLeftNeo2.getEncoder().getPosition();
    return second/60;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new Driving());
  }
}
