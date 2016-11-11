//This is the main autonomous program with encoders for FTC Velocity Vortex 2016
//Authors: FTC team [11212??], The Lexington Legoheads
//In case of questions email anupendra@gmail.com (coach)
//**********************************************************************************************************
//Run from the necessary package
package org.usfirst.ftc.exampleteam.yourcodehere;

//Import necessary items

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.swerverobotics.library.SynchronousOpMode;


@Autonomous(name="Autonumous Program Encoders") //Name the class
public class autonomousProgramEncoders extends SynchronousOpMode //CLASS START
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
    Servo doorLeft ;
    Servo doorRight;

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

public void moveFrontMotors(double powerLeft, double powerRight)
{
    while ((rightMotorBack.isBusy() == true) || (leftMotorBack.isBusy() == true))
    {
        leftMotorFront.setPower(powerLeft);
        rightMotorFront.setPower(powerRight);
    }
    stopDriving();
}

public void driveForward(double power, int degrees)
{
    degrees = degrees / 1;
    power = (float) power / 1;
    leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    leftMotorBack.setTargetPosition(degrees);
    rightMotorBack.setTargetPosition(degrees);

    leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    leftMotorBack.setPower(power);
    rightMotorBack.setPower(power);

    moveFrontMotors(power, power);
    stopDriving();
}

public void driveBackward( double power, int degrees)
{
    degrees = degrees * 4;
    power = power / 4;
    leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    leftMotorBack.setTargetPosition(degrees);
    rightMotorBack.setTargetPosition(degrees);

    leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    leftMotorBack.setPower(-power);
    rightMotorBack.setPower(-power);

    moveFrontMotors(-power, -power);
    stopDriving();
}

public void leftTurn( double power, int degrees)
{
    degrees = degrees * 4;
    power = power / 4;
    leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    leftMotorBack.setTargetPosition(degrees);
    rightMotorBack.setTargetPosition(degrees);

    leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    leftMotorBack.setPower(power);
    rightMotorBack.setPower(-power);

    moveFrontMotors(power, -power);
    stopDriving();
}

public void rightTurn( double power, int degrees)
{
    degrees = degrees * 4;
    power = power / 4;
    leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    leftMotorBack.setTargetPosition(degrees);
    rightMotorBack.setTargetPosition(degrees);

    leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    leftMotorBack.setPower(-power);
    rightMotorBack.setPower(power);

    moveFrontMotors(-power, power);
    stopDriving();
}


public void leftShift (double power, int degrees) throws InterruptedException
{
    degrees = degrees * 4;
    power = power / 4;
    leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    leftMotorBack.setTargetPosition(degrees);
    rightMotorBack.setTargetPosition(degrees);

    leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    leftMotorBack.setPower(-power);
    rightMotorBack.setPower(power);

    moveFrontMotors(power, -power);
    stopDriving();
}


public void rightShift (double power, int degrees) throws InterruptedException
{
    degrees = degrees / 5;
    power = power / 4;
    leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    leftMotorBack.setTargetPosition(degrees);
    rightMotorBack.setTargetPosition(degrees);

    leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    leftMotorBack.setPower(power);
    rightMotorBack.setPower(-power);

    moveFrontMotors(-power, power);
    stopDriving();
}

public void stopDriving()
{
    leftMotorFront.setPower(0.0);
    leftMotorBack.setPower(0.0);
    rightMotorFront.setPower(0.0);
    rightMotorBack.setPower(0.0);
}


public void openDoor()
{
    doorLeft.setPosition(OPEN_DOOR_POSITION);
    doorRight.setPosition(OPEN_DOOR_POSITION);
}


public void closeDoor()
{
    doorLeft.setPosition(CLOSED_DOOR_POSITION);
    doorRight.setPosition(CLOSED_DOOR_POSITION);
}







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

    //*************************************************************************************************************
//AUTONOMOUS CODE BELOW

       driveForward(1.0, 12);

    } //Close main
} //Close class and end program
