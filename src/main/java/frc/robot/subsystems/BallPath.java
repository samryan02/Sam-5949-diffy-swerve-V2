/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class BallPath extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public VictorSP intakeMotor;
  public VictorSP hopperMotor;
  public VictorSP feederMotor;

  public BallPath(){
    intakeMotor = new VictorSP(0);
    hopperMotor = new VictorSP(1);
    feederMotor = new VictorSP(2);
  }
  public void intake(){
      intakeMotor.set(0.6);
  }
  public void dispense(){
      intakeMotor.set(.6);
      hopperMotor.set(.6);
      feederMotor.set(.6);
  }
  public void stopAll(){
    intakeMotor.set(0);
    hopperMotor.set(0);
    feederMotor.set(0);
  }
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
