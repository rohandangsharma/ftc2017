package org.usfirst.ftc.exampleteam.yourcodehere;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;

public class functions
{
    public static void stopDriving(DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        leftMotorFront.setPower(0.0);
        leftMotorBack.setPower(0.0);
        rightMotorFront.setPower(0.0);
        rightMotorBack.setPower(0.0);
    }
    public static void stopAttachments(DcMotor spinner, DcMotor flipper)
    {
        spinner.setPower(0.0);
        flipper.setPower(0.0);
    }

    public static void stopEverything(DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack, DcMotor spinner, DcMotor flipper)
    {
        functions.stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        functions.stopAttachments(spinner, flipper);
    }

    public static void driveTeleop(float drive,  DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        leftMotorFront.setPower(drive);
        leftMotorBack.setPower(drive);
        rightMotorFront.setPower(drive);
        rightMotorBack.setPower(drive);
    }

    public static void rightTurnTeleop(float turn,  DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        rightMotorFront.setPower(-turn);
        rightMotorBack.setPower(-turn);
        leftMotorFront.setPower(turn);
        leftMotorBack.setPower(turn);
    }

    public static void leftTurnTeleop(float turn,  DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        rightMotorFront.setPower(turn);
        rightMotorBack.setPower(turn);
        leftMotorFront.setPower(-turn);
        leftMotorBack.setPower(-turn);
    }
    public static void shiftTeleop(float shift, DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)  {
        leftMotorFront.setPower(-shift);
        leftMotorBack.setPower(shift);
        rightMotorFront.setPower(shift);
        rightMotorBack.setPower(-shift);
    }

    public static void toggleSpinner(DcMotor spinner, int aPressCount, float power )
    {
        if (aPressCount % 3 == 0)
        {
            spinner.setPower(0.0);
        }
        if (aPressCount % 3 == 1)
        {
            spinner.setPower(power);
        }
        if (aPressCount % 3 == 2)
        {
            spinner.setPower(-power);
        }
    }

    public static void shootBall(DcMotor flipper, long time) throws InterruptedException
    {
        flipper.setPower(1.0);
        Thread.sleep(time);
        flipper.setPower(0);
    }

    public static void moveFrontMotorsAutonomous(float powerLeft, float powerRight, DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        if ((rightMotorBack.isBusy()) || (leftMotorBack.isBusy()))
        {
            leftMotorFront.setPower(powerLeft);
            rightMotorFront.setPower(powerRight);
        }
        if ((!rightMotorBack.isBusy()) && (!leftMotorBack.isBusy()))
        {
            leftMotorFront.setPower(0.0);
            rightMotorFront.setPower(0.0);
        }
    }

    public static void driveForwardAutonomous(float power, int degrees, DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotorBack.setTargetPosition(-degrees);
        rightMotorBack.setTargetPosition(-degrees);

        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotorBack.setPower(-power);
        rightMotorBack.setPower(-power);

        functions.moveFrontMotorsAutonomous(-power, -power, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        while (leftMotorBack.isBusy() || rightMotorBack.isBusy())
        { }

        functions.stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static void driveBackwardAutonomous(float power, int degrees, DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotorBack.setTargetPosition(degrees);
        rightMotorBack.setTargetPosition(degrees);

        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotorBack.setPower(power);
        rightMotorBack.setPower(power);

        functions.moveFrontMotorsAutonomous(power, power, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        while ((leftMotorBack.isBusy()) && (rightMotorBack.isBusy()) && (leftMotorFront.isBusy()) && (rightMotorFront.isBusy()))
        { }

        functions.stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static void leftTurnAutonomous(float power, int degrees, DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotorBack.setTargetPosition(-degrees);
        rightMotorBack.setTargetPosition(degrees);

        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotorBack.setPower(-power);
        rightMotorBack.setPower(power);

        functions.moveFrontMotorsAutonomous(-power, power, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        while ((leftMotorBack.isBusy()) || (rightMotorBack.isBusy()))
        { }

        functions.stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static void rightTurnAutonomous(float power, int degrees, DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotorBack.setTargetPosition(degrees);
        rightMotorBack.setTargetPosition(-degrees);

        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotorBack.setPower(power);
        rightMotorBack.setPower(-power);

        functions.moveFrontMotorsAutonomous(power, -power, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        while ((leftMotorBack.isBusy()) || (rightMotorBack.isBusy()))
        { }

        functions.stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }



    public static void leftShiftAutonomous (float shift, int degrees, DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        leftMotorBack.setMode(DcMotor.RunMode.RESET_ENCODERS);
        rightMotorBack.setMode(DcMotor.RunMode.RESET_ENCODERS);

        leftMotorBack.setTargetPosition(degrees);
        rightMotorBack.setTargetPosition(-degrees);

        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotorBack.setPower(shift);
        rightMotorBack.setPower(-shift);

        functions.moveFrontMotorsAutonomous(-shift, shift, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        while ((leftMotorBack.isBusy()) && (rightMotorBack.isBusy()) && (leftMotorFront.isBusy()) && (rightMotorFront.isBusy()))
        { }

        functions.stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static void rightShiftAutonomous (float shift, int degrees, DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        leftMotorBack.setMode(DcMotor.RunMode.RESET_ENCODERS);
        rightMotorBack.setMode(DcMotor.RunMode.RESET_ENCODERS);

        leftMotorBack.setTargetPosition(-degrees);
        rightMotorBack.setTargetPosition(degrees);

        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotorBack.setPower(-shift);
        rightMotorBack.setPower(shift);

        functions.moveFrontMotorsAutonomous(shift, -shift, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        while ((leftMotorBack.isBusy()) && (rightMotorBack.isBusy()) && (leftMotorFront.isBusy()) && (rightMotorFront.isBusy()))
        { }

        functions.stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static boolean blueGreater(ColorSensor colorSensor) {
        if (colorSensor.blue() > colorSensor.red()) {
            return true;
        }
        else {
            return false;
        }
    }
} //Close class
