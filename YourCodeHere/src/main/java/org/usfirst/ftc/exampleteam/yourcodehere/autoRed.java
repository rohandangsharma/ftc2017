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


@Autonomous(name="Red Autonomous") //Name the class
public class autoRed extends LinearOpMode //CLASS START
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
    ColorSensor colorSensorLeft;
    ColorSensor colorSensorRight;
    ColorSensor colorSensorBottom;
    DeviceInterfaceModule CDI;

    //Define a string to use as the color, and set it to blue
    String color = "red";

    boolean fastMode = false;

    //Set up drive powers to avoid magic numbers
    double drivePower = fastMode ? 1.0 : 0.5;
    double shiftPower = fastMode ? 1.0 : 0.4;
    double turnPower = fastMode ? 1.0 : 0.4;

    //MAIN BELOW
    @Override
    public void runOpMode() throws InterruptedException {

        //Get references to the motors from the hardware map
        leftMotorFront = hardwareMap.dcMotor.get("leftMotorFront");
        rightMotorFront = hardwareMap.dcMotor.get("rightMotorFront");
        leftMotorBack = hardwareMap.dcMotor.get("leftMotorBack");
        rightMotorBack = hardwareMap.dcMotor.get("rightMotorBack");

        spinner = hardwareMap.dcMotor.get("spinner");
        shooterLeft = hardwareMap.dcMotor.get("shooterLeft");
        shooterRight = hardwareMap.dcMotor.get("shooterRight");

        //Get references to the sensors from the hardware map
        colorSensorLeft = hardwareMap.colorSensor.get("colorSensorLeft");
        colorSensorRight = hardwareMap.colorSensor.get("colorSensorRight");
        colorSensorBottom = hardwareMap.colorSensor.get("colorSensorBottom");

        CDI = hardwareMap.deviceInterfaceModule.get("CDI");

        //Constructor
        DriveFunctions functions = new DriveFunctions(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack, colorSensorLeft, colorSensorRight, colorSensorBottom, spinner, shooterLeft, shooterRight);

        //Reverse the right motors since it is facing the opposite direction as the left motor
        leftMotorFront.setDirection(DcMotor.Direction.REVERSE);
        rightMotorBack.setDirection(DcMotor.Direction.REVERSE);

        //Put color sensor in passive mode
        colorSensorLeft.enableLed(false);
        colorSensorRight.enableLed(false);
        colorSensorBottom.enableLed(true);

        //Wait for start button to be clicked
        waitForStart();
        //Open loops
        while (opModeIsActive()) {

            //Drive forward to center Vortex
            functions.driveAutonomous((float) -drivePower, (fastMode ? 2300 : -2700));

            //Shift towards beacon
            functions.rightShiftAutonomous((float) shiftPower, 3000);

            //Become aligned with beacon
            functions.driveAutonomous((float) -drivePower, fastMode ? 1600 : -2000);

            //Shift next to beacon
            functions.rightShiftAutonomous((float) shiftPower, 3000);

            //If "red" seen, shift to right, hit button
            functions.beaconColorCheck(color, colorSensorLeft);

            //Come off of wall
            functions.leftShiftAutonomous((float) shiftPower, 300);

            //Align with second beacon
            functions.driveAutonomous((float) -drivePower, fastMode ? 4000 : -4400);

            //If "red seen, shift to right, hit button
            functions.beaconColorCheck(color, colorSensorLeft);

            //Come off wall
            functions.leftShiftAutonomous((float) shiftPower, 300);

            //Turn to Center Vortex
            functions.leftTurnAutonomous((float) turnPower, 1267);

            //Drive to Center Vortex
            functions.driveAutonomous((float) drivePower, fastMode ? 5000 : 5400);

            //Turn to ramp
            functions.rightTurnAutonomous((float) turnPower, 2000);

            //Drive up ramp
            functions.driveAutonomous((float) drivePower, fastMode ? 5600 : 6000);

            spinner.setPower(1.0);

            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
    }
}