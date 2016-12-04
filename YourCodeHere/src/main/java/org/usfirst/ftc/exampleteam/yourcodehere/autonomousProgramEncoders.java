
//This is the main autonomous program with encoders for FTC Velocity Vortex 2016
//Authors: FTC team [11212??], The Lexington Legoheads
//In case of questions email anupendra@gmail.com (coach)
//**********************************************************************************************************
//Run from the necessary package
package org.usfirst.ftc.exampleteam.yourcodehere;

//Import necessary items

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.swerverobotics.library.SynchronousOpMode;



@Autonomous(name="Autonumous Program Encoders") //Name the class
public class autonomousProgramEncoders extends SynchronousOpMode //CLASS START
{
    //Define DC Motors
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;
    DcMotor flipper;
    DcMotor spinner;

    //Define Sensors
    ColorSensor colorSensor;
    DeviceInterfaceModule CDI;

    //MAIN BELOW
    @Override
    public void main() throws InterruptedException {
        //Get references to the motors from the hardware map
        leftMotorFront = hardwareMap.dcMotor.get("leftMotorFront");
        rightMotorFront = hardwareMap.dcMotor.get("rightMotorFront");
        leftMotorBack = hardwareMap.dcMotor.get("leftMotorBack");
        rightMotorBack = hardwareMap.dcMotor.get("rightMotorBack");
        spinner = hardwareMap.dcMotor.get("spinner");
        flipper = hardwareMap.dcMotor.get("flipper");

        //Get references to the sensors from the hardware map
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        CDI = hardwareMap.deviceInterfaceModule.get("CDI");

        //Reverse the right motors since it is facing the opposite direction as the left motor
        rightMotorFront.setDirection(DcMotor.Direction.REVERSE);
        rightMotorBack.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
//            while (functions.colorGreater(colorSensor, "blue") == true) {
//                functions.driveTeleop((float) 1.0, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//            }
//            while (functions.colorGreater(colorSensor, "red") == true) {
//                functions.driveTeleop((float) -1.0, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//            }

            if(functions.iSeeAColor(colorSensor) == true){
                while(functions.whatColor(colorSensor).equals("blue")){

                }
            }




//        functions.driveForwardAutonomous((float) 1.0, 11000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//        functions.rightTurnAutonomous((float) 0.4, 4200, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//        functions.driveForwardAutonomous((float) 1.0, 9000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//        functions.leftTurnAutonomous((float) 0.4, 4000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//        functions.driveForwardAutonomous((float) 1.0, 7000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//        functions.leftTurnAutonomous((float) 0.4, 8000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//        functions.driveForwardAutonomous((float) 1.0, 11000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//        functions.leftTurnAutonomous((float) 0.4, 1000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//        functions.driveForwardAutonomous((float) 1.0, 5000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

        }


    }
}