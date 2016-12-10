
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

import static org.usfirst.ftc.exampleteam.yourcodehere.functions.*;


@Autonomous(name="Blue Team Autonomous") //Name the class
public class autonomousProgramBlueTeam extends SynchronousOpMode //CLASS START
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
    public void main() throws InterruptedException
    {
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
        while (opModeIsActive())
        {
            //Drive forward to center Vortex
            driveAutonomous((float) 0.5, 3000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Shift towards beacon
            rightShiftAutonomous((float) 0.4, 3000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Become aligned with beacon
            driveAutonomous((float) 0.5, 2000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Shift next to beacon
            rightShiftAutonomous((float) 0.2, 3000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //If "blue" seen, shift to right, hit button
            colorSensorAutonomous("blue", colorSensor, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Come off of wall
            leftShiftAutonomous((float) 0.5, 300, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Align with second beacon
            driveAutonomous((float) 0.5, 5500, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //If "blue seen, shift to right, hit button
            colorSensorAutonomous("blue", colorSensor, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Come off wall
            leftShiftAutonomous((float) 0.4, 2000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Turn to Center Vortex
            leftTurnAutonomous((float) 0.4, 4000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Drive to Center Vortex
            driveAutonomous((float) 0.5, 6000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Turn to ramp
            leftTurnAutonomous((float) 0.4, 1000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

            //Drive up ramp
            driveAutonomous((float) 0.5, 6000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

        }
    }
}