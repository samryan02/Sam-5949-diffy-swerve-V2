/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class Calculations {
    
    public double[] wheelSpeeds(){
        double[] swerveControls = Robot.controls.getSwerveControls();

        double[] wheelSpeeds = new double[4];

        double throttleRaw = swerveControls[0];
        double strafeRaw = swerveControls[1];
        double rotateRaw = swerveControls[2];

        double temp = throttleRaw*Math.cos(0) + strafeRaw*Math.sin(0);
        strafeRaw = -throttleRaw*Math.sin(0) + strafeRaw*Math.cos(0);
        throttleRaw = temp;
        
        double A = strafeRaw-rotateRaw*(Constants.Length/Constants.R);
        double B = strafeRaw+rotateRaw*(Constants.Length/Constants.R);
        double C = throttleRaw-rotateRaw*(Constants.Width/Constants.R);
        double D = throttleRaw+rotateRaw*(Constants.Width/Constants.R);

        double LFS = Math.sqrt((B*B)+(C*C));
        double LBS = Math.sqrt((B*B)+(D*D));
        double RFS = Math.sqrt((A*A)+(D*D));
        double RBS = Math.sqrt((A*A)+(C*C));

        double max1 = Math.max(LFS, LBS);
        double max2 = Math.max(RFS, RBS);
        double max = Math.max(max1, max2);

        if(max > 1){
            LFS = LFS/max;
            LBS = LBS/max;
            RFS = RFS/max;
            RBS = RBS/max;
        }
        wheelSpeeds[0] = LFS;
        wheelSpeeds[1] = LBS;
        wheelSpeeds[2] = -RFS;
        wheelSpeeds[3] = RBS;

        return wheelSpeeds;
    }
    public double[] wheelAngles(){
        double[] swerveControls = Robot.controls.getSwerveControls();

        double[] wheelAngles = new double[4];

        double throttleRaw = swerveControls[0];
        double strafeRaw = swerveControls[1];
        double rotateRaw = swerveControls[2];

        double temp = throttleRaw*Math.cos(0) + strafeRaw*Math.sin(0);
        strafeRaw = -throttleRaw*Math.sin(0) + strafeRaw*Math.cos(0);
        throttleRaw = temp;
        
        double A = strafeRaw-rotateRaw*(Constants.Length/Constants.R);
        double B = strafeRaw+rotateRaw*(Constants.Length/Constants.R);
        double C = throttleRaw-rotateRaw*(Constants.Width/Constants.R);
        double D = throttleRaw+rotateRaw*(Constants.Width/Constants.R);

        double LFA;
        double LBA;
        double RFA;
        double RBA;

        LFA = (Math.atan2(B,C))*180/Math.PI;
        LBA = (Math.atan2(B,D))*180/Math.PI;
        RFA = (Math.atan2(A,D))*180/Math.PI;
        RBA = (Math.atan2(A,C))*180/Math.PI;

        wheelAngles[0] = LFA/60;
        wheelAngles[1] = LBA/60;
        wheelAngles[2] = RFA/60;
        wheelAngles[3] = RBA/60;

        return wheelAngles;

        
    }
}
