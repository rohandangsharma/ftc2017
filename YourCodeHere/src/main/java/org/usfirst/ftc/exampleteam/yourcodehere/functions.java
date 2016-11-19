package org.usfirst.ftc.exampleteam.yourcodehere;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by sunildesai on 11/18/16.
 */

public class functions {
    //Right Shift Function

    public static void drive(float drive,  DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        leftMotorFront.setPower(drive);
        leftMotorBack.setPower(drive);
        rightMotorFront.setPower(drive);
        rightMotorBack.setPower(drive);
    }

    public static void leftTurn (float turn,  DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        rightMotorFront.setPower(turn);
        rightMotorBack.setPower(turn);
        leftMotorFront.setPower(-turn);
        leftMotorBack.setPower(-turn);
    }

    public static void rightTurn(float turn,  DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        rightMotorFront.setPower(-turn);
        rightMotorBack.setPower(-turn);
        leftMotorFront.setPower(turn);
        leftMotorBack.setPower(turn);
    }
    public static void shift(float shift, DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        leftMotorFront.setPower(-shift);
        leftMotorBack.setPower(shift);
        rightMotorFront.setPower(shift);
        rightMotorBack.setPower(-shift);
    }
}
