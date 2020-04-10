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
    double[] swerveControls;
    double[] wheelSpeeds;
    double[] wheelAngles;
    double throttleRaw;
    double strafeRaw;
    double rotateRaw;
    double temp;
    double A;
    double B;
    double C;
    double D;
    double LFS;
    double LBS;
    double RFS;
    double RBS;
    double max1;
    double max2;
    double max;
    double LFA;
    double RFA;
    double RBA;
    double LBA;
    double GyroRadians;
    double GyroAngle;
    public double[] wheelSpeeds(){
        swerveControls = Robot.controls.getSwerveControls();

        wheelSpeeds = new double[4];

        throttleRaw = swerveControls[0];
        strafeRaw = swerveControls[1];
        rotateRaw = swerveControls[2];
        GyroAngle = Robot.imu.getAngle();
        GyroRadians = (GyroAngle*Math.PI)/180;
        double temp = throttleRaw*Math.cos(GyroRadians) + strafeRaw*Math.sin(GyroRadians);
        strafeRaw = -throttleRaw*Math.sin(GyroRadians) + strafeRaw*Math.cos(GyroRadians);
        throttleRaw = temp;
        
        A = strafeRaw-rotateRaw*(Constants.Length/Constants.R);
        B = strafeRaw+rotateRaw*(Constants.Length/Constants.R);
        C = throttleRaw-rotateRaw*(Constants.Width/Constants.R);
        D = throttleRaw+rotateRaw*(Constants.Width/Constants.R);

        LFS = Math.sqrt((B*B)+(C*C));
        LBS = Math.sqrt((B*B)+(D*D));
        RFS = Math.sqrt((A*A)+(D*D));
        RBS = Math.sqrt((A*A)+(C*C));

        max1 = Math.max(LFS, LBS);
        max2 = Math.max(RFS, RBS);
        max = Math.max(max1, max2);

        if(max > 1){
            LFS = LFS/max;
            LBS = LBS/max;
            RFS = RFS/max;
            RBS = RBS/max;
        }
        wheelSpeeds[0] = LFS;
        wheelSpeeds[1] = LBS;
        wheelSpeeds[2] = RFS;
        wheelSpeeds[3] = RBS;
        
        //just some debuging stuff
        System.out.println(wheelSpeeds[0]);
        System.out.println(wheelSpeeds[0]);
        System.out.println(wheelSpeeds[2]);
        System.out.println(wheelSpeeds[3]);

        return wheelSpeeds;
    }
    // math for getting angles
    public double[] wheelAngles(){
        swerveControls = Robot.controls.getSwerveControls();

        wheelAngles = new double[4];

        throttleRaw = swerveControls[0];
        strafeRaw = swerveControls[1];
        rotateRaw = swerveControls[2];

        temp = throttleRaw*Math.cos(0) + strafeRaw*Math.sin(0);
        strafeRaw = -throttleRaw*Math.sin(0) + strafeRaw*Math.cos(0);
        throttleRaw = temp;
        
        A = strafeRaw-rotateRaw*(Constants.Length/Constants.R);
        B = strafeRaw+rotateRaw*(Constants.Length/Constants.R);
        C = throttleRaw-rotateRaw*(Constants.Width/Constants.R);
        D = throttleRaw+rotateRaw*(Constants.Width/Constants.R);

        

        RBA = (Math.atan2(B,C))*180/Math.PI;
        RFA = (Math.atan2(B,D))*180/Math.PI;
        LFA = (Math.atan2(A,D))*180/Math.PI;
        LBA = (Math.atan2(A,C))*180/Math.PI;

        wheelAngles[0] = RBA/60;
        wheelAngles[1] = RFA/60;
        wheelAngles[2] = LFA/60;
        wheelAngles[3] = LBA/60;
        
        return wheelAngles;

        
    }
}
