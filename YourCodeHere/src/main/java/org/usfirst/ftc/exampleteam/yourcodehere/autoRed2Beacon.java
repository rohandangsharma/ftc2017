//This is the main autonomous program with encoders for FTC Velocity Vortex 2016
//Authors: FTC team [11212??], The Lexington Legoheads
//In case of questions email anupendra@gmail.com (coach)
//**********************************************************************************************************
//Run from the necessary package
package org.usfirst.ftc.exampleteam.yourcodehere;

//Import necessary items

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;

//***************************************************************************************************************************
@Disabled //We don't want this class to show up in the list, it is just here for reference
@Autonomous(name="2 Beacon Red Team") //Name the program
public class autoRed2Beacon extends LinearOpMode //CLASS START
{
    //Define DC Motors
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;
    DcMotor shooterLeft;
    DcMotor shooterRight;
    DcMotor spinner;

    //Define Sensors and the CDI
    ColorSensor colorSensorLeft;
    ColorSensor colorSensorRight;
    ColorSensor colorSensorBottom;
    DeviceInterfaceModule CDI;

    //Define a string to use as the color, and set it to red, since we are red team
    String color = "Red";

    //Set up drive powers to avoid magic numbers
    float drivePower = (float) 0.5;
    float shiftPower = (float) 0.4;
    float turnPower = (float) 0.4;

//***************************************************************************************************************************
    //MAIN BELOW
    @Override
    public void runOpMode() throws InterruptedException
    {
        //Get references to the DC motors from the hardware map
        leftMotorFront = hardwareMap.dcMotor.get("leftMotorFront");
        rightMotorFront = hardwareMap.dcMotor.get("rightMotorFront");
        leftMotorBack = hardwareMap.dcMotor.get("leftMotorBack");
        rightMotorBack = hardwareMap.dcMotor.get("rightMotorBack");
        spinner = hardwareMap.dcMotor.get("spinner");
        shooterLeft = hardwareMap.dcMotor.get("shooterLeft");
        shooterRight = hardwareMap.dcMotor.get("shooterRight");

        //Get references to the sensors and the CDI from the hardware map
        colorSensorBottom = hardwareMap.colorSensor.get("colorSensorBottom");
        colorSensorLeft = hardwareMap.colorSensor.get("colorSensorLeft");
        colorSensorRight = hardwareMap.colorSensor.get("colorSensorRight");
        CDI = hardwareMap.deviceInterfaceModule.get("CDI");

        //Set up the DriveFunctions class and give it all the necessary components (motors, sensors, CDI)
        DriveFunctions functions = new DriveFunctions(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack, spinner, shooterLeft, shooterRight, colorSensorLeft, colorSensorRight, colorSensorBottom, CDI);

        //Set the sensors to the modes that we want, and set their addresses. Also set the directions of the motors
        functions.initializeMotorsAndSensors();

        //Wait for start button to be clicked
        waitForStart();

//***************************************************************************************************************************

        //Drive toward the center vortex
        functions.driveAutonomous(drivePower, 2700);

        //Shift towards beacon
        functions.leftShiftAutonomous(shiftPower, 3000);

        //Become aligned with beacon
        functions.driveAutonomous(drivePower,2000);

        //Shift next to beacon
        functions.leftShiftAutonomous(shiftPower, 3000);

        //If we see the color (in this case, "red") shift and hit the beacon
        functions.beaconColorCheckAutonomous(color, colorSensorLeft);

        //Come off of wall
        functions.rightShiftAutonomous(shiftPower, 300);

        //Drive to second beacon
        functions.driveAutonomous(drivePower, 4400);

        //If we see the color (in this case, "red") shift and hit the beacon
        functions.beaconColorCheckAutonomous(color, colorSensorLeft);

        //Come off wall
        functions.rightShiftAutonomous(shiftPower, 300);

        //Turn to Center Vortex
        functions.rightTurnAutonomous(turnPower, 3800);

        //Drive to Center Vortex
        functions.driveAutonomous(drivePower, 5400);

        //Turn to ramp
        functions.rightTurnAutonomous(turnPower, 2000);

        //Drive up ramp
        functions.driveAutonomous(drivePower, 6000);

        //Shoot balls that are in the robot
        spinner.setPower(1.0);

    } //Close "run Opmode" loop
} //Close class and end program