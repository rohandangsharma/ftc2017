//Run from the necessary package
package org.usfirst.ftc.exampleteam.yourcodehere;

//Import necessary items
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;

//***************************************************************************************************************************
@Autonomous(name="3 Beacon Blue Team") //Name the program
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

    //Define Sensors and the CDI
    ColorSensor colorSensorLeft;
    ColorSensor colorSensorRight;
    ColorSensor colorSensorBottom;
    DeviceInterfaceModule CDI;

    //Set up drive powers to avoid magic numbers
    float drivePower = (float) 0.5;
    float shiftPower = (float) 0.4;
    float turnPower = (float) 0.4;

    //Define a string to use as the color, and set it to blue since we are blue team
    String color = "Blue";

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
        functions.rightShiftAutonomous(shiftPower, 3000);

        //Become aligned with beacon
        functions.driveAutonomous(drivePower, 2000);

        //Shift next to beacon
        functions.rightShiftAutonomous(shiftPower, 3300);

        //If we see the color (in this case, "blue") shift and hit the beacon
        functions.beaconColorCheck(color, colorSensorRight);

        //Come off of wall
        functions.leftShiftAutonomous(shiftPower, 300);

        //Align with second beacon
        functions.driveAutonomous(drivePower, 4400);

        //If we see the color (in this case, "blue") shift and hit the beacon
        functions.beaconColorCheck(color, colorSensorRight);

        //Come off wall
        functions.leftShiftAutonomous(shiftPower, 3000);

        //Turn to third beacon
        functions.leftTurnAutonomous(turnPower, 2400);

        //Align on wall
        functions.rightShiftAutonomous(drivePower, 5000);

        //Come off wall
        functions.leftShiftAutonomous(shiftPower, 300);

        //Go to third beacon
        functions.driveAutonomous(drivePower, 1800);

        //If we see the color (in this case, "blue") shift and hit the beacon
        functions.beaconColorCheck(color, colorSensorRight);

//            //Turn to Center Vortex
//            leftTurnAutonomous(turnPower, 3800, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
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

    } //Close "run Opmode" loop
} //Close class and end program