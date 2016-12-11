//This is the main remote control program for FTC Velocity Vortex 2016
//Authors: FTC team [11212??], The Lexington Legoheads
//In case of questions email anupendra@gmail.com (coach)
//**********************************************************************************************************
//Run from the necessary package
package org.usfirst.ftc.exampleteam.yourcodehere;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import org.swerverobotics.library.SynchronousOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import static org.usfirst.ftc.exampleteam.yourcodehere.functions.*;

@TeleOp(name="Legoheads Teleop") //Name the class
public class Teleop extends SynchronousOpMode //CLASS START
{
    //Define DC Motors
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;
    DcMotor spinner;
    DcMotor flipper;

    //Define an int to use as the spinner mode
    int spinnerMode = 0;

    //Define color sensor and CDI
    ColorSensor colorSensor;
    DeviceInterfaceModule CDI;

    //Define floats to be used as joystick and trigger inputs, as well as attachment power
    float drive;
    float shift;
    float rightTurn;
    float leftTurn;
    float power = (float) 1.0;

    //Define a long to use as the time to spin the flipper, in milliseconds
    long time = 300;


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


//LOOPS BELOW
        waitForStart();
        //Open loops
        while (opModeIsActive())
        {
            if (updateGamepads())
            {
                //Set float variables as the inputs from the joystick and the triggers
                drive = gamepad1.left_stick_y;
                shift = -gamepad1.left_stick_x;
                leftTurn = gamepad1.left_trigger;
                rightTurn = gamepad1.right_trigger;


                //Drive vs Shift on left joystick (Quadrants):
                //Do nothing if joystick is stationary
                if (Math.abs(drive) == Math.abs(shift))
                {
                    stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
                }
                //Shift if pushed more on X than Y
                if (Math.abs(shift) > Math.abs(drive))
                {
                    shiftTeleop(shift, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
                }
                //Drive if joystick pushed more Y than X
                if (Math.abs(drive) > Math.abs(shift))
                {
                    driveTeleop(drive, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
                }

                //Access turn functions from function class
                //Turn left if left trigger is pushed
                if (leftTurn > 0)
                {
                    leftTurnTeleop(leftTurn, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
                }
                //Turn right if right trigger is pushed
                if (rightTurn > 0)
                {
                    rightTurnTeleop(rightTurn, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
                }

                // "a" button starts spinner forwards
                if (gamepad2.a)
                {
                    spinnerMode = 2;
                    toggleSpinner(spinner, spinnerMode, power);
                }
                // "x" button starts spinner backwards
                if (gamepad2.x)
                {
                    spinnerMode = 1;
                    toggleSpinner(spinner, spinnerMode, power);
                }
                //Stop all motors when "b" is pressed
                if ((gamepad1.b) || (gamepad2.b))
                {
                    stopEverything(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack, spinner, flipper);
                }
                if (gamepad2.y)
                {
                    shootBall(flipper, time);
                }

            } //Close inside "if" loop
            telemetry.update();
            idle();
        } //Close outside loop
    } //Close main
} //Close class and end program