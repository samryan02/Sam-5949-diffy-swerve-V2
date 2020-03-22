/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controls {
    // Axis
    private static final int throttleAxis = 1;
    private static final int strafeAxis = 0;
    private static final int rotateAxis = 4;
	
    private Joystick joystick = new Joystick(0);

    //creates Joystick Object
    public Controls() {
        //super(ControlsPort);
    }

    
    public double getThrottle() {
        double throttle = joystick.getRawAxis(throttleAxis);
        if(Math.abs(throttle)<.1){
            throttle = 0;
        }
        return throttle;
    }
    public double getStrafe() {
        double strafe = joystick.getRawAxis(strafeAxis);
        if(Math.abs(strafe)<.1){
            strafe = 0;
        }
        return strafe;
    }

    public double getRotate() {
        double rotate = joystick.getRawAxis(rotateAxis);
        if(Math.abs(rotate)<.1){
            rotate = 0;
        }
        return rotate;
    }
    public double[] getSwerveControls(){
        double[] swerveControls = new double[3];
        swerveControls[0] = getThrottle();
        swerveControls[1] = getStrafe();
        swerveControls[2] = getRotate();
        return swerveControls;


    }

}
