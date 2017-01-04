//Run from the package
package org.usfirst.ftc.exampleteam.yourcodehere;

//Import necessary items
import android.graphics.Color;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;


@Disabled
public class DriveFunctions extends LinearOpMode
{ //START CLASS
    //Define DC motors
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;
    DcMotor spinner;
    DcMotor shooterLeft;
    DcMotor shooterRight;

    //Define sensors and CDI
    ColorSensor colorSensorLeft;
    ColorSensor colorSensorRight;
    ColorSensor colorSensorBottom;
    DeviceInterfaceModule CDI;

    /**
     * Initialize all the harware all the hardware
     * It creates a data type DriveFunctions to store all the hardware devices
     */
    public DriveFunctions(DcMotor leftMotorFront, DcMotor rightMotorFront, DcMotor leftMotorBack, DcMotor rightMotorBack, DcMotor spinner, DcMotor shooterLeft, DcMotor shooterRight, ColorSensor colorSensorLeft, ColorSensor colorSensorRight, ColorSensor colorSensorBottom, DeviceInterfaceModule CDI)
    {
        //These lines enable us to store the motors, sensors and CDI without having to write them over and over again

        //Initialize DC motors
        this.leftMotorFront = leftMotorFront;
        this.leftMotorBack = leftMotorBack;
        this.rightMotorFront = rightMotorFront;
        this.rightMotorBack = rightMotorBack;
        this.spinner = spinner;
        this.shooterLeft = shooterLeft;
        this.shooterRight = shooterRight;

        //Initialize sensors and CDI
        this.colorSensorLeft = colorSensorLeft;
        this.colorSensorRight = colorSensorRight;
        this.colorSensorBottom = colorSensorBottom;
        this.CDI = CDI;
    }

    /**
     * Set sensor addresses, modes and DC motor directions
     */
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
     * Takes in motor powers for 4 drive motors
     */
    public void setDriveMotorPowers(float leftFrontPower, float leftBackPower, float rightFrontPower, float rightBackPower)
    {
        //Use the entered powers and feed them to the motors
        leftMotorFront.setPower(leftFrontPower);
        leftMotorBack.setPower(leftBackPower);
        rightMotorFront.setPower(rightFrontPower);
        rightMotorBack.setPower(rightBackPower);
    }
    /**
     * If this function is called, stop the drive motors
     */
    public void stopDriving()
    {
        setDriveMotorPowers((float) 0.0, (float) 0.0, (float) 0.0, (float) 0.0);
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
        //Send all the motors in the same direction
        setDriveMotorPowers(drive, drive, drive, drive);
    }

    /**
     * If this function is called, turn on the drive motors at the given powers, to make it tank turn right
     */
    public void rightTurnTeleop(float turn)
    {
        //Turn the right motors backwards and the left motors forward so that it turns right
        setDriveMotorPowers(turn, turn, -turn, -turn);
    }

    /**
     * If this function is called, turn on the drive motors at the given powers, to make it tank turn left
     */
    public void leftTurnTeleop(float turn)
    {
        //Turn the left motors backwards and the right motors forward so that it turns left
        setDriveMotorPowers(-turn, -turn, turn, turn);
    }

    /**
     * If this function is called, turn on the drive motors at the given powers, to make it shift in the desired direction
     */
    public void shiftTeleop(float shift)
    {
        //This sequence of backwards, forwards, forwards, backwards makes the robot shift
        setDriveMotorPowers(-shift, shift, shift, -shift);
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
        shooterLeft.setPower(-power);
        shooterRight.setPower(power);
    }

    /**
     * Takes in powers for 4 drive motors, as well as 4 encoder distances
     * //Allows us to run at the entered power, for the entered distance
     */
    public void moveDriveMotorsWithEncoders(int leftFrontDegrees, int leftBackDegrees, int rightFrontDegrees, int rightBackDegrees, float leftFrontPower, float leftBackPower, float rightFrontPower, float rightBackPower)
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
        setDriveMotorPowers(leftFrontPower, leftBackPower, rightFrontPower, rightBackPower);

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

        //Everything in the same direction creates linear driving
        moveDriveMotorsWithEncoders(degrees, degrees, degrees, degrees, power, power, power, power);
    }

    /**
     * Turn left for the given distance at the given power
     * @param degrees distance
     */
    public void leftTurnAutonomous(float power, int degrees)
    {
        //Left motors backwards and right motors forwards gives us a left turn
        moveDriveMotorsWithEncoders(-degrees, -degrees, degrees, degrees, -power, -power, power, power);
    }

    /**
     * Turn right for the given distance at the given power
     * @param degrees distance
     */
    public void rightTurnAutonomous(float power, int degrees)
    {
        //Right motors backwards and left motors forwards gives us a right turn
        moveDriveMotorsWithEncoders(degrees, degrees, -degrees, -degrees, power, power, -power, -power);
    }

    /**
     * Shift left for the given distance at the given power
     * @param degrees distance
     */
    public void leftShiftAutonomous(float power, int degrees)
    {
        //This sequence of backwards, forwards, forwards, backwards makes the robot shift left
        moveDriveMotorsWithEncoders(-degrees, degrees, degrees, -degrees, -power, power, power, -power);
    }

    /**
     * Shift right for the given distance at the given power
     * @param degrees distance
     */
    public void rightShiftAutonomous(float power, int degrees)
    {
        //This sequence of forwards, backwards, backwards, forwards makes the robot shift right
        moveDriveMotorsWithEncoders(degrees, -degrees, -degrees, degrees, power, -power, -power, power);
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
        //Define float for hue
        float hue;

        //This is an array that stores the hue[0], saturation[1], and value[2], values
        float[] hsvValues = {0, 0, 0};

        //Convert from RGB to HSV (red-green-blue to hue-saturation-value)
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

        //store the first value from the array into hue
        hue = hsvValues[0];

        //If hue is greater than 120, we are looking at blue, so return blue
        if (hue > 120)
        {
            return "Blue";
        }

        //Otherwise return red
        return "Red";
    }

    /**
     * Drives forward slowly until the target color is seen, then shifts into the beacon to press
     * the button in autonomous
     * @param color take in our team color
     * @param colorSensor take in the correct color sensor
     */
    public void beaconColorCheckAutonomous(String color, ColorSensor colorSensor)
    {
        //Define some constants to use and avoid magic numbers
        float drivePower = (float) 0.2;
        float shiftPower = (float) 0.4;
        int findBeaconDistance = 100;
        int alignBeaconDistance = 400;
        int shiftDistance = 800;

        //While we do not see the beacon, drive forward
        while (!iSeeAColor(colorSensor))
        {
            driveAutonomous(drivePower, findBeaconDistance);
        }

        //Now we see a color, but possibly not the target color
        //While we don't see the target color, drive forward
        while (!whatColor(colorSensor).equals(color))
        {
            driveAutonomous(drivePower, findBeaconDistance);
        }

        //Now we see the target color, drive forward a tiny bit so cardboard is aligned
        driveAutonomous(drivePower, alignBeaconDistance);

        //The robot is aligned to the button of the target color, shift into the button to press it
        if (color.equals("Blue"))
        {
            rightShiftAutonomous(shiftPower, shiftDistance);
        }

        if (color.equals("Red"))
        {
            leftShiftAutonomous(shiftPower, shiftDistance);
        }
    }

    /**
     * Drives forward slowly until the target color is seen, then shifts into the beacon to press
     * the button of the o
     * @param color take in our the other teams color, as that is the color that needs to be pressed
     * @param colorSensor take in the correct color sensor
     */
    public void beaconColorCheckTeleOp(String color, ColorSensor colorSensor)
    {
        //Define some constants to use and avoid magic numbers
        float drivePower = (float) 0.2;
        float shiftPower = (float) 0.4;
        int findBeaconDistance = 100;
        int alignBeaconDistance = 400;
        int shiftDistance = 800;

        //While we do not see the beacon, drive forward
        while (!iSeeAColor(colorSensor))
        {
            driveAutonomous(drivePower, findBeaconDistance);
        }

        //Now we see a color, but possibly not the target color
        //While we don't see the target color, drive forward
        while (!whatColor(colorSensor).equals(color))
        {
            driveAutonomous(drivePower, findBeaconDistance);
        }

        //Now we see the target color, drive forward a tiny bit so cardboard is aligned
        driveAutonomous(drivePower, alignBeaconDistance);

        //The robot is aligned to the button of the target color, shift into the button to press it
        if (color.equals("Blue"))
        {
            leftShiftAutonomous(shiftPower, shiftDistance);
        }

        if (color.equals("Red"))
        {
            rightShiftAutonomous(shiftPower, shiftDistance);
        }
    }
    /**
     * Stops on the white line
     */
    public void whiteLineStop(float drivePower)
    {
        //Switch the direction that is entered to make it correct
        //Use the alpha value, because it measures luminosity, and the white line has a much higher luminosity compared to the mat
        //If the alpha value is less than 20, drive forward
        //The alpha value of the mat is close to zero, and the alpha value of the line is above 50
        //This means that if the below condition is true, we are not on the line
        while (colorSensorLeft.alpha() < 20) {
            driveTeleop(-drivePower);

        }

        driveAutonomous(-drivePower * 2, -400);

        //When the above condition is no longer true, stop the robot, after which it will be on the line
        stopDriving();
    }

    /**
     * FOUR CASES
     * @param direction
     * @param color
     */
    public void sameSideBeacons(String direction, String color)
    {

        float power = (float) 0.5;

        if (color.equals("Red"))
        {
            if (direction.equals("Forward"))
            {
                //Beacon 1 to beacon 2 blue team, or beacon 3 to beacon 4 blue team
                //Come off beacon
                leftShiftAutonomous(power, 300);

                //Align with second beacon
                driveAutonomous(power, 4400);

                //If we see the color, shift and hit the beacon
                beaconColorCheckTeleOp(color, colorSensorRight);
            }

            if (direction.equals("Backward"))
            {
                //Beacon 2 to beacon 1 blue team, or beacon 4 to beacon 3 blue team
                //Come off beacon
                leftShiftAutonomous(power, 300);

                //Align with second beacon
                driveAutonomous(-power, -5000);

                //If we see the color, shift and hit the beacon
                beaconColorCheckTeleOp(color, colorSensorRight);
            }
        }

        if (color.equals("Blue"))
        {
            if (direction.equals("Forward"))
            {
                //Beacon 1 to beacon 2 red team, or beacon 3 to beacon 4 red team
                //Come off beacon
                rightShiftAutonomous(power, 300);

                //Align with second beacon
                driveAutonomous(power, 4400);

                //If we see the color, shift and hit the beacon
                beaconColorCheckTeleOp(color, colorSensorLeft);
            }

            if (direction.equals("Backward"))
            {
                //Beacon 2 to beacon 1 red team, or beacon 4 to beacon 3 red team
                //Come off beacon
                leftShiftAutonomous(power, 300);

                //Align with second beacon
                driveAutonomous(-power, -5000);

                //If we see the color, shift and hit the beacon
                beaconColorCheckTeleOp(color, colorSensorLeft);
            }
        }
    }

    /**
     * TWO CASES
     * @param direction
     * @param color
     */
    public void oddBeacons(String direction, String color)
    {
        float power = (float) 0.5;

        if (color.equals("Red"))
        {
            if (direction.equals("Forward"))
            {
                //Beacon 1 to beacon 3 blue team
                leftShiftAutonomous(power, 500);

                leftTurnAutonomous(power, 2400);

                rightShiftAutonomous(power, 5000);

                beaconColorCheckTeleOp(color, colorSensorRight);
            }

            if (direction.equals("Backward"))
            {
                //Beacon 3 to beacon 1 blue team
                leftShiftAutonomous(power, 500);

                rightTurnAutonomous(power, 2400);

                driveAutonomous(-power, -5000);

                beaconColorCheckTeleOp(color, colorSensorRight);
            }
        }

        if (color.equals("Blue"))
        {
            if (direction.equals("Forward"))
            {
                //Beacon 1 to beacon 3 red team
                rightShiftAutonomous(power, 1000);

                rightTurnAutonomous(power, 2400);

                rightShiftAutonomous(power, 5000);

                beaconColorCheckTeleOp(color, colorSensorLeft);
            }

            if (direction.equals("Backward"))
            {
                //Beacon 3 to beacon 1 red team
                leftShiftAutonomous(power, 500);

                rightTurnAutonomous(power, 2400);

                rightShiftAutonomous(power, 1000);

                beaconColorCheckTeleOp(color, colorSensorLeft);
            }
        }

    }

    /**
     * TWO CASES
     * @param direction
     * @param color
     */
    public void evenBeacons(String direction, String color)
    {
        float power = (float) 0.5;

        if (color.equals("Red"))
        {
            if (direction.equals("Forward"))
            {
                //Beacon 2 to beacon 4 blue team
                leftShiftAutonomous(power, 5000);

                leftTurnAutonomous(power, 2400);

                rightShiftAutonomous(power, 700);

                beaconColorCheckTeleOp(color, colorSensorRight);
            }

            if (direction.equals("Backward"))
            {
                //Beacon 4 to beacon 2 blue team
                leftShiftAutonomous(power, 2500);

                rightTurnAutonomous(power, 2400);

                rightShiftAutonomous(power, 5000);

                beaconColorCheckTeleOp(color, colorSensorRight);
            }
        }

        if (color.equals("Blue"))
        {
            if (direction.equals("Forward"))
            {
                //Beacon 2 to beacon 4 red team
                rightShiftAutonomous(power, 5000);

                rightTurnAutonomous(power, 2400);

                rightShiftAutonomous(power, 500);

                beaconColorCheckTeleOp(color, colorSensorLeft);
            }

            if (direction.equals("Backward"))
            {
                //Beacon 4 to beacon 2 red team
                rightShiftAutonomous(power, 2000);

                leftTurnAutonomous(power, 2400);

                rightShiftAutonomous(power, 5000);

                beaconColorCheckTeleOp(color, colorSensorLeft);
            }
        }
    }

    public void closeCrossBeacons(String direction, String color)
    {
        float power = (float) 0.5;

        if (color.equals("Red"))
        {
            if (direction.equals("Forward"))
            {
                //Beacon 2 to beacon 3 blue team
                leftShiftAutonomous(power, 2500);

                //Turn to third beacon
                leftTurnAutonomous(power, 2400);

                //Align on wall
                rightShiftAutonomous(power, 3300);

                //Go to third beacon
                driveAutonomous(power, 1000);

                //If we see the color (in this case, "blue") shift and hit the beacon
                beaconColorCheckTeleOp(color, colorSensorRight);
            }

            if (direction.equals("Backward"))
            {
                //Beacon 4 to beacon 2 blue team
                leftShiftAutonomous(power, 2500);

                rightTurnAutonomous(power, 2400);

                rightShiftAutonomous(power, 700);

                beaconColorCheckTeleOp(color, colorSensorRight);
            }
        }

        if (color.equals("Blue"))
        {
            if (direction.equals("Forward"))
            {
                //Beacon 2 to beacon 3 red team
                rightShiftAutonomous(power, 5000);

                rightTurnAutonomous(power, 2400);

                rightShiftAutonomous(power, 500);

                beaconColorCheckTeleOp(color, colorSensorLeft);
            }

            if (direction.equals("Backward"))
            {
                //Beacon 4 to beacon 2 red team
                rightShiftAutonomous(power, 2000);

                leftTurnAutonomous(power, 2400);

                rightShiftAutonomous(power, 5000);

                beaconColorCheckTeleOp(color, colorSensorLeft);
            }
        }
    }

    public int whatStartBeacon() {
    //Default currentBecaon = 1
    int startBeacon = 1;

    //Set current beacon number by dPad
    if (gamepad1.dpad_left) {
        startBeacon = 1;
    }

    if (gamepad1.dpad_up){
        startBeacon = 2;
    }

    if (gamepad1.dpad_right) {
        startBeacon = 3;
    }

    if (gamepad1.dpad_down) {
        startBeacon = 4;
    }

    return startBeacon;
}


    public int whatEndBeacon() {
        int endBeacon = 1;

        if (gamepad1.x) {
            endBeacon = 1;
        }

        if (gamepad1.y){
            endBeacon = 2;
        }

        if (gamepad1.b) {
            endBeacon = 3;
        }

        if (gamepad1.a) {
            endBeacon = 4;
        }

        return endBeacon;
    }


    public void gotToBeacon(int startBeacon, int endBeacon){

    }


    //Empty main
    @Override
    public void runOpMode ( ) throws InterruptedException
    {

    }
} //CLOSE CLASS

