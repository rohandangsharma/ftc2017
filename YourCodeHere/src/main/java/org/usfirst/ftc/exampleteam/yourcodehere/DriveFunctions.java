//Run from the package
package org.usfirst.ftc.exampleteam.yourcodehere;

//Import necessary items
import android.graphics.Color;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Disabled
public class DriveFunctions
{ //START CLASS
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;
    DcMotor spinner;
    DcMotor flipper;
    ColorSensor colorSensor;

    public DriveFunctions(DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack, ColorSensor colorSensor, DcMotor spinner, DcMotor flipper){
        this.leftMotorFront = leftMotorFront;
        this.leftMotorBack = leftMotorBack;
        this.rightMotorFront = rightMotorFront;
        this.rightMotorBack = rightMotorBack;
        this.colorSensor = colorSensor;
        this.spinner = spinner;
        this.flipper = flipper;

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
        flipper.setPower(0.0);
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
     * If this function is called, turn on the drive motors at the given powers to make it drive forward
     */
    public void driveTeleop(float drive)
    {
        leftMotorFront.setPower(drive);
        leftMotorBack.setPower(drive);
        rightMotorFront.setPower(drive);
        rightMotorBack.setPower(drive);
    }

    /**
     * If this function is called, turn on the drive motors at the given powers, to make it turn right
     */
    public void rightTurnTeleop(float turn)
    {
        //Turn the right motors backwards and the left motors forward
        rightMotorFront.setPower(-turn);
        rightMotorBack.setPower(-turn);
        leftMotorFront.setPower(turn);
        leftMotorBack.setPower(turn);
    }

    /**
     * If this function is called, turn on the drive motors at the given powers, to make it turn left
     */
    public void leftTurnTeleop(float turn)
    {
        //Turn the left motors backwards and the right motors forward
        rightMotorFront.setPower(turn);
        rightMotorBack.setPower(turn);
        leftMotorFront.setPower(-turn);
        leftMotorBack.setPower(-turn);
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
    public void toggleSpinner(int spinnerMode, double power)
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
     * Shoot the ball for the given time
     * @param time we want the flipper on for
     */
    public void shootBall(long time) throws InterruptedException
    {
        flipper.setPower(1.0); //turn on flipper
        Thread.sleep(time); //stop at the given time
        flipper.setPower(0.0);
    }

    /**
     * Reset encoder readings
     * Sets to mode of turning for degrees specified
     */
    void prepMotorsForAutonomous(){
        //Reset encoders
        leftMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set up the motors run to the given position
        leftMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    /**
     * Sets motors to mode of moving with given velocity
     */
    void prepMotorsForTeleop(){
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

        prepMotorsForAutonomous();

        //Set the target position of the encoders as the input
        leftMotorFront.setTargetPosition(-degrees);
        leftMotorBack.setTargetPosition(-degrees);
        rightMotorFront.setTargetPosition(-degrees);
        rightMotorBack.setTargetPosition(-degrees);


        //Turn on the motors at the given powers
        leftMotorFront.setPower(-power);
        leftMotorBack.setPower(-power);
        rightMotorFront.setPower(-power);
        rightMotorBack.setPower(-power);

        //Empty while loop while motors are moving
        while ((leftMotorFront.isBusy()) && (rightMotorFront.isBusy()) && (leftMotorBack.isBusy()) && (rightMotorBack.isBusy()))
        { }

        //Stop motors and use encoders
        stopDriving();

        prepMotorsForTeleop();

    }

    /**
     * Turn left for the given distance at the given power
     * @param degrees distance
     */
    public void leftTurnAutonomous(float power, int degrees) {
        prepMotorsForAutonomous();

        //Set motor target positions
        //Left motors backwards and right motors forwards gives us a left turn
        leftMotorFront.setTargetPosition(-degrees);
        leftMotorBack.setTargetPosition(-degrees);
        rightMotorFront.setTargetPosition(degrees);
        rightMotorBack.setTargetPosition(degrees);


        //Turn on the motors at the given powers
        //Left motors backwards and right motors forwards gives us a left turn
        leftMotorFront.setPower(-power);
        leftMotorBack.setPower(-power);
        rightMotorFront.setPower(power);
        rightMotorBack.setPower(power);

        //Empty while loop while motors are moving
        while ((leftMotorFront.isBusy()) && (rightMotorFront.isBusy()) && (leftMotorBack.isBusy()) && (rightMotorBack.isBusy())) {
        }

        //Stop motors and use encoders
        stopDriving();
        prepMotorsForTeleop();
    }

    /**
     * Turn right for the given distance at the given power
     * @param degrees distance
     */
    public void rightTurnAutonomous(float power, int degrees)
    {
        prepMotorsForAutonomous();

        //Set motor target positions
        //Right motors backwards and left motors forwards gives us a right turn
        leftMotorFront.setTargetPosition(degrees);
        leftMotorBack.setTargetPosition(degrees);
        rightMotorFront.setTargetPosition(-degrees);
        rightMotorBack.setTargetPosition(-degrees);



        //Turn on the motors at the given powers
        //Right motors backwards and left motors forwards gives us a right turn
        leftMotorFront.setPower(power);
        leftMotorBack.setPower(power);
        rightMotorFront.setPower(-power);
        rightMotorBack.setPower(-power);

        //Empty while loop while motors are moving
        while ((leftMotorFront.isBusy()) && (rightMotorFront.isBusy()) && (leftMotorBack.isBusy()) && (rightMotorBack.isBusy()))
        { }

        //Stop motors and use encoders
        stopDriving();
        prepMotorsForTeleop();
    }

    /**
     * Shift left for the given distance at the given power
     * @param degrees distance
     */
    public void leftShiftAutonomous(float power, int degrees)
    {
        prepMotorsForAutonomous();

        //Set motor target positions
        //This sequence of backwards, forwards, forwards, backwards makes the robot shift left
        leftMotorFront.setTargetPosition(-degrees);
        leftMotorBack.setTargetPosition(degrees);
        rightMotorFront.setTargetPosition(degrees);
        rightMotorBack.setTargetPosition(-degrees);

        //Turn on the motors at the given powers
        //This sequence of backwards, forwards, forwards, backwards makes the robot shift left
        leftMotorFront.setPower(-power);
        leftMotorBack.setPower(power);
        rightMotorFront.setPower(power);
        rightMotorBack.setPower(-power);

        //Empty while loop while motors are moving
        while ((leftMotorFront.isBusy()) && (rightMotorFront.isBusy()) && (leftMotorBack.isBusy()) && (rightMotorBack.isBusy()))
        { }

        //Stop motors and use encoders
        stopDriving();
        prepMotorsForTeleop();
    }

    /**
     * Shift right for the given distance at the given power
     * @param degrees distance
     */
    public void rightShiftAutonomous(float power, int degrees)
    {
        prepMotorsForAutonomous();

        //Set motor target positions
        //This sequence of forwards, backwards, backwards, forwards makes the robot shift right
        leftMotorFront.setTargetPosition(degrees);
        leftMotorBack.setTargetPosition(-degrees);
        rightMotorFront.setTargetPosition(-degrees);
        rightMotorBack.setTargetPosition(degrees);

        //Set up the motors run to the given position
        leftMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Turn on the motors at the given powers
        //This sequence of forwards, backwards, backwards, forwards makes the robot shift right
        leftMotorFront.setPower(power);
        leftMotorBack.setPower(-power);
        rightMotorFront.setPower(-power);
        rightMotorBack.setPower(power);

        //Empty while loop while motors are moving
        while ((leftMotorFront.isBusy()) && (rightMotorFront.isBusy()) && (leftMotorBack.isBusy()) && (rightMotorBack.isBusy()))
        { }

        //Stop motors and use encoders
        stopDriving();
        prepMotorsForTeleop();
    }

    /**
     * @return returns true if the supplied ColorSensor either red or blue.  False otherwise
     */
    public boolean iSeeAColor()
    {
        float[] hsvValues = {0, 0, 0}; //This is an array that stores the hue[0], saturation[1], and value[2], values

        //Convert from RGB to HSV (red-green-blue to hue-saturation-value)
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

        //If it sees neither color, return false
        if (hsvValues[2] == 0)
            {
                return false;
            }
            //Otherwise return true
            return true;
    }

    /**
     * Determines what color the color sensor is seeing
     * @return The string "blue" if we see the color blue, "red" if we see the color red
     */
    public String whatColor()
    {
        float hue; //Define float
        float[] hsvValues = {0, 0, 0}; //This is an array that stores the hue[0], saturation[1], and value[2], values
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues); //Convert from RGB to HSV (red-green-blue to hue-saturation-value)

        hue = hsvValues[0]; //store the first value from the array into hue

        if (hue > 120) //If hue is greater than 120, we are looking at blue, so return blue
        {
            return "blue";
        }
        //Otherwise return red
        return "red";
    }

    /**
     * Drives forward slowly until the target color is seen, then shifts into the beacon to press
     * the button
     * @param color take in our team color
     */
    public void colorSensorAutonomous(String color) throws InterruptedException
    {
        boolean blueTeam = color.equals("blue");

        double power  = blueTeam ? 0.2 : -0.2;
        int findBeaconDistance = blueTeam ? 100 : -100;
        int alignBeaconDistance = blueTeam ? 400 : -200;

        //while we do not see the beacon, drive forward
        while (!iSeeAColor())
        {
            driveAutonomous((float) power, findBeaconDistance);
        }

        //now we see a color, but possibly not the target color
        // while we don't see the target color -> drive forward
        while (!whatColor().equals(color))
        {
            driveAutonomous((float) power, findBeaconDistance);
        }

        //now we see the target color, drive forward a tiny bit so cardboard is aligned
        driveAutonomous((float) power, alignBeaconDistance);

        //robot is aligned to the button for the target color, drive into button to press it
        //No matter what, the robot will always have to right shift
        rightShiftAutonomous((float) 0.2, 800);
    }

    public void iSeeAColorStop(float power, String color)
    {
        while (!iSeeAColor())
        {
            driveTeleop(power);
        }
        stopDriving();
    }

    public void whatColorStop(float power, String color)
    {
        while (!whatColor().equals(color))
        {
            driveTeleop(power);
        }
    }
} //CLOSE CLASS