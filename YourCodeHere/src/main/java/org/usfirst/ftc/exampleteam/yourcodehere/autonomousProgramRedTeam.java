//This is the main autonomous program with encoders for FTC Velocity Vortex 2016
//Authors: FTC team [11212??], The Lexington Legoheads
//In case of questions email anupendra@gmail.com (coach)
//**********************************************************************************************************
//Run from the necessary package
package org.usfirst.ftc.exampleteam.yourcodehere;

//Import necessary items

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;

import org.swerverobotics.library.SynchronousOpMode;

import static org.usfirst.ftc.exampleteam.yourcodehere.functions.colorSensorAutonomous;
import static org.usfirst.ftc.exampleteam.yourcodehere.functions.driveAutonomous;
import static org.usfirst.ftc.exampleteam.yourcodehere.functions.leftShiftAutonomous;
import static org.usfirst.ftc.exampleteam.yourcodehere.functions.leftTurnAutonomous;
import static org.usfirst.ftc.exampleteam.yourcodehere.functions.rightShiftAutonomous;
import static org.usfirst.ftc.exampleteam.yourcodehere.functions.rightTurnAutonomous;

@Autonomous(name="Red Team Autonomous") //Name the class
public class autonomousProgramRedTeam extends SynchronousOpMode //CLASS START
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

    //Define a string to use as the color, and set it to blue
    String color = "red";

    boolean fastMode = false;

    //Set up drive powers to avoid magic numbers
    double drivePower = fastMode ? 1.0 : 0.5;
    double shiftPower = fastMode ? 1.0 : 0.4;
    double turnPower = fastMode ? 1.0 : 0.4;

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

        //Put color sensor in passive mode
        colorSensor.enableLed(false);

        //Wait for start button to be clicked
        waitForStart();
        //Open loops
        while (opModeIsActive()) {

            //Drive forward to center Vortex
            driveAutonomous((float) -drivePower, (fastMode ? 2300 : -2700), leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Shift towards beacon
            rightShiftAutonomous((float) shiftPower, 3000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Become aligned with beacon
            driveAutonomous((float) -drivePower, fastMode ? 1600 : -2000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Shift next to beacon
            rightShiftAutonomous((float) shiftPower, 3000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //If "blue" seen, shift to right, hit button
            colorSensorAutonomous(color, colorSensor, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Come off of wall
            leftShiftAutonomous((float) shiftPower, 300, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Align with second beacon
            driveAutonomous((float) -drivePower, fastMode ? 4000 : -4400, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //If "blue seen, shift to right, hit button
            colorSensorAutonomous(color, colorSensor, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Come off wall
            leftShiftAutonomous((float) shiftPower, 2000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Turn to Center Vortex
            leftTurnAutonomous((float) turnPower, 1267, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Drive to Center Vortex
            driveAutonomous((float) drivePower, fastMode ? 5000 : 5400, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Turn to ramp
            rightTurnAutonomous((float) turnPower, 2000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Drive up ramp
            driveAutonomous((float) drivePower, fastMode ? 5600 : 6000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            spinner.setPower(1.0);
        }
    }
}