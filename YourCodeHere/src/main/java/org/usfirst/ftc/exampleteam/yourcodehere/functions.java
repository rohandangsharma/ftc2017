package org.usfirst.ftc.exampleteam.yourcodehere;

import android.graphics.Color;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;


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
        stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        stopAttachments(spinner, flipper);
    }

    public static void driveTeleop(float drive, DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        leftMotorFront.setPower(drive);
        leftMotorBack.setPower(drive);
        rightMotorFront.setPower(drive);
        rightMotorBack.setPower(drive);
    }

    public static void rightTurnTeleop(float turn, DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        rightMotorFront.setPower(-turn);
        rightMotorBack.setPower(-turn);
        leftMotorFront.setPower(turn);
        leftMotorBack.setPower(turn);
    }

    public static void leftTurnTeleop(float turn, DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        rightMotorFront.setPower(turn);
        rightMotorBack.setPower(turn);
        leftMotorFront.setPower(-turn);
        leftMotorBack.setPower(-turn);
    }

    public static void shiftTeleop(float shift, DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        leftMotorFront.setPower(-shift);
        leftMotorBack.setPower(shift);
        rightMotorFront.setPower(shift);
        rightMotorBack.setPower(-shift);
    }

    public static void toggleSpinner(DcMotor spinner, int aPressCount, double power)
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
        flipper.setPower(0.0);
    }

    public static void driveAutonomous(float power, int degrees, DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        leftMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotorFront.setTargetPosition(-degrees);
        leftMotorBack.setTargetPosition(-degrees);
        rightMotorFront.setTargetPosition(-degrees);
        rightMotorBack.setTargetPosition(-degrees);

        leftMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotorFront.setPower(-power);
        leftMotorBack.setPower(-power);
        rightMotorFront.setPower(-power);
        rightMotorBack.setPower(-power);

        while ((leftMotorFront.isBusy()) || (rightMotorFront.isBusy()) || (leftMotorBack.isBusy()) || (rightMotorBack.isBusy()))
        { }

        stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        leftMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static void leftTurnAutonomous(float power, int degrees, DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        leftMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotorFront.setTargetPosition(-degrees);
        leftMotorBack.setTargetPosition(-degrees);
        rightMotorFront.setTargetPosition(degrees);
        rightMotorBack.setTargetPosition(degrees);

        leftMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotorFront.setPower(-power);
        leftMotorBack.setPower(-power);
        rightMotorFront.setPower(power);
        rightMotorBack.setPower(power);

        while ((leftMotorFront.isBusy()) && (rightMotorFront.isBusy()) && (leftMotorBack.isBusy()) && (rightMotorBack.isBusy()))
        { }

        stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        leftMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static void rightTurnAutonomous(float power, int degrees, DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        leftMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotorFront.setTargetPosition(degrees);
        leftMotorBack.setTargetPosition(degrees);
        rightMotorFront.setTargetPosition(-degrees);
        rightMotorBack.setTargetPosition(-degrees);

        leftMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotorFront.setPower(power);
        leftMotorBack.setPower(power);
        rightMotorFront.setPower(-power);
        rightMotorBack.setPower(-power);

        while ((leftMotorFront.isBusy()) || (rightMotorFront.isBusy()) || (leftMotorBack.isBusy()) || (rightMotorBack.isBusy()))
        { }

        stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        leftMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public static void leftShiftAutonomous(float power, int degrees, DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        leftMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotorFront.setTargetPosition(-degrees);
        leftMotorBack.setTargetPosition(degrees);
        rightMotorFront.setTargetPosition(degrees);
        rightMotorBack.setTargetPosition(-degrees);

        leftMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotorFront.setPower(-power);
        leftMotorBack.setPower(power);
        rightMotorFront.setPower(power);
        rightMotorBack.setPower(-power);

        while ((leftMotorFront.isBusy()) || (rightMotorFront.isBusy()) || (leftMotorBack.isBusy()) || (rightMotorBack.isBusy()))
        { }

        stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        leftMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static void rightShiftAutonomous(float power, int degrees, DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        leftMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotorFront.setTargetPosition(degrees);
        leftMotorBack.setTargetPosition(-degrees);
        rightMotorFront.setTargetPosition(-degrees);
        rightMotorBack.setTargetPosition(degrees);

        leftMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotorFront.setPower(power);
        leftMotorBack.setPower(-power);
        rightMotorFront.setPower(-power);
        rightMotorBack.setPower(power);

        while ((leftMotorFront.isBusy()) || (rightMotorFront.isBusy()) || (leftMotorBack.isBusy()) || (rightMotorBack.isBusy()))
        { }

        stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        leftMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * @param colorSensor The ColorSensor object to read values from
     * @return returns true if the supplied ColorSensor object sees a color.  False otherwise
     */
    public static boolean iSeeAColor(ColorSensor colorSensor)
    {
        float[] hsvValues = {0, 0, 0};
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

        float valueBlue = hsvValues[2];
        float valueRed = hsvValues[1];
        if ((valueBlue == 0) && (valueRed == 0))
        {
            return false;
        }

        return true;
    }

    /**
     * Determines what color the color sensor is seeing
     * @param colorSensor The ColorSensor object to read values from
     * @return The string "blue" if we see the color blue, "red" if we see the color red
     */
    public static String whatColor(ColorSensor colorSensor)
    {
        float[] hsvValues = {0, 0, 0};
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

        float hue = hsvValues[0];

        if (hue > 120)
        {
            return "blue";
        }
        return "red";
    }

    public static void colorSensorAutonomous(String color, ColorSensor colorSensor, DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack) throws InterruptedException
    {

        if (iSeeAColor(colorSensor))
        {
            while (!whatColor(colorSensor).equals(color))
            {
                driveAutonomous((float) 0.2, 200, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
            }
            driveAutonomous((float) 0.2, 300, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
            rightShiftAutonomous((float) 0.2, 3000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        }

    }
}