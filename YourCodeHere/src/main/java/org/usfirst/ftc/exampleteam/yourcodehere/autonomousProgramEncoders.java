
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
    DcMotor flipper;
    DcMotor spinner;

    //Define Sensors
    ColorSensor colorSensor;
    DeviceInterfaceModule CDI;


//    //Define servo motor door positions
//    final double CLOSED_DOOR_POSITION = 0.4;
//    final double OPEN_DOOR_POSITION = 1.2;

//**********************************************************************************************************
//METHODS BELOW

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
        flipper = hardwareMap.dcMotor.get("flipper");

        //Get references to the sensors from the hardware map
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        CDI = hardwareMap.deviceInterfaceModule.get("CDI");

        //Reverse the right motors since it is facing the opposite direction as the left motor
        rightMotorFront.setDirection(DcMotor.Direction.REVERSE);
        rightMotorBack.setDirection(DcMotor.Direction.REVERSE);

        //*************************************************************************************************************
//AUTONOMOUS CODE BELOW

        functions.driveForwardAutonomous((float) 1.0, 5000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        functions.rightTurnAutonomous((float) 1.0, 2000,leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        functions.driveForwardAutonomous((float) 1.0, 5000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        functions.leftTurnAutonomous((float) 1.0, 2000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        functions.rightShiftAutonomous((float) 1.0, 1500, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        functions.leftShiftAutonomous((float) 1.0, 1500, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        functions.driveForwardAutonomous((float) 1.0, 3000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        functions.rightShiftAutonomous((float) 1.0, 1500, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        functions.leftShiftAutonomous((float) 1.0, 1500, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        functions.leftTurnAutonomous((float) 1.0, 3000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);
        functions.driveForwardAutonomous((float) 1.0, 6000, leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack);


//Color Sensor (Not Working):

//        colorSensor.enableLed(true);
//        float hsvValues[] = {0, 0, 0};
//        //calculate hue
//        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);
//
//        //display values
//        telemetry.addData("2 Clear", colorSensor.alpha());
//        telemetry.addData("3 Red  ", colorSensor.red());
//        telemetry.addData("4 Green", colorSensor.green());
//        telemetry.addData("5 Blue ", colorSensor.blue());
//        telemetry.addData("6 Hue", hsvValues[0]);
//
//        //illuminate the RED/BLUE LED on the Core Device Interface if the RED/BLUE value is greatest
//        if (colorSensor.red() > colorSensor.blue() && colorSensor.red() > colorSensor.green()) {
//            CDI.setLED(1, true);
//            CDI.setLED(0, false);
//        }
//        else if (colorSensor.blue() > colorSensor.red() && colorSensor.blue() > colorSensor.green()) {
//            CDI.setLED(1, false);
//            CDI.setLED(0, true);
//        }
//        else {
//            CDI.setLED(1, false);
//            CDI.setLED(0, false);
//        }
//
//        waitOneFullHardwareCycle();
//
    }//Close Main
}// Close Class