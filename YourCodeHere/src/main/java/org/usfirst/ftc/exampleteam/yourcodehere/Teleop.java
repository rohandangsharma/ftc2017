//Run from the necessary package
package org.usfirst.ftc.exampleteam.yourcodehere;

//Import necessary items
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import org.swerverobotics.library.SynchronousOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp (name="Legoheads TeleOp ") //Name the class
public class Teleop extends SynchronousOpMode //CLASS START
{
    //Define DC Motors
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;
    DcMotor shooterLeft;
    DcMotor shooterRight;
    DcMotor spinnerTop;
    DcMotor spinnerBottom;

    //Define an int to use as the spinner's mode

    //Define Sensors and the CDI
    ColorSensor colorSensorLeft;
    ColorSensor colorSensorRight;
    ColorSensor colorSensorBottom;
    DeviceInterfaceModule CDI;

    //Define floats to be used as joystick and trigger inputs, as well as a float for maximum power to avoid magic numbers
    float drive;
    float shift;
    float rightTurn;
    float leftTurn;

    int shooterCount = 0;

//***********************************************************************************************************
    //MAIN BELOW
    @Override
    public void main() throws InterruptedException
    {
        //Get references to the DC motors from the hardware map
        leftMotorFront = hardwareMap.dcMotor.get("leftMotorFront");
        rightMotorFront = hardwareMap.dcMotor.get("rightMotorFront");
        leftMotorBack = hardwareMap.dcMotor.get("leftMotorBack");
        rightMotorBack = hardwareMap.dcMotor.get("rightMotorBack");
        spinnerTop = hardwareMap.dcMotor.get("spinnerTop");
        spinnerBottom = hardwareMap.dcMotor.get("spinnerBottom");
        shooterLeft = hardwareMap.dcMotor.get("shooterLeft");
        shooterRight = hardwareMap.dcMotor.get("shooterRight");

        //Get references to the sensors and the CDI from the hardware map
        colorSensorBottom = hardwareMap.colorSensor.get("colorSensorBottom");
        colorSensorLeft = hardwareMap.colorSensor.get("colorSensorLeft");
        colorSensorRight = hardwareMap.colorSensor.get("colorSensorRight");
        CDI = hardwareMap.deviceInterfaceModule.get("CDI");

        //Set up the DriveFunctions class and give it all the necessary components (motors, sensors, CDI)
        DriveFunctions functions = new DriveFunctions(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack, spinnerTop, spinnerBottom, shooterLeft, shooterRight, colorSensorLeft, colorSensorRight, colorSensorBottom, CDI);

        //Set the sensors to the modes that we want, and set their addresses. Also set the directions of the motors
        functions.initializeMotorsAndSensors();

        //Wait for start button to be clicked
        waitForStart();


    //LOOP BELOW
        //While the op mode is active, do anything within the loop
        //Note we use opModeIsActive() as our loop condition because it is an interruptible method.
        while (opModeIsActive()) {
            //If the gamepads are changed
            if (updateGamepads()) {
                //Set float variables as the inputs from the joysticks and the triggers
                drive = -gamepad1.left_stick_y;
                shift = - gamepad1.left_stick_x;
                leftTurn = gamepad1.left_trigger;
                rightTurn = gamepad1.right_trigger;

                //Drive vs Shift on left joystick:
                //Do nothing if joystick is stationary
                if (Math.abs(drive) == Math.abs(shift)) {
                    functions.stopDriving();
                }

                //Shift if pushed more on X than Y
                if (Math.abs(shift) > Math.abs(drive)) {
                    functions.shiftTeleop(shift);
                }

                //Drive if joystick pushed more Y than X
                if (Math.abs(drive) > Math.abs(shift)) {
                    functions.driveTeleop(drive);
                }

                //If the left trigger is pushed, turn left at that power
                if (leftTurn > 0) {
                    functions.leftTurnTeleop(leftTurn);
                }

                //If the right trigger is pushed, turn right at that power
                if (rightTurn > 0) {
                    functions.rightTurnTeleop(rightTurn);
                }

                //If the "a" button on the second gamepad is pressed, start the spinner forward
                if (gamepad2.dpad_down) {
                    functions.spinner(1, (float) 0.75);
                }
                ////If the "x" button on the second gamepad is pressed, start the spinner backwards
                if (gamepad2.dpad_up) {
                    functions.spinner(2, (float) 0.75);
                }

                if (gamepad2.dpad_left || gamepad2.dpad_right) {
                    functions.spinner(0, (float) 0.0);
                }

                //If the "y" button is pressed, shoot the ball
                if (gamepad2.y) {
                    shooterCount+=1;
                    functions.shooter(shooterCount, (float) 0.75);
                }

                //Stop all motors when any bumper is pressed
                if ((gamepad1.right_bumper) || (gamepad2.right_bumper) || (gamepad1.left_bumper) || (gamepad2.left_bumper) || (gamepad1.b) || (gamepad2.b)) {
                    functions.stopEverything();
                 }
            } //Close inside "if" loop

            //Update data
            telemetry.update();
            waitOneFullHardwareCycle();

            // Always call idle() at the bottom of your while(opModeIsActive()) loop
            idle();
        } //Close "while (opModeIsActive())" loop
    } //Close main
} //Close class and end program