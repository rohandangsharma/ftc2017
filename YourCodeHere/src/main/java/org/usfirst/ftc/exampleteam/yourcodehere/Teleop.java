//Run from the necessary package
package org.usfirst.ftc.exampleteam.yourcodehere;

//Import necessary items
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import org.swerverobotics.library.SynchronousOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import static org.usfirst.ftc.exampleteam.yourcodehere.DriveFunctions.*;


@TeleOp (name="Legoheads TeleOp ") //Name the class
public class Teleop extends SynchronousOpMode //CLASS START
{
    //Define DC Motors
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;
    DcMotor spinner;
    DcMotor shooterLeft;
    DcMotor shooterRight;

    //Define an int to use as the spinner's mode
    int spinnerMode = 0;
//    int startBeacon = 1;
//    int endBeacon = 1;

    //Define Sensors and the CDI
    ColorSensor colorSensorLeft;
    ColorSensor colorSensorRight;
    ColorSensor colorSensorBottom;
    DeviceInterfaceModule CDI;

    //Define floats to be used as joystick and trigger inputs, as well as a float for maximum power to avoid magic numbers
    float drive;
    float shift;
    float rightTurn;
    float leftTurn;
    float maxPower = (float) 1.0;
    float drivePower = (float) 0.5;
    float shiftPower = (float) 0.4;

    //Define a string to use as the opposite color, and set it to red since we are blue team
    String oppositeColor = "Red";

//***********************************************************************************************************
    //MAIN BELOW
    @Override
    public void main() throws InterruptedException
    {
        leftMotorFront = hardwareMap.dcMotor.get("leftMotorFront");
        rightMotorFront = hardwareMap.dcMotor.get("rightMotorFront");
        leftMotorBack = hardwareMap.dcMotor.get("leftMotorBack");
        rightMotorBack = hardwareMap.dcMotor.get("rightMotorBack");

        spinner = hardwareMap.dcMotor.get("spinner");
        shooterLeft = hardwareMap.dcMotor.get("shooterLeft");
        shooterRight = hardwareMap.dcMotor.get("shooterRight");

        //Get references to the sensors from the hardware map
        colorSensorBottom = hardwareMap.colorSensor.get("colorSensorBottom");
        colorSensorLeft = hardwareMap.colorSensor.get("colorSensorLeft");
        colorSensorRight = hardwareMap.colorSensor.get("colorSensorRight");
        CDI = hardwareMap.deviceInterfaceModule.get("CDI");

        DriveFunctions functions = new DriveFunctions(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack, spinner, shooterLeft, shooterRight, colorSensorLeft, colorSensorRight, colorSensorBottom, CDI);

        //Set the sensors to the modes that we want, and set their addresses. Also set the directions of the motors
        functions.initializeMotorsAndSensors();

        //Wait for start button to be clicked
        waitForStart();

//***********************************************************************************************************
//LOOP BELOW
        //While the op mode is active, do anything within the loop
        //Note we use opModeIsActive() as our loop condition because it is an interruptible method.
        while (opModeIsActive()) {
            //If the gamepads are changed
            if (updateGamepads()) {
                //Set float variables as the inputs from the joysticks and the triggers
                drive = gamepad1.left_stick_y;
                shift = - gamepad1.left_stick_x;
                leftTurn = gamepad1.left_trigger;
                rightTurn = gamepad1.right_trigger;

                //Drive vs Shift on left joystick:
                //Do nothing if joystick is stationary
                if (Math.abs(drive) == Math.abs(shift)) {
                    functions.stopDriving();
                }

                //Shift if pushed more on X than Y
                if (Math.abs(shift) > Math.abs(drive)) {
                    functions.shiftTeleop(shift);
                }

                //Drive if joystick pushed more Y than X
                if (Math.abs(drive) > Math.abs(shift)) {
                    functions.driveTeleop(drive);
                }

                //If the left trigger is pushed, turn left at that power
                if (leftTurn > 0) {
                    functions.leftTurnTeleop(leftTurn);
                }

                //If the right trigger is pushed, turn right at that power
                if (rightTurn > 0) {
                    functions.rightTurnTeleop(rightTurn);
                }


                //If the "a" button on the second gamepad is pressed, start the spinner forward
                if (gamepad2.a) {
                    spinnerMode = 2;
                    functions.toggleSpinner(spinnerMode, maxPower);
                }
                ////If the "x" button on the second gamepad is pressed, start the spinner backwards
                if (gamepad2.x) {
                    spinnerMode = 1;
                    functions.toggleSpinner(spinnerMode, maxPower);
                }

                //If the "y" button is pressed, shoot the ball
                if (gamepad2.y) {
                    functions.shootBall(maxPower);
                }


                //Stop all motors when any bumper is pressed
                if ((gamepad1.right_bumper) || (gamepad2.right_bumper) || (gamepad1.left_bumper) || (gamepad2.left_bumper)) {
                    spinnerMode = 0;
                    functions.toggleSpinner(spinnerMode, maxPower);
                    functions.stopEverything();
                }
                if (gamepad1.dpad_left)
                {
                    if (gamepad1.y)
                    {
                        functions.sameSideBeacons("Forward", oppositeColor);
                    }
                    if (gamepad1.b)
                    {
                        //1-3
                    }
                    if (gamepad1.a)
                    {
                        //1-4
                    }
                } //Close "if" statement of when we are on beacon 1

                if (gamepad1.dpad_up)
                {
                    if (gamepad1.x)
                    {
                        functions.sameSideBeacons("Backward", oppositeColor);
                    }
                    if (gamepad1.b)
                    {
                        //2-3
                    }
                    if (gamepad1.a)
                    {
                        //2-4
                    }
                } //Close "if" statement of when we are on beacon 2

                if (gamepad1.dpad_right)
                {
                    if (gamepad1.x)
                    {
                        //3-1
                    }
                    if (gamepad1.y)
                    {
                        //3-2
                    }
                    if (gamepad1.a)
                    {
                        functions.sameSideBeacons("Forward", oppositeColor);
                    }
                } //Close "if" statement of when we are on beacon 3

                if (gamepad1.dpad_down)
                {
                    if (gamepad1.x)
                    {
                        //4-1
                    }
                    if (gamepad1.y)
                    {
                        //4-2
                    }
                    if (gamepad1.b)
                    {
                        functions.sameSideBeacons("Backward", oppositeColor);
                    }
                } //Close "if" statement of when we are on beacon 4
            } //Close inside "if" loop
            //Update data
            telemetry.update();

            // Always call idle() at the bottom of your while(opModeIsActive()) loop
            idle();

        } //Close "while (opModeIsActive())" loop
    } //Close main
} //Close class and end program


































