//This is the main autonomous program with encoders for FTC Velocity Vortex 2016
//Authors: FTC team [11212??], The Lexington Legoheads
//In case of questions email anupendra@gmail.com (coach)
//**********************************************************************************************************
//Run from the necessary package
package org.usfirst.ftc.exampleteam.yourcodehere;

//Import necessary items

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;

@Autonomous(name="3 Beacon Blue Team") //Name the class
public class autoBlue3Beacon extends LinearOpMode //CLASS START
{
    //Define DC Motors
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;
    DcMotor shooterLeft;
    DcMotor shooterRight;
    DcMotor spinner;

    //Define Sensors
    ColorSensor colorSensor;
    DeviceInterfaceModule CDI;

    //Define a string to use as the color, and set it to blue
    String color = "blue";

    //MAIN BELOW
    @Override
    public void runOpMode() throws InterruptedException {
        boolean fastMode = false;

        //Set up drive powers to avoid magic numbers
        float drivePower = fastMode ? (float) 1.0 : (float) 0.5;
        float shiftPower = fastMode ?(float) 1.0 : (float) 0.4;
        float turnPower = fastMode ? (float) 1.0 : (float) 0.4;

        //Get references to the motors from the hardware map
        leftMotorFront = hardwareMap.dcMotor.get("leftMotorFront");
        rightMotorFront = hardwareMap.dcMotor.get("rightMotorFront");
        leftMotorBack = hardwareMap.dcMotor.get("leftMotorBack");
        rightMotorBack = hardwareMap.dcMotor.get("rightMotorBack");
        spinner = hardwareMap.dcMotor.get("spinner");
        shooterLeft = hardwareMap.dcMotor.get("shooterLeft");
        shooterRight = hardwareMap.dcMotor.get("shooterRight");

        //Get references to the sensors from the hardware map
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        CDI = hardwareMap.deviceInterfaceModule.get("CDI");

        DriveFunctions functions = new DriveFunctions(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack, colorSensor, spinner, shooterLeft, shooterRight);

        //Reverse the right motors since it is facing the opposite direction as the left motor
        leftMotorFront.setDirection(DcMotor.Direction.REVERSE);
        rightMotorBack.setDirection(DcMotor.Direction.REVERSE);

        //Put color sensor in passive mode
        colorSensor.enableLed(false);

        //Wait for start button to be clicked
        waitForStart();
        //Open loops
        while (opModeIsActive()) {
            //Drive forward to center Vortex

//            DriveFunctions.iSeeAColorStop( -0.1, colorSensor, "Blue", leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
            functions.driveAutonomous(drivePower, 2700);

            //Shift towards beacon
            functions.rightShiftAutonomous(shiftPower, 3000);

            //Become aligned with beacon
            functions.driveAutonomous(drivePower, 2000);

            //Shift next to beacon
            functions.rightShiftAutonomous(shiftPower, 3000);

            //If "blue" seen, shift to right, hit button
            functions.beaconColorCheck(color);

            //Come off of wall
            functions.leftShiftAutonomous(shiftPower, 300);

            //Align with second beacon
            functions.driveAutonomous(drivePower, 4400);

            //If "blue seen, shift to right, hit button
            functions.beaconColorCheck(color);

            //Come off wall
            functions.leftShiftAutonomous(shiftPower, 300);

            functions.leftTurnAutonomous(turnPower, 2400);

            functions.rightShiftAutonomous(drivePower, 4000);

            functions.driveAutonomous(drivePower, 7000);

            functions.beaconColorCheck(color);

//            //Turn to Center Vortex
//            leftTurnAutonomous( turnPower, 3800, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//
//            //Drive to Center Vortex
//            driveAutonomous(drivePower, 5400, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//
//            //Turn to ramp
//            leftTurnAutonomous(turnPower, 2000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//
//            //Drive up ramp
//            driveAutonomous(drivePower, 6000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

//            spinner.setPower(1.0);

            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
    }
}