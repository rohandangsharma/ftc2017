//This is the main autonomous program with encoders for FTC Velocity Vortex 2016
//Authors: FTC team [11212??], The Lexington Legoheads
//In case of questions email anupendra@gmail.com (coach)
//**********************************************************************************************************
//Run from the necessary package
package org.usfirst.ftc.exampleteam.yourcodehere;

//Import necessary items

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
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

//    //Define Servo Motors
//    Servo doorLeft ;
//    Servo doorRight;

    //Define Sensors
//    ColorSensor colorSensor;


//    //Define servo motor door positions
//    final double CLOSED_DOOR_POSITION = 0.4;
//    final double OPEN_DOOR_POSITION = 1.2;

//**********************************************************************************************************
//METHODS BELOW

//public void leftTurn(float power, int degrees)
//{
//    leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//    rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//    leftMotorBack.setTargetPosition(-degrees);
//    rightMotorBack.setTargetPosition(degrees);
//
//    leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//    rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//    leftMotorBack.setPower(-power);
//    rightMotorBack.setPower(power);
//
//    functions.moveFrontMotorsAutonomous(-power, power, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//    while ((leftMotorBack.isBusy()) && (rightMotorBack.isBusy()) && (leftMotorFront.isBusy()) && (rightMotorFront.isBusy()))
//    { }
//    functions.stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//}
//
//public void rightTurn(float power, int degrees)
//{
//    degrees = degrees * 4;
//    power = power / 4;
//    leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//    rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//    leftMotorBack.setTargetPosition(degrees);
//    rightMotorBack.setTargetPosition(-degrees);
//
//    leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//    rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//    leftMotorBack.setPower(power);
//    rightMotorBack.setPower(-power);
//
//    functions.moveFrontMotorsAutonomous(power, -power, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//    while ((leftMotorBack.isBusy()) && (rightMotorBack.isBusy()) && (leftMotorFront.isBusy()) && (rightMotorFront.isBusy()))
//    { }
//    functions.stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//}
//
//
//public void leftShift (float shift, int degrees) throws InterruptedException
//{
//    leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//    rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//    leftMotorBack.setTargetPosition(degrees);
//    rightMotorBack.setTargetPosition(-degrees);
//
//    leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//    rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//    leftMotorBack.setPower(shift);
//    rightMotorBack.setPower(-shift);
//
//    functions.moveFrontMotorsAutonomous(-shift, shift, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//    while ((leftMotorBack.isBusy()) && (rightMotorBack.isBusy()) && (leftMotorFront.isBusy()) && (rightMotorFront.isBusy()))
//    { }
//    functions.stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//}
//
//
//public void rightShift (float shift, int degrees) throws InterruptedException
//{
//    leftMotorBack.setMode(DcMotor.RunMode.RESET_ENCODERS);
//    rightMotorBack.setMode(DcMotor.RunMode.RESET_ENCODERS);
//
//    leftMotorBack.setTargetPosition(-degrees);
//    rightMotorBack.setTargetPosition(degrees);
//
//    leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//    rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//    leftMotorBack.setPower(-shift);
//    rightMotorBack.setPower(shift);
//
//    functions.moveFrontMotorsAutonomous(shift, -shift, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//    while ((leftMotorBack.isBusy()) || (rightMotorBack.isBusy()) || (leftMotorFront.isBusy()) || (rightMotorFront.isBusy()))
//    { }
//    functions.stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
//}








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
//        doorLeft = hardwareMap.servo.get("doorLeft");
//        doorRight = hardwareMap.servo.get("doorRight");

        //Get references to the sensors from the hardware map
//        colorSensor = hardwareMap.colorSensor.get("colorSensor");

        //Reverse the right motors since it is facing the opposite direction as the left motor
        rightMotorFront.setDirection(DcMotor.Direction.REVERSE);
        rightMotorBack.setDirection(DcMotor.Direction.REVERSE);

    //*************************************************************************************************************
//AUTONOMOUS CODE BELOW

        functions.rightShiftAutonomous((float) 1.0, 8500, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

    } //Close main
} //Close class and end program
