
//This is the main autonomous program with encoders for FTC Velocity Vortex 2016
//Authors: FTC team [11212??], The Lexington Legoheads
//In case of questions email anupendra@gmail.com (coach)
//**********************************************************************************************************
//Run from the necessary package
package org.usfirst.ftc.exampleteam.yourcodehere;

//Import necessary items

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
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
    ColorSensor colorSensor;
    DeviceInterfaceModule CDI;


//    //Define servo motor door positions
//    final double CLOSED_DOOR_POSITION = 0.4;
//    final double OPEN_DOOR_POSITION = 1.2;

//**********************************************************************************************************
//METHODS BELOW

    public void moveFrontMotors(double powerLeft, double powerRight) {
        while ((rightMotorBack.isBusy()) || (leftMotorBack.isBusy())) {
//            leftMotorFront.setPower(powerLeft);
            rightMotorFront.setPower(powerRight);
        }

        while ((!rightMotorBack.isBusy()) || (!leftMotorBack.isBusy())) {
//            leftMotorFront.setPower(0.0);
            rightMotorFront.setPower(0.0);
        }
        stopDriving();
    }


    public void driveForward(float power, int degrees) {
        power = power / 2;
        leftMotorBack.setMode(DcMotor.RunMode.RESET_ENCODERS);
        rightMotorBack.setMode(DcMotor.RunMode.RESET_ENCODERS);

        leftMotorBack.setTargetPosition(-degrees);
        rightMotorBack.setTargetPosition(-degrees);

        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotorBack.setPower(-power);
        rightMotorBack.setPower(-power);

        moveFrontMotors(-power, -power);
        while ((leftMotorBack.isBusy()) && (rightMotorBack.isBusy()) && (leftMotorFront.isBusy()) && (rightMotorFront.isBusy())) {
        }
        stopDriving();
        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void driveBackward(double power, int degrees) {
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
        while ((leftMotorBack.isBusy()) && (rightMotorBack.isBusy()) && (leftMotorFront.isBusy()) && (rightMotorFront.isBusy())) {
        }
        stopDriving();

        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
    }

    public void leftTurn(double power, int degrees) {
        degrees = degrees * 4;
        power = power / 4;
        leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotorBack.setTargetPosition(-degrees);
        rightMotorBack.setTargetPosition(degrees);

        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotorBack.setPower(-power);
        rightMotorBack.setPower(power);

        moveFrontMotors(-power, power);
        while ((leftMotorBack.isBusy()) && (rightMotorBack.isBusy()) && (leftMotorFront.isBusy()) && (rightMotorFront.isBusy())) {
        }
        stopDriving();
    }

    public void rightTurn(float power, int degrees) {
        leftMotorBack.setMode(DcMotor.RunMode.RESET_ENCODERS);
        rightMotorBack.setMode(DcMotor.RunMode.RESET_ENCODERS);

        leftMotorBack.setTargetPosition(-degrees);
        rightMotorBack.setTargetPosition(degrees);

        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotorBack.setPower(-power);
        rightMotorBack.setPower(power);

        moveFrontMotors(-power, power);
        while ((leftMotorBack.isBusy()) || (rightMotorBack.isBusy()) || (leftMotorFront.isBusy()) || (rightMotorFront.isBusy())) {
        }
        stopDriving();
        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void leftShift(double power, int degrees) throws InterruptedException {
        leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotorBack.setTargetPosition(degrees);
        rightMotorBack.setTargetPosition(-degrees);

        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotorBack.setPower(-power);
        rightMotorBack.setPower(power);

        moveFrontMotors(-power, power);
        while ((leftMotorBack.isBusy()) || (rightMotorBack.isBusy()) || (leftMotorFront.isBusy()) || (rightMotorFront.isBusy())) {
        }
        stopDriving();
    }


    public void rightShift(float shift, int degrees) throws InterruptedException {
        shift = shift / 2;
        leftMotorBack.setMode(DcMotor.RunMode.RESET_ENCODERS);
        rightMotorBack.setMode(DcMotor.RunMode.RESET_ENCODERS);

        leftMotorBack.setTargetPosition(-degrees);
        rightMotorBack.setTargetPosition(degrees);

        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotorBack.setPower(-shift);
        rightMotorBack.setPower(shift);

        moveFrontMotors(shift, -shift);
        while ((leftMotorBack.isBusy()) || (rightMotorBack.isBusy()) || (leftMotorFront.isBusy()) || (rightMotorFront.isBusy())) {
        }

    }

    public void stopDriving() {
        leftMotorFront.setPower(0.0);
        leftMotorBack.setPower(0.0);
        rightMotorFront.setPower(0.0);
        rightMotorBack.setPower(0.0);
    }


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
//        doorLeft = hardwareMap.servo.get("doorLeft");
//        doorRight = hardwareMap.servo.get("doorRight");

        //Get references to the sensors from the hardware map
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        CDI = hardwareMap.deviceInterfaceModule.get("CDI");

        //Reverse the right motors since it is facing the opposite direction as the left motor
        rightMotorFront.setDirection(DcMotor.Direction.REVERSE);
        rightMotorBack.setDirection(DcMotor.Direction.REVERSE);

        //*************************************************************************************************************
//AUTONOMOUS CODE BELOW

//        driveForward((float) 1.0, 5000);
//        rightTurn((float) 1.0, 20000);
//        functions.stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);

        colorSensor.enableLed(true);
        float hsvValues[] = {0, 0, 0};
        //calculate hue
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

        //display values
        telemetry.addData("2 Clear", colorSensor.alpha());
        telemetry.addData("3 Red  ", colorSensor.red());
        telemetry.addData("4 Green", colorSensor.green());
        telemetry.addData("5 Blue ", colorSensor.blue());
        telemetry.addData("6 Hue", hsvValues[0]);

        //illuminate the RED/BLUE LED on the Core Device Interface if the RED/BLUE value is greatest
        if (colorSensor.red() > colorSensor.blue() && colorSensor.red() > colorSensor.green()) {
            CDI.setLED(1, true);
            CDI.setLED(0, false);
        } else if (colorSensor.blue() > colorSensor.red() && colorSensor.blue() > colorSensor.green()) {
            CDI.setLED(1, false);
            CDI.setLED(0, true);
        } else {
            CDI.setLED(1, false);
            CDI.setLED(0, false);
        }


waitOneFullHardwareCycle();
    }

}