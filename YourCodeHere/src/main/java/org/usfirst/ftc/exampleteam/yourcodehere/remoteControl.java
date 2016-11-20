//This is the main remote control program for FTC Velocity Vortex 2016
//Authors: FTC team [11212??], The Lexington Legoheads
//In case of questions email anupendra@gmail.com (coach)
//**********************************************************************************************************
//Run from the necessary package
package org.usfirst.ftc.exampleteam.yourcodehere;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.swerverobotics.library.SynchronousOpMode;


@TeleOp(name="teleOp Working") //Name the class
public class remoteControl extends SynchronousOpMode //CLASS START
{
    //Define DC Motors
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;
    DcMotor spinner;
    //DcMotor elevator;
    //DcMotor shooterLeft;
    //DcMotor shooterRight;


    //Define Servo Motors
//    Servo doorLeft;
//    Servo doorRight;

    //Define Sensors
    //ColorSensor colorSensor;

    //Define press counts
    public int aPressCount = 0;
    //public int yPressCount = 0;

    //Define floats to be used as joystick and trigger inputs
    float drive;
    float shift;
    float rightTurn;
    float leftTurn;

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
        spinner = hardwareMap.dcMotor.get("spinner");
        //shooterLeft = hardwareMap.dcMotor.get("shooterLeft");
        //shooterRight = hardwareMap.dcMotor.get("shooterRight");
//        doorLeft = hardwareMap.servo.get("doorLeft");
//        doorRight = hardwareMap.servo.get("doorRight");
        //colorSensor = hardwareMap.colorSensor.get("colorSensor");

        //Reverse the right motors since it is facing the opposite direction as the left motor
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
                //Set float variables as the inputs from the joystick and the dpad
                //The negative sign is necessary because pushing the joystick up normally sends the robot backward
                //Additionally, set float variables as the input from the triggers
                drive = -gamepad1.left_stick_y;
                shift = gamepad1.left_stick_x;
                leftTurn = gamepad1.left_trigger;
                rightTurn = gamepad1.right_trigger;

//                functions.drive(drive, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

                //Call drive function from function class
                //Do nothing if joystick is stationary
                if (Math.abs(drive) == Math.abs(shift))
                {
                    functions.stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
                }
                //Shift if pushed more on X than Y
                if (Math.abs(shift) > Math.abs(drive))
                {
                    functions.shiftTeleop(shift, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
                }
                //Drive if joystick pushed more Y than X
                if (Math.abs(drive) > Math.abs(shift))
                {
                    functions.driveTeleop(drive, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
                }


                //Access turn functions from function class
                if (leftTurn > 0)
                {
                    functions.leftTurnTeleop(leftTurn, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
                }
                if (rightTurn > 0)
                {
                    functions.rightTurnTeleop(rightTurn, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
                }

                //Attachments code
                //Set the power of the spinner so that it toggles between forward and backward
                if (gamepad1.a)
                {
                    functions.movesSpinnerTeleop(spinner, aPressCount);
                }
                if (gamepad1.b)
                {
                    functions.stopEverything(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack, spinner);
                }
            } //Close inside "if" loop
            telemetry.update();
            idle();
        } //Close outside loop
    } //Close main
} //Close class and end program