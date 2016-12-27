//This is the main remote control program for FTC Velocity Vortex 2016
//Authors: FTC team [11212??], The Lexington Legoheads
//In case of questions email anupendra@gmail.com (coach)
//**********************************************************************************************************
//Run from the necessary package
        package org.usfirst.ftc.exampleteam.yourcodehere;

        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
        import org.swerverobotics.library.SynchronousOpMode;
        import com.qualcomm.robotcore.hardware.ColorSensor;

        import static org.usfirst.ftc.exampleteam.yourcodehere.DriveFunctions.*;


@TeleOp(name="teleOp Working") //Name the class
public class remoteControl extends SynchronousOpMode //CLASS START
{
    //Define DC Motors
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;
    DcMotor spinner;
    DcMotor shooterLeft;
    DcMotor shooterRight;

    int aPressCount = 0;

    //Define Sensors
    ColorSensor colorSensor;
    DeviceInterfaceModule CDI;


    //Define floats to be used as joystick and trigger inputs
    float drive;
    float shift;
    float rightTurn;
    float leftTurn;

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
        spinner = hardwareMap.dcMotor.get("spinner");
        shooterLeft = hardwareMap.dcMotor.get("shooterLeft");
        shooterRight = hardwareMap.dcMotor.get("shooterRight");


        //Get references to the sensors from the hardware map
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        CDI = hardwareMap.deviceInterfaceModule.get("CDI");
        DriveFunctions functions = new DriveFunctions(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack, colorSensor, spinner, shooterLeft, shooterRight);

        //Reverse the right motors since it is facing the opposite direction as the left motor
        leftMotorFront.setDirection(DcMotor.Direction.REVERSE);
        rightMotorBack.setDirection(DcMotor.Direction.REVERSE);
//***********************************************************************************************************
//LOOP BELOW
        waitForStart();
        //Open loops
        while (opModeIsActive()) {
            if (updateGamepads()) {
                //Set float variables as the inputs from the joystick and the dpad
                //The negative sign is necessary because pushing the joystick up normally sends the robot backward
                //Additionally, set float variables as the input from the triggers
                drive = gamepad1.left_stick_y;
                shift = -gamepad1.left_stick_x;
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

                //Access turn DriveFunctions from function class
                if (leftTurn > 0) {
                    functions.leftTurnTeleop(leftTurn);
                }
                if (rightTurn > 0) {
                    functions.rightTurnTeleop(rightTurn);
                }

                // "a" button starts spinner
                if (gamepad2.a) {
                    aPressCount = 2;
                    functions.toggleSpinner(aPressCount, (float) 1.0);
                }
                if (gamepad2.x) {
                    aPressCount = 1;
                    functions.toggleSpinner(aPressCount, (float) 1.0);
                }
                //Stop all motors when "b" is pressed
                if ((gamepad1.b) || (gamepad2.b)) {
                    aPressCount = 0;
                    functions.toggleSpinner(aPressCount, (float) 1.0);
                    functions.stopEverything();
                }
                if (gamepad2.y) {
                    shooterRight.setPower(-0.5);
                    shooterLeft.setPower(0.5);
                }

            } //Close inside "if" loop
            telemetry.update();
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        } //Close outside loop
    } //Close main
} //Close class and end program