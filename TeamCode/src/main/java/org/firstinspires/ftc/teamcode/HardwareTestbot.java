/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class HardwareTestbot {


    /* Public OpMode members. */
    public DcMotor frontRight = null;
    public DcMotor frontLeft = null;
    public DcMotor backRight = null;
    public DcMotor backLeft = null;
    public DcMotor slide = null;
    public CRServo rightSpinWheel = null;
    public CRServo leftSpinWheel = null;

    /*public Servo    leftClaw    = null;
    public Servo    rightClaw   = null;

    public static final double MID_SERVO       =  0.5 ;
    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;
    */
    /* local OpMode members. */
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();


    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        frontRight = hwMap.get(DcMotor.class, "frontRight");
        frontLeft = hwMap.get(DcMotor.class, "frontLeft");
        backRight = hwMap.get(DcMotor.class, "backRight");
        backLeft = hwMap.get(DcMotor.class, "backLeft");
        slide = hwMap.get(DcMotor.class, "slide");
        rightSpinWheel = hwMap.get(CRServo.class, "rightSpinWheel");
        leftSpinWheel = hwMap.get(CRServo.class, "leftSpinWheel");
      //  slide = hwMap.get(DcMotor.class, "slide");

        //leftArm    = hwMap.get(DcMotor.class, "left_arm");
        frontRight.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        frontLeft.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        backRight.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        backLeft.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        slide.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightSpinWheel.setDirection(CRServo.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        leftSpinWheel.setDirection(CRServo.Direction.FORWARD); // Set to REVERSE if using AndyMark motors

        // Set all motors to zero power
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
        slide.setPower (0);
        rightSpinWheel.setPower(0);
        leftSpinWheel.setPower(0);


        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Set Encoder to zero
        // upArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // upArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

}