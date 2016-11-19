//This is the main remote control program for FTC Velocity Vortex 2016
//Authors: FTC team [11212??], The Lexington Legoheads
//In case of questions email anupendra@gmail.com (coach)
//**********************************************************************************************************
//Run from the necessary package
package org.usfirst.ftc.exampleteam.yourcodehere;

//Import necessary items
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.swerverobotics.library.SynchronousOpMode;


@TeleOp(name="teleOp Simple") //Name the class
public class remoteControlSimple extends SynchronousOpMode //CLASS START
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

    //Define Sensors
    //ColorSensor colorSensor;

    //Define press counts
    //public int aPressCount = 0;
    public int yPressCount = 0;

    //Define floats to be used as joystick and trigger inputs
    float drive;
    float shift;
    float rightTurn;
    float leftTurn;

    //Define servo motor door positions
    final double CLOSED_DOOR_POSITION = 0.4;
    final double OPEN_DOOR_POSITION = 1.2;


    //**********************************************************************************************************
//METHODS BELOW
    //Create a custom function to count the number of times the button "y" is pressed
    //It toggles button y, so if it has been pressed an odd number of times, the door will be open
    //Otherwise, it will be closed
    public void toggleButtonY() {
        doorRight.setDirection(Servo.Direction.REVERSE);
        yPressCount = yPressCount + 1;
        if (yPressCount % 2 == 1) {

            doorLeft.setPosition(OPEN_DOOR_POSITION);
            doorRight.setPosition(OPEN_DOOR_POSITION);
        } else {

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
    public void main() throws InterruptedException {
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
        //colorSensor = hardwareMap.colorSensor.get("colorSensor");

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
                drive = -gamepad1.left_stick_y;
                shift = gamepad1.left_stick_x;
                leftTurn = gamepad1.left_trigger;
                rightTurn = gamepad1.right_trigger;


                //Set the power of the motors with the joystick inputs
                    functions.drive(drive, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);


                //Set up tank turning on the robot
                if (shift != 0)
                {
                    functions.shift(shift, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
                }



                //Add left and right shift functionality
                if (leftTurn > 0)
                {
                    functions.leftTurn(leftTurn, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
                }
                else if (rightTurn > 0)
                {
                    functions.rightTurn(rightTurn, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
                }

                //Attachments code

                    //Set the power of the spinner so that it runs for the entire run
                    //spinner.setPower(1.0);


                    //Set the position of the door in 2 different situations, using the "y" button
                    // The 2nd situation is void
                    if (gamepad2.y)
                    {
                        toggleButtonY();
                    }
                    else { }


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