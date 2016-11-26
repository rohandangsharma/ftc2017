//This is the main remote control program for FTC Velocity Vortex 2016
//Authors: FTC team [11212??], The Lexington Legoheads
//In case of questions email anupendra@gmail.com (coach)
//**********************************************************************************************************
//Run from the necessary package
package org.usfirst.ftc.exampleteam.yourcodehere;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.I2cDevice;
import org.swerverobotics.library.SynchronousOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;


@TeleOp(name="teleOp Working") //Name the class
public class remoteControl extends SynchronousOpMode //CLASS START
{
    //Define DC Motors
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;
    DcMotor spinner;
    DcMotor flipper;
    //DcMotor elevator;
    //DcMotor shooterLeft;
    //DcMotor shooterRight;

    //Define Servo Motors
//    Servo doorLeft;
//    Servo doorRight;

    //Define Sensors
    ColorSensor colorSensor;
    DeviceInterfaceModule CDI;


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
        spinner = hardwareMap.dcMotor.get("spinner");
        flipper = hardwareMap.dcMotor.get("flipper");

        //Get references to the sensors from the hardware map
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        CDI = hardwareMap.deviceInterfaceModule.get("CDI");

        //Reverse the right motors since it is facing the opposite direction as the left motor
        rightMotorFront.setDirection(DcMotor.Direction.REVERSE);
        rightMotorBack.setDirection(DcMotor.Direction.REVERSE);
//***********************************************************************************************************
//LOOP BELOW
        waitForStart();
        //Open loops
        while (opModeIsActive()) {
            if (updateGamepads()) {
                //Set float variables as the inputs from the joystick and the dpad
                //The negative sign is necessary because pushing the joystick up normally sends the robot backward
                //Additionally, set float variables as the input from the triggers
                drive = gamepad1.left_stick_y;
                shift = -gamepad1.left_stick_x;
                leftTurn = gamepad1.left_trigger;
                rightTurn = gamepad1.right_trigger;

                //Spinner always turning
                spinner.setPower(-0.2);

                //Drive vs Shift on left joystick:
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
                //Stop all motors when "b" is pressed
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