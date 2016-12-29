//This is the main autonomous program with encoders for FTC Velocity Vortex 2016
//Authors: FTC team [11212??], The Lexington Legoheads
//In case of questions email anupendra@gmail.com (coach)
//**********************************************************************************************************
//Run from the necessary package
package org.usfirst.ftc.exampleteam.yourcodehere;

//Import necessary items
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.ColorSensor;

import static org.usfirst.ftc.exampleteam.yourcodehere.DriveFunctions.*;

@Autonomous(name="Floor Test") //Name the class
public class tapeColorTest extends LinearOpMode //CLASS START
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
    String color = "Blue";

    //MAIN BELOW
    @Override
    public void runOpMode() throws InterruptedException {
        boolean fastMode = false;

        //Set up drive powers to avoid magic numbers
        double drivePower = fastMode ? 1.0 : 0.5;
        double shiftPower = fastMode ? 1.0 : 0.4;
        double turnPower = fastMode ? 1.0 : 0.4;

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

        //Put side color sensors in passive mode
        colorSensorLeft.enableLed(false);
        colorSensorRight.enableLed(false);

        //Bottom color sensor in active mode
        colorSensorBottom.enableLed(true);

        //Wait for start button to be clicked
        waitForStart();
        //Open loops
        while (opModeIsActive()) {





            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
    }
}