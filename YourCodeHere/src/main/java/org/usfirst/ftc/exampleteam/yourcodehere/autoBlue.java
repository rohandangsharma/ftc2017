//Run from the package
package org.usfirst.ftc.exampleteam.yourcodehere;

//Import necessary items
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.ColorSensor;

//***************************************************************************************************************************
//@Disabled //We don't want this class to show up in the list, it is just here for reference
@Autonomous(name="Auto Blue") //Name the program
public class autoBlue extends LinearOpMode //CLASS START
{
    //Define DC Motors
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;
    DcMotor shooterLeft;
    DcMotor shooterRight;
    DcMotor spinnerTop;
    DcMotor spinnerBottom;

    //Define Sensors and the CDI
    ColorSensor colorSensorLeft;
    ColorSensor colorSensorRight;
    ColorSensor colorSensorBottom;
    DeviceInterfaceModule CDI;

    //Define a string to use as the color, and set it to blue, since we are blue team
    String color = "Blue";

    //Set up drive powers to avoid magic numbers
    float drivePower = (float) 0.5;
    float shiftPower = (float) 0.4;
    float turnPower = (float) 0.4;

    //***************************************************************************************************************************
    //MAIN BELOW
    @Override
    public void runOpMode() throws InterruptedException {
        //Get references to the DC motors from the hardware map
        leftMotorFront = hardwareMap.dcMotor.get("leftMotorFront");
        rightMotorFront = hardwareMap.dcMotor.get("rightMotorFront");
        leftMotorBack = hardwareMap.dcMotor.get("leftMotorBack");
        rightMotorBack = hardwareMap.dcMotor.get("rightMotorBack");
        spinnerTop = hardwareMap.dcMotor.get("spinnerLeft");
        spinnerBottom = hardwareMap.dcMotor.get("spinnerRight");
        shooterLeft = hardwareMap.dcMotor.get("shooterTop");
        shooterRight = hardwareMap.dcMotor.get("shooterBottom");

        //Get references to the sensors and the CDI from the hardware map
        colorSensorBottom = hardwareMap.colorSensor.get("colorSensorBottom");
        colorSensorLeft = hardwareMap.colorSensor.get("colorSensorLeft");
        colorSensorRight = hardwareMap.colorSensor.get("colorSensorRight");
        CDI = hardwareMap.deviceInterfaceModule.get("CDI");

        //Set up the DriveFunctions class and give it all the necessary components (motors, sensors, CDI)
        DriveFunctions functions = new DriveFunctions(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack, spinnerTop, spinnerBottom, shooterLeft, shooterRight, colorSensorLeft, colorSensorRight, colorSensorBottom, CDI);

        //Set the sensors to the modes that we want, and set their addresses. Also set the directions of the motors
        functions.initializeMotorsAndSensors();

        //Wait for start button to be clicked
        waitForStart();

        while (opModeIsActive()) {

            //Drive toward the center vortex
            functions.driveAutonomous(-drivePower, -3000);

            //Shift towards beacon
            functions.leftShiftAutonomous(shiftPower, 3000);

            //Become aligned with beacon
            functions.driveAutonomous(-drivePower, -1500);

            //Shift next to beacon
            functions.leftShiftAutonomous(shiftPower, 3000);

            functions.whiteLineStop(-drivePower / 2);

            //If we see the color (in this case, "blue") shift and hit the beacon
            functions.beaconColorCheck(color, colorSensorLeft);

            //Drive to second beacon
            functions.driveAutonomous(-drivePower, -4000);

            functions.whiteLineStop(-drivePower / 2);

            //If we see the color (in this case, "blue") shift and hit the beacon
            functions.beaconColorCheck(color, colorSensorLeft);

            //Turn to Center Vortex
            functions.rightTurnAutonomous(turnPower, 1267);

            //Drive to Center Vortex
            functions.driveAutonomous(drivePower, 6500);
            idle();
            break;
        } //Close "run Opmode" loop
    } //Close class and end program
}