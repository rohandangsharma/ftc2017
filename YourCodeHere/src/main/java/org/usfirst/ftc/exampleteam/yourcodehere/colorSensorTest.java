
//This is the main autonomous program with encoders for FTC Velocity Vortex 2016
//Authors: FTC team [11212??], The Lexington Legoheads
//In case of questions email anupendra@gmail.com (coach)
//**********************************************************************************************************
//Run from the necessary package
package org.usfirst.ftc.exampleteam.yourcodehere;

//Import necessary items

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import org.swerverobotics.library.SynchronousOpMode;


@Autonomous(name="Color Sensor ") //Name the class
public class colorSensorTest extends SynchronousOpMode {
    //Define DC Motors
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;
    DcMotor spinner;
    DcMotor flipper;
    int a = 1;
    float color;

    //Define Sensors
    ColorSensor colorSensor;
    DeviceInterfaceModule CDI;

    //***********************************************************************************************************
//MAIN BELOW
    @Override
    public void main() throws InterruptedException {
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

        while (a == 1)
        {
            color = functions.colorCheck(colorSensor);
            if (color > 5)
            {
                leftMotorBack.setPower(1.0);
                rightMotorBack.setPower(1.0);
                Thread.sleep(1000);
            }
            if (color < 5)
            {
                leftMotorBack.setPower(-1.0);
                rightMotorBack.setPower(-1.0);
            }
        }
    }
}