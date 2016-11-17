//This is the main autonomous program with encoders for FTC Velocity Vortex 2016
//Authors: FTC team [11212??], The Lexington Legoheads
//In case of questions email anupendra@gmail.com (coach)
//**********************************************************************************************************
//Run from the necessary package
package org.usfirst.ftc.exampleteam.yourcodehere;

//Import necessary items

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.swerverobotics.library.SynchronousOpMode;

import java.sql.Time;
import java.util.concurrent.TimeUnit;


@Autonomous(name="Autonumous Program Time") //Name the class
public class autonomousProgramTime extends SynchronousOpMode //CLASS START
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

public void moveFrontMotors(double powerLeft, double powerRight)
{
    while ((rightMotorBack.isBusy() == true) || (leftMotorBack.isBusy() == true))
    {
        leftMotorFront.setPower(powerLeft);
        rightMotorFront.setPower(powerRight);
    }
    stopDriving();
}



public void driveForward(float power, long time) throws InterruptedException {

    leftMotorBack.setPower(power);
    rightMotorBack.setPower(power);
    Thread.sleep(time);
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
    leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
    rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
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
    telemetry.addData("Encoder", leftMotorBack.getCurrentPosition());
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


//public void openDoor()
//{
//    doorLeft.setPosition(OPEN_DOOR_POSITION);
//    doorRight.setPosition(OPEN_DOOR_POSITION);
//}
//
//
//public void closeDoor()
//{
//    doorLeft.setPosition(CLOSED_DOOR_POSITION);
//    doorRight.setPosition(CLOSED_DOOR_POSITION);
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

       driveForward((float) 1.0, 50);
        telemetry.addData("Encoder", leftMotorBack.getCurrentPosition());
        telemetry.update();


    } //Close main
} //Close class and end program
