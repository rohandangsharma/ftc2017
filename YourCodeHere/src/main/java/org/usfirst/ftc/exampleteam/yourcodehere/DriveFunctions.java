//Run from the package
package org.usfirst.ftc.exampleteam.yourcodehere;

//Import necessary items
import android.graphics.Color;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import org.swerverobotics.library.SynchronousOpMode;

@Disabled
public class DriveFunctions extends SynchronousOpMode
{ //START CLASS
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;
    DcMotor spinner;
    DcMotor shooterLeft;
    DcMotor shooterRight;
    ColorSensor colorSensorLeft;
    ColorSensor colorSensorRight;
    ColorSensor colorSensorBottom;
    DeviceInterfaceModule CDI;

    public DriveFunctions(DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack, DcMotor spinner, DcMotor shooterLeft, DcMotor shooterRight, ColorSensor colorSensorLeft, ColorSensor colorSensorRight, ColorSensor colorSensorBottom, DeviceInterfaceModule CDI)
    {
        //These lines enable us to store the motors, sensors and CDI without having to write them over and over again
        this.leftMotorFront = leftMotorFront;
        this.leftMotorBack = leftMotorBack;
        this.rightMotorFront = rightMotorFront;
        this.rightMotorBack = rightMotorBack;
        this.spinner = spinner;
        this.shooterLeft = shooterLeft;
        this.shooterRight = shooterRight;
        this.colorSensorLeft = colorSensorLeft;
        this.colorSensorRight = colorSensorRight;
        this.colorSensorBottom = colorSensorBottom;
        this.CDI = CDI;
    }

    public void initializeMotorsAndSensors()
    {
        //Set the sensors to the modes that we want, and set their addresses
        colorSensorBottom.enableLed(true);
        colorSensorBottom.setI2cAddress(I2cAddr.create8bit(0x3a));
        colorSensorLeft.enableLed(true);
        colorSensorLeft.setI2cAddress(I2cAddr.create8bit(0x3c));
        colorSensorRight.enableLed(false);
        colorSensorRight.setI2cAddress(I2cAddr.create8bit(0x3e));

        //Reverse some motors and keep others forward
        leftMotorFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftMotorBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotorFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotorBack.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    /**
     * If this function is called, stop the drive motors
     */
    public void stopDriving()
    {
        leftMotorFront.setPower(0.0);
        leftMotorBack.setPower(0.0);
        rightMotorFront.setPower(0.0);
        rightMotorBack.setPower(0.0);
    }

    /**
     * If this function is called, stop the attachments
     */
    public void stopAttachments()
    {
        spinner.setPower(0.0);
        shooterRight.setPower(0.0);
        shooterLeft.setPower(0.0);
    }

    /**
     * If this function is called, stop everything
     */
    public void stopEverything()
    {
        stopDriving();
        stopAttachments();
    }

    /**
     * If this function is called, turn on the drive motors at the given powers to make it drive forward or backwards
     */
    public void driveTeleop(float drive)
    {
        leftMotorFront.setPower(drive);
        leftMotorBack.setPower(drive);
        rightMotorFront.setPower(drive);
        rightMotorBack.setPower(drive);
    }

    /**
     * If this function is called, turn on the drive motors at the given powers, to make it tank turn right
     */
    public void rightTurnTeleop(float turn)
    {
        //Turn the right motors backwards and the left motors forward so that it turns right
        leftMotorFront.setPower(turn);
        leftMotorBack.setPower(turn);
        rightMotorFront.setPower(-turn);
        rightMotorBack.setPower(-turn);
    }

    /**
     * If this function is called, turn on the drive motors at the given powers, to make it tank turn left
     */
    public void leftTurnTeleop(float turn)
    {
        //Turn the left motors backwards and the right motors forward so that it turns left
        leftMotorFront.setPower(-turn);
        leftMotorBack.setPower(-turn);
        rightMotorFront.setPower(turn);
        rightMotorBack.setPower(turn);
    }

    /**
     * If this function is called, turn on the drive motors at the given powers, to make it shift in the desired direction
     */
    public void shiftTeleop(float shift)
    {
        //This sequence of backwards, forwards, forwards, backwards makes the robot shift
        leftMotorFront.setPower(-shift);
        leftMotorBack.setPower(shift);
        rightMotorFront.setPower(shift);
        rightMotorBack.setPower(-shift);
    }

    /**
     * Toggle the spinner to make it stop, go forwards, and go backwards whenever we want
     * @param spinnerMode tells the mode of the spinner, that is set in the teleop program
     */
    public void toggleSpinner(int spinnerMode, float power)
    {
        if (spinnerMode % 3 == 0)
        {
            spinner.setPower(0.0);
        }
        if (spinnerMode % 3 == 1)
        {
            spinner.setPower(power);
        }
        if (spinnerMode % 3 == 2)
        {
            spinner.setPower(-power);
        }
    }

    /**
     * @param power power for shooter to move at
     */
    public void shootBall(float power)
    {
        //Turn on the two wheels in opposite directions
        shooterLeft.setPower(power);
        shooterRight.setPower(-power);
    }

    /**
     * Reset encoder readings
     * Sets to mode of turning for degrees specified
     */
//    public void prepMotorsForAutonomous()
//    {
//        //Reset encoders
//        leftMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        //Set up the motors run to the given position
//        leftMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//    }

    public void moveMotorsWithEncoders(int leftFrontDegrees, int leftBackDegrees, int rightFrontDegrees, int rightBackDegrees, float leftFrontPower, float leftBackPower, float rightFrontPower, float rightBackPower)
    {
        //Resets encoders
        leftMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set up the motors run to the given position
        leftMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Sets the target postition as the corresponding values entered
        leftMotorFront.setTargetPosition(leftFrontDegrees);
        leftMotorBack.setTargetPosition(leftBackDegrees);
        rightMotorFront.setTargetPosition(rightFrontDegrees);
        rightMotorBack.setTargetPosition(rightBackDegrees);

        //Turn on the motors at the given powers
        leftMotorFront.setPower(leftFrontPower);
        leftMotorBack.setPower(leftBackPower);
        rightMotorFront.setPower(rightFrontPower);
        rightMotorBack.setPower(rightBackPower);

        //Empty while loop while the motors are moving
        while ((leftMotorFront.isBusy()) && (rightMotorFront.isBusy()) && (leftMotorBack.isBusy()) && (rightMotorBack.isBusy()))
        { }

        //Stop driving
        stopDriving();

        //Use the encoders in the future
        leftMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Drive for the given distance at the given power
     * @param degrees distance
     */
    public void driveAutonomous(float power, int degrees)
    {
        //INVERT MOTORS
        power = -power;
        degrees = -degrees;
        moveMotorsWithEncoders(degrees, degrees, degrees, degrees, power, power, power, power);
//        //Prepare encoders
//        prepMotorsForAutonomous();
//
//        //Set the target position of the encoders as the input
//        leftMotorFront.setTargetPosition(-degrees);
//        leftMotorBack.setTargetPosition(-degrees);
//        rightMotorFront.setTargetPosition(-degrees);
//        rightMotorBack.setTargetPosition(-degrees);
//
//        //Turn on the motors at the given powers
//        leftMotorFront.setPower(-power);
//        leftMotorBack.setPower(-power);
//        rightMotorFront.setPower(-power);
//        rightMotorBack.setPower(-power);
//
//        //Empty while loop while motors are moving
//        while ((leftMotorFront.isBusy()) && (rightMotorFront.isBusy()) && (leftMotorBack.isBusy()) && (rightMotorBack.isBusy()))
//        { }
//
//        //Stop motors and use encoders
//        stopDriving();
//        prepMotorsForTeleop();
    }

    /**
     * Turn left for the given distance at the given power
     * @param degrees distance
     */
    public void leftTurnAutonomous(float power, int degrees)
    {
        //Left motors backwards and right motors forwards gives us a left turn
        moveMotorsWithEncoders(-degrees, -degrees, degrees, degrees, -power, -power, power, power);

//        //Prepare encoders
//        prepMotorsForAutonomous();
//
//        //Set motor target positions
//        //Left motors backwards and right motors forwards gives us a left turn
//        leftMotorFront.setTargetPosition(-degrees);
//        leftMotorBack.setTargetPosition(-degrees);
//        rightMotorFront.setTargetPosition(degrees);
//        rightMotorBack.setTargetPosition(degrees);
//
//
//        //Turn on the motors at the given powers
//        //Left motors backwards and right motors forwards gives us a left turn
//        leftMotorFront.setPower(-power);
//        leftMotorBack.setPower(-power);
//        rightMotorFront.setPower(power);
//        rightMotorBack.setPower(power);
//
//        //Empty while loop while motors are moving
//        while ((leftMotorFront.isBusy()) && (rightMotorFront.isBusy()) && (leftMotorBack.isBusy()) && (rightMotorBack.isBusy()))
//        { }
//
//        //Stop motors and use encoders
//        stopDriving();
//        prepMotorsForTeleop();
    }

    /**
     * Turn right for the given distance at the given power
     * @param degrees distance
     */
    public void rightTurnAutonomous(float power, int degrees)
    {
        //Right motors backwards and left motors forwards gives us a right turn
        moveMotorsWithEncoders(degrees, degrees, -degrees, -degrees, power, power, -power, -power);
//        //Prepare encoders
//        prepMotorsForAutonomous();
//
//        //Set motor target positions
//        //Right motors backwards and left motors forwards gives us a right turn
//        leftMotorFront.setTargetPosition(degrees);
//        leftMotorBack.setTargetPosition(degrees);
//        rightMotorFront.setTargetPosition(-degrees);
//        rightMotorBack.setTargetPosition(-degrees);
//
//        //Turn on the motors at the given powers
//        //Right motors backwards and left motors forwards gives us a right turn
//        leftMotorFront.setPower(power);
//        leftMotorBack.setPower(power);
//        rightMotorFront.setPower(-power);
//        rightMotorBack.setPower(-power);
//
//        //Empty while loop while motors are moving
//        while ((leftMotorFront.isBusy()) && (rightMotorFront.isBusy()) && (leftMotorBack.isBusy()) && (rightMotorBack.isBusy()))
//        { }
//
//        //Stop motors and use encoders
//        stopDriving();
//        prepMotorsForTeleop();
    }

    /**
     * Shift left for the given distance at the given power
     * @param degrees distance
     */
    public void leftShiftAutonomous(float power, int degrees)
    {
        //This sequence of backwards, forwards, forwards, backwards makes the robot shift left
        moveMotorsWithEncoders(-degrees, degrees, degrees, -degrees, -power, power, power, -power);
//        //Prepare encoders
//        prepMotorsForAutonomous();
//
//        //Set motor target positions
//        //This sequence of backwards, forwards, forwards, backwards makes the robot shift left
//        leftMotorFront.setTargetPosition(-degrees);
//        leftMotorBack.setTargetPosition(degrees);
//        rightMotorFront.setTargetPosition(degrees);
//        rightMotorBack.setTargetPosition(-degrees);
//
//        //Turn on the motors at the given powers
//        //This sequence of backwards, forwards, forwards, backwards makes the robot shift left
//        leftMotorFront.setPower(-power);
//        leftMotorBack.setPower(power);
//        rightMotorFront.setPower(power);
//        rightMotorBack.setPower(-power);
//
//        //Empty while loop while motors are moving
//        while ((leftMotorFront.isBusy()) && (rightMotorFront.isBusy()) && (leftMotorBack.isBusy()) && (rightMotorBack.isBusy()))
//        { }
//
//        //Stop motors and use encoders
//        stopDriving();
//        prepMotorsForTeleop();
    }

    /**
     * Shift right for the given distance at the given power
     * @param degrees distance
     */
    public void rightShiftAutonomous(float power, int degrees)
    {
        //This sequence of forwards, backwards, backwards, forwards makes the robot shift right
        moveMotorsWithEncoders(degrees, -degrees, -degrees, degrees, power, -power, -power, power);
//        //Prepare encoders
//        prepMotorsForAutonomous();
//
//        //Set motor target positions
//        //This sequence of forwards, backwards, backwards, forwards makes the robot shift right
//        leftMotorFront.setTargetPosition(degrees);
//        leftMotorBack.setTargetPosition(-degrees);
//        rightMotorFront.setTargetPosition(-degrees);
//        rightMotorBack.setTargetPosition(degrees);
//
//        //Turn on the motors at the given powers
//        //This sequence of forwards, backwards, backwards, forwards makes the robot shift right
//        leftMotorFront.setPower(power);
//        leftMotorBack.setPower(-power);
//        rightMotorFront.setPower(-power);
//        rightMotorBack.setPower(power);
//
//        //Empty while loop while motors are moving
//        while ((leftMotorFront.isBusy()) && (rightMotorFront.isBusy()) && (leftMotorBack.isBusy()) && (rightMotorBack.isBusy()))
//        { }
//
//        //Stop motors and use encoders
//        stopDriving();
//        prepMotorsForTeleop();
    }

    /**
     * @return returns true if the supplied ColorSensor either red or blue.  False otherwise
     */
    public boolean iSeeAColor(ColorSensor colorSensor)
    {
        //This is an array that stores the hue[0], saturation[1], and value[2], values
        float[] hsvValues = {0F, 0F, 0F};

        //Convert from RGB to HSV (red-green-blue to hue-saturation-value)
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

        //If no value, return false
        if (hsvValues[2] == 0)
        {
            return false;
        }

        //Otherwise return true
        return true;
    }

    /**
     * Determines what color the color sensor is seeing
     * @param colorSensor take in the correct color sensor
     * @return The string "Blue" if we see the color blue, "Red" if we see the color red
     */
    public String whatColor(ColorSensor colorSensor)
    {
        float hue; //Define float for hue
        float[] hsvValues = {0, 0, 0}; //This is an array that stores the hue[0], saturation[1], and value[2], values
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues); //Convert from RGB to HSV (red-green-blue to hue-saturation-value)

        hue = hsvValues[0]; //store the first value from the array into hue

        if (hue > 120) //If hue is greater than 120, we are looking at blue, so return blue
        {
            return "Blue";
        }

        //Otherwise return red
        return "Red";
    }

    /**
     * Drives forward slowly until the target color is seen, then shifts into the beacon to press
     * the button
     * @param color take in our team color
     * @param colorSensor take in the correct color sensor
     */
    public void beaconColorCheck(String color, ColorSensor colorSensor)
    {
        double power = 0.2;
        int findBeaconDistance = 100;
        int alignBeaconDistance = 400;

        //While we do not see the beacon, drive forward
        while (!iSeeAColor(colorSensor))
        {
            driveAutonomous((float) power, findBeaconDistance);
        }

        //Now we see a color, but possibly not the target color
        //While we don't see the target color, drive forward
        while (!whatColor(colorSensor).equals(color))
        {
            driveAutonomous((float) power, findBeaconDistance);
        }

        //Now we see the target color, drive forward a tiny bit so cardboard is aligned
        driveAutonomous((float) power, alignBeaconDistance);

        //The robot is aligned to the button of the target color, shift into the button to press it
        if (color.equals("Blue"))
        {
            rightShiftAutonomous((float) 0.4, 800);
        }

        if (color.equals("Red"))
        {
            leftShiftAutonomous((float) 0.4, 800);
        }
    }

    /**
     * Stops on the white line
     */
    public void stopOnWhiteLine(float drivePower)
    {
        //Switch the direction that is entered to make it correct
        drivePower = -drivePower;

        //Use the alpha value, because it measures luminosity, and the white line has a much higher luminosity compared to the mat
        //If the alpha value is less than 20, drive forward
        //The alpha value of the mat is close to zero, and the alpha value of the line is above 50
        //This means that if the below condition is true, we are not on the line
        while (colorSensorLeft.alpha() < 20)
        {
            driveTeleop(drivePower);
        }

        //When the above condition is no longer true, stop the robot, after which it will be on the line
        stopDriving();
    }

    /**
     *
     * @param color take in our team color
     */
    public void AutoBeacons(String color)
    {
        if (color.equals("Blue"))
        {
            if (gamepad1.dpad_left)
            {
                if (gamepad1.x)
                {
                    //Empty
                }
                if (gamepad1.y)
                {
                    //1-2
                }
                if (gamepad1.b)
                {
                    //1-3
                }
                if (gamepad1.a)
                {
                    //1-4
                }
            } //Close "if" statement of when we are on beacon 1

            if (gamepad1.dpad_up)
            {
                if (gamepad1.x)
                {
                    //2-1
                }
                if (gamepad1.y)
                {
                    //Empty
                }
                if (gamepad1.b)
                {
                    //2-3
                }
                if (gamepad1.a)
                {
                    //2-4
                }
            } //Close "if" statement of when we are on beacon 2

            if (gamepad1.dpad_right)
            {
                if (gamepad1.x)
                {
                    //3-1
                }
                if (gamepad1.y)
                {
                    //3-2
                }
                if (gamepad1.b)
                {
                    //Empty
                }
                if (gamepad1.a)
                {
                    //3-4
                }
            } //Close "if" statement of when we are on beacon 3

            if (gamepad1.dpad_down)
            {
                if (gamepad1.x)
                {
                    //4-1
                }
                if (gamepad1.y)
                {
                    //4-2
                }
                if (gamepad1.b)
                {
                    //4-3
                }
                if (gamepad1.a)
                {
                    //Empty
                }
            } //Close "if" statement of when we are on beacon 4
        } //Close "if" statement of when we are blue team
    } //Close method


    //Empty main
    @Override
    protected void main ( ) throws InterruptedException
    {

    }
} //CLOSE CLASS

