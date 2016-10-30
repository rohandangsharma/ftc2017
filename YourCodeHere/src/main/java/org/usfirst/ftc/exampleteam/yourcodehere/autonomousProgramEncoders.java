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


@Autonomous(name="Autonumous Program") //Name the class
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
    Servo doorLeft = null;
    Servo doorRight = null;

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

public void driveForward( double power, int degrees)
{
    degrees = degrees * 4;
    leftMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    leftMotorFront.setTargetPosition(degrees);
    leftMotorBack.setTargetPosition(degrees);
    rightMotorFront.setTargetPosition(degrees);



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
        leftMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    } //Close main
} //Close class and end program
