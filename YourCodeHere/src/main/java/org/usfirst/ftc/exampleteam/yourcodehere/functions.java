package org.usfirst.ftc.exampleteam.yourcodehere;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.swerverobotics.library.SynchronousOpMode;


/**
 * Created by sunildesai on 11/18/16.
 */

public class functions
{

    public static void stopEverything(DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack, DcMotor spinner)
    {
        functions.stopDriving(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        spinner.setPower(0.0);
    }

    public static void drive(float drive,  DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        leftMotorFront.setPower(drive);
        leftMotorBack.setPower(drive);
        rightMotorFront.setPower(drive);
        rightMotorBack.setPower(drive);
    }

    public static void stopDriving(DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack)
    {
        leftMotorFront.setPower(0.0);
        leftMotorBack.setPower(0.0);
        rightMotorFront.setPower(0.0);
        rightMotorBack.setPower(0.0);
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

    public static void movesSpinner(DcMotor spinner, int aPressCount )
    {
            aPressCount = aPressCount + 1;
            if (aPressCount % 2 == 0)
            {
                spinner.setPower(1.0);
            }
            else if (aPressCount % 2 == 1)
            {
                spinner.setPower(-1.0);
            }
    }

}
