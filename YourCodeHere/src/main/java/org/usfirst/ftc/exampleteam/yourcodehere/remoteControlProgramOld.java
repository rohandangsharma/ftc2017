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


@TeleOp(name="Remote Control Program Old") //Name the class
public class remoteControlProgramOld extends SynchronousOpMode //CLASS START
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
    float drive;
    float turn;
    float absDrivePower;
    float absTurnPower;
    float rightShift;
    float leftShift;


    //Define servo motor door positions
    final double CLOSED_DOOR_POSITION = 0.4;
    final double OPEN_DOOR_POSITION = 1.2;


//**********************************************************************************************************
    //METHODS BELOW


    //Calculate the absolute value of the input, in order to make comparisons easier
    public float absoluteValue(float input)
    {
        if (input < 0)
        {
            input = -input;
        }
        else if (input > 0)
        {
            input = input;
        }
        return input;
    }


    //Set powers to the motors to make the robot drive forwards and backwards, based on joystick input
    public void drive()
    {
        leftMotorFront.setPower(drive);
        leftMotorBack.setPower(drive);
        rightMotorFront.setPower(drive);
        rightMotorBack.setPower(drive);
    }


    //Set up tank turning on the robot, based on joystick inputs
    public void leftOrRightTurn()
    {
        if (turn < 0)
        {
            rightMotorFront.setPower(-turn);
            rightMotorBack.setPower(-turn);
            leftMotorFront.setPower(turn);
            leftMotorBack.setPower(turn);
        }
        else if (turn > 0)
        {
            leftMotorFront.setPower(turn);
            leftMotorBack.setPower(turn);
            rightMotorFront.setPower(-turn);
            rightMotorBack.setPower(-turn);
        }
    }


    //Add left and right shift functionality
    public void meccanumShift()
    {
        if (leftShift > 0)
        {
            //Left shift
            leftMotorFront.setPower(leftShift);
            leftMotorBack.setPower(-leftShift);
            rightMotorFront.setPower(-leftShift);
            rightMotorBack.setPower(leftShift);
        }
        else if (rightShift > 0)
        {
            //Right shift
            leftMotorFront.setPower(-rightShift);
            leftMotorBack.setPower(rightShift);
            rightMotorFront.setPower(rightShift);
            rightMotorBack.setPower(-rightShift);
        }
    }


    //Create a custom function to count the number of times the button "y" is pressed
    //It toggles button y, so if it has been pressed an odd number of times, the door will be open
    //Otherwise, it will be closed
    public void toggleButtonY()
    {
        doorRight.setDirection(Servo.Direction.REVERSE);
        yPressCount = yPressCount + 1;
        if (yPressCount % 2 == 1)
        {
            doorLeft.setPosition(OPEN_DOOR_POSITION);
            doorRight.setPosition(OPEN_DOOR_POSITION);
        }
        else if (yPressCount % 2 == 0)
        {
            doorLeft.setPosition(CLOSED_DOOR_POSITION);
            doorRight.setPosition(CLOSED_DOOR_POSITION);
        }
    }
    //Create a custom function to count the number of times the button "a" is pressed
    //It toggles button a, so if it has been pressed an odd number of times, the motor will go forward
    //Otherwise, it will be off
    /**
     * public void toggleButtonA()
     * {
     * aPressCount = aPressCount + 1;
     * if (aPressCount % 2 == 1) {
     * elevator.setPower(1.0);
     * }
     * else
     * {
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


        //Reverse the left motors since it is facing the opposite direction as the left motor
        leftMotorFront.setDirection(DcMotor.Direction.REVERSE);
        leftMotorBack.setDirection(DcMotor.Direction.REVERSE);
//***********************************************************************************************************
//LOOP BELOW
        waitForStart();
        //Open loops
        while (opModeIsActive())
        {
            if (updateGamepads())
            {
                //DRIVE MOTORS CODE

                //Set float variables as the inputs from the joystick and the dpad
                //The negative signs are necessary as "invert motor" equivalents of last year
                //Additionally, set float variables as the input from the triggers
                //Finally, divide all inputs by 2.5, to scale robot speed to a reasonable amount
                drive = -gamepad1.left_stick_y / (float) 2.5;
                turn = -gamepad1.left_stick_x / (float) 2.5;
                leftShift = gamepad1.left_trigger / (float) 2.5;
                rightShift = gamepad1.right_trigger / (float) 2.5;


                //calculate the absolute value of the two joystick inputs
                absDrivePower = absoluteValue(drive);
                absTurnPower = absoluteValue(turn);


                //Compare the absolute values and run corresponding functions
                if (absDrivePower > absTurnPower)
                {
                    //Set the power of the motors with the joystick inputs to drive forwards or backwards
                    drive();
                }
                else if (absTurnPower > absDrivePower)
                {
                    //Choose the correct direction to turn, based on joystick input
                    leftOrRightTurn();
                }


                //Add meccanum shift functionality by running the function
                meccanumShift();


                //ATTACHMENTS CODE

                //Set the power of the spinner so that it runs for the entire run
                //spinner.setPower(0.5);


                //Set the position of the door in 2 different situations, using the "y" button
                // The 2nd situation is void
                if (gamepad2.y)
                {
                    toggleButtonY();
                }
                else { }


                //Set the power of the elevator in 2 different situations, using the "a" button.
                // The 2nd situation is void
                //if (gamepad2.a)
                // {
                //  toggleButtonA();
                // }
                // else { }


                //Set the power of the football shooter so that it runs for the entire run
                //shooterLeft.setPower(1.0);
                //shooterRight.setPower(1.0);

            } //Close inside "if" loop
            telemetry.update();
            idle();
        } //Close outside loop
    } //Close main
} //Close class and end program