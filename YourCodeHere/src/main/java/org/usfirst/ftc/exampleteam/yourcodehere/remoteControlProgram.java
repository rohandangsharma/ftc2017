//This is the main remote control program for FTC Velocity Vortex 2016
//Authors: FTC team [11212??], The Lexington Legoheads
//In case of questions email anupendra@gmail.com (coach)
//**********************************************************************************************************
//Run from the necessary package
package org.usfirst.ftc.exampleteam.yourcodehere;

//Import necessary items
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;
import org.swerverobotics.library.*;
import org.swerverobotics.library.interfaces.*;


@TeleOp(name="Remote Control Program") //Name the class
public class remoteControlProgram extends SynchronousOpMode //CLASS START
{
    //Define DC Motors
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;
    //DcMotor elevator;
    //DcMotor spinner;
    //DcMotor shooterLeft;
    //DcMotor shooterRight;

    //Define Servo Motors
    Servo doorLeft;
    Servo doorRight;

    //Define press counts
    //public int aPressCount = 0;
    public int yPressCount = 0;

    //Define floats to be used as joystick and trigger inputs
    float driveY;
    float turnX;
    float rightShift;
    float leftShift;

    //Define servo motor door positions
    final double CLOSED_DOOR_POSITION = 0.4;
    final double OPEN_DOOR_POSITION = 1.2;


    //**********************************************************************************************************
//METHODS BELOW
    //Create a custom function to count the number of times the button "y" is pressed
    //It toggles button y, so if it has been pressed an odd number of times, the door will be open
    //Otherwise, it will be closed
    public void toggleButtonY()
    {
        doorRight.setDirection(Servo.Direction.REVERSE);
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
//Create a custom function to count the number of times the button "a" is pressed
//It toggles button a, so if it has been pressed an odd number of times, the motor will go forward
//Otherwise, it will be off

    /**
     * public void toggleButtonA() {
     * aPressCount = aPressCount + 1;
     * if (aPressCount % 2 == 1) {
     * elevator.setPower(1.0);
     * } else {
     * elevator.setPower(0.0);
     * }
     * }
     **/
//***********************************************************************************************************
//MAIN BELOW
    @Override
    public void main() throws InterruptedException
    {
        //Get references to the motors from the hardware map
        leftMotorFront = hardwareMap.dcMotor.get("leftMotorFront");
        rightMotorFront = hardwareMap.dcMotor.get("rightMotorFront");
        leftMotorBack = hardwareMap.dcMotor.get("leftMotorBack");
        rightMotorBack = hardwareMap.dcMotor.get("rightMotorBack");
        //elevator = hardwareMap.dcMotor.get("elevator");
        //spinner = hardwareMap.dcMotor.get("spinner");
        //shooterLeft = hardwareMap.dcMotor.get("shooterLeft");
        //shooterRight = hardwareMap.dcMotor.get("shooterRight");
        doorLeft = hardwareMap.servo.get("doorLeft");
        doorRight = hardwareMap.servo.get("doorRight");

        //Reverse the right motors since it is facing the opposite direction as the left motor
        leftMotorFront.setDirection(DcMotor.Direction.REVERSE);
        leftMotorBack.setDirection(DcMotor.Direction.REVERSE);
//***********************************************************************************************************
//LOOP BELOW
        waitForStart();
        //Open loops
        while (opModeIsActive()) {
            if (updateGamepads()) {
                //Set float variables as the inputs from the joystick and the dpad
                //The negative sign is necessary because pushing the joystick up normally sends the robot backward
                //Additionally, set float variables as the input from the triggers
                driveY = -gamepad1.left_stick_y / 4;
                turnX = gamepad1.left_stick_x / 4;
                leftShift = gamepad1.left_trigger / 4;
                rightShift = gamepad1.right_trigger / 4;


                //Set the power of the motors with the joystick inputs
                leftMotorFront.setPower(driveY);
                leftMotorBack.setPower(driveY);
                rightMotorFront.setPower(driveY);
                rightMotorBack.setPower(driveY);


                //Set up tank turning on the robot
                if (turnX > 0) {
                    leftMotorFront.setPower(-turnX);
                    leftMotorBack.setPower(-turnX);
                    rightMotorFront.setPower(turnX);
                    rightMotorBack.setPower(turnX);
                }
                else if (turnX < 0) {
                    rightMotorFront.setPower(turnX);
                    rightMotorBack.setPower(turnX);
                    leftMotorFront.setPower(-turnX);
                    leftMotorBack.setPower(-turnX);
                }
                else { }


                //Add left and right shift functionality
                if (leftShift > 0) {
                    leftMotorFront.setPower(leftShift);
                    leftMotorBack.setPower(-leftShift);
                    rightMotorFront.setPower(-leftShift);
                    rightMotorBack.setPower(leftShift);
                }
                else if (rightShift > 0) {
                    leftMotorFront.setPower(-rightShift);
                    leftMotorBack.setPower(rightShift);
                    rightMotorFront.setPower(rightShift);
                    rightMotorBack.setPower(-rightShift);
                }


                //Attachments code

                //Set the power of the spinner so that it runs for the entire run
                //spinner.setPower(1.0);


                //Set the position of the door in 2 different situations, using the "y" button
                // The 2nd situation is void
                if (gamepad2.y) {
                    toggleButtonY();
                } else { }


                //Set the power of the elevator in 2 different situations, using the "a" button.
                // The 2nd situation is void
                //if (gamepad2.a) {
                //  toggleButtonA();
                //} else {
                //}


                //Set the power of the football shooter so that it runs for the entire run
                //shooterLeft.setPower(1.0);
                //shooterRight.setPower(1.0);

            } //Close inside "if" loop
            telemetry.update();
            idle();
        } //Close outside loop
    } //Close main
} //Close class and end program
