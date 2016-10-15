package org.usfirst.ftc.exampleteam.yourcodehere;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;

import org.swerverobotics.library.*;
import org.swerverobotics.library.interfaces.*;

/**
 * A skeletal example of a do-nothing first OpMode. Go ahead and change this code
 * to suit your needs, or create sibling OpModes adjacent to this one in the same
 * Java package.
 */
@TeleOp(name = "My First OpMode")
public class MyFirstOpMode extends SynchronousOpMode {
    /* Declare here any fields you might find useful. */
    // DcMotor motorLeft = null;
    // DcMotor motorRight = null;
    //Define DC Motors
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;
    DcMotor a;
    //DcMotor elevator;
    //DcMotor spinner;
    //DcMotor shooterLeft;
    //DcMotor shooterRight;

    //Define Servo Motors
    Servo doorLeft;
    Servo doorRight;
    // DcMotor leftMotorFront;

    //Define press counts
    //public int aPressCount = 0;
    public int yPressCount = 0;

    //Define servo motor door positions
    final double CLOSED_DOOR_POSITION = 0.0;
    final double OPEN_DOOR_POSITION = 1.0;

    //METHODS BELOW
    //Create a custom function to count the number of times the button "y" is pressed
    //It toggles button y, so if it has been pressed an odd number of times, the door will be open
    //Otherwise, it will be closed
    public void toggleButtonY()
    {
        yPressCount = yPressCount + 1;
        if (yPressCount % 2 == 1) {
            doorLeft.setPosition(OPEN_DOOR_POSITION);
            doorRight.setPosition(OPEN_DOOR_POSITION);
        }
        else {
            doorLeft.setPosition(CLOSED_DOOR_POSITION);
            doorRight.setPosition(CLOSED_DOOR_POSITION);
        }
    }


    @Override
    public void main() throws InterruptedException {
        /* Initialize our hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names you assigned during the robot configuration
         * step you did in the FTC Robot Controller app on the phone.
         */
        // this.motorLeft = this.hardwareMap.dcMotor.get("motorLeft");
        // this.motorRight = this.hardwareMap.dcMotor.get("motorRight");
        //Get references to the motors from the hardware map

        //Get references to the motors from the hardware map
        leftMotorFront = hardwareMap.dcMotor.get("left_Front_Drive");
        rightMotorFront = hardwareMap.dcMotor.get("right_Front_Drive");
        leftMotorBack = hardwareMap.dcMotor.get("left_Back_Drive");
        rightMotorFront = hardwareMap.dcMotor.get("right_Back_Drive");
        //elevator = hardwareMap.dcMotor.get("lift_Ball");
        //spinner = hardwareMap.dcMotor.get("spinner");
        doorLeft = hardwareMap.servo.get("elevator_Door_Left");
        doorRight = hardwareMap.servo.get("elevator_Door_Right");

        //Reverse the right motors since it is facing the opposite direction as the left motor
        rightMotorFront.setDirection(DcMotor.Direction.REVERSE);
        rightMotorBack.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start
        waitForStart();

        // Go go gadget robot!
        while (opModeIsActive()) {
            if (updateGamepads()) {

                //Set float variables as the inputs from the joystick and the dpad
                //The negative sign is necessary because pushing the joystick up normally sends the robot backward
                //Additionally, set float variables as the input from the triggers
                float driveY = -gamepad1.left_stick_y;
                float turnX = gamepad1.left_stick_x;
                float leftShift = gamepad1.left_trigger;
                float rightShift = gamepad1.right_trigger;


                //Set the power of the motors with the joystick inputs
                leftMotorFront.setPower(driveY);
                leftMotorBack.setPower(driveY);
                rightMotorFront.setPower(driveY);
                rightMotorBack.setPower(driveY);


                //Set up tank turning on the robot
                if (turnX > 0) {
                    leftMotorFront.setPower(turnX);
                    leftMotorBack.setPower(turnX);
                    rightMotorFront.setPower(-turnX);
                    rightMotorBack.setPower(-turnX);
                }
                else if (turnX < 0) {
                    rightMotorFront.setPower(-turnX);
                    rightMotorBack.setPower(-turnX);
                    leftMotorFront.setPower(turnX);
                    leftMotorBack.setPower(turnX);
                }
                else { }


                //Add left and right shift functionality
                if (leftShift > 0) {
                    leftMotorFront.setPower(-leftShift);
                    leftMotorBack.setPower(leftShift);
                    rightMotorFront.setPower(leftShift);
                    rightMotorBack.setPower(-leftShift);
                }
                else if (rightShift > 0) {
                    leftMotorFront.setPower(leftShift);
                    leftMotorBack.setPower(-leftShift);
                    rightMotorFront.setPower(-leftShift);
                    rightMotorBack.setPower(leftShift);
                }
                if (leftShift > 0) {
                    leftMotorFront.setPower(-leftShift);
                    leftMotorBack.setPower(leftShift);
                    rightMotorFront.setPower(leftShift);
                    rightMotorBack.setPower(-leftShift);
                }
                else if (rightShift > 0) {
                    leftMotorFront.setPower(leftShift);
                    leftMotorBack.setPower(-leftShift);
                    rightMotorFront.setPower(-leftShift);
                    rightMotorBack.setPower(leftShift);
                }

                if (gamepad2.y) {
                    toggleButtonY();
                } else { }


            }

            telemetry.update();
            idle();
        }
    }
}
