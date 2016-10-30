//This is the main autonomous program with time for FTC Velocity Vortex 2016
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

    public void driveForward (double power, double time) throws InterruptedException {
        power = power / 4;
        leftMotorFront.setPower(power);
        leftMotorBack.setPower(power);
        rightMotorFront.setPower(power);
        rightMotorBack.setPower(power);
        time = time * 1000;
        Thread.sleep((long) time);
        stopDriving();
    }

    public void driveBackward (double power, double time) throws InterruptedException {
        power = power / 4;
        leftMotorFront.setPower(-power);
        leftMotorBack.setPower(-power);
        rightMotorFront.setPower(-power);
        rightMotorBack.setPower(-power);
        time = time * 1000;
        Thread.sleep((long) time);
        stopDriving();
    }

    public void leftTurn (double power, double time) throws InterruptedException {
        power = power / 4;
        leftMotorFront.setPower(-power);
        leftMotorBack.setPower(-power);
        rightMotorFront.setPower(power);
        rightMotorBack.setPower(power);
        time = time * 1000;
        Thread.sleep((long)time);
        stopDriving();
    }

    public void rightTurn (double power, double time) throws InterruptedException {
        power = power / 4;
        leftMotorFront.setPower(power);
        leftMotorBack.setPower(power);
        rightMotorFront.setPower(-power);
        rightMotorBack.setPower(-power);
        time = time * 1000;
        Thread.sleep((long)time);
        stopDriving();
    }

    public void leftShift (double power, double time) throws InterruptedException {
        power = power / 4;
        leftMotorFront.setPower(power);
        leftMotorBack.setPower(-power);
        rightMotorFront.setPower(-power);
        rightMotorBack.setPower(power);
        time = time * 1000;
        Thread.sleep((long)time);
        stopDriving();
    }

    public void rightShift (double power, double time) throws InterruptedException {
        power = power / 4;
        leftMotorFront.setPower(-power);
        leftMotorBack.setPower(power);
        rightMotorFront.setPower(power);
        rightMotorBack.setPower(-power);
        time = time * 1000;
        Thread.sleep((long)time);
        stopDriving();
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

    public void stopDriving()
    {
        leftMotorFront.setPower(0.0);
        leftMotorBack.setPower(0.0);
        rightMotorFront.setPower(0.0);
        rightMotorBack.setPower(0.0);
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

        openDoor();
        driveForward(1.0, 1.0);
        closeDoor();
        driveBackward(1.0, 1.03);
        driveForward(1.0, 3.0);
        driveBackward(1.0, 0.5);
        rightTurn(1.0, 0.5);
        driveForward(1.0, 4.0);
        driveBackward(1.0, 0.25);
        leftShift(1.0, 1.0);
        driveForward(1.0, 0.25);
        driveBackward(1.0, 0.5);
        rightShift(1.0, 2.0);
        leftTurn(1.0, 0.2);
        driveForward(1.0, 1.0);
    } //Close main
} //Close class and end program
