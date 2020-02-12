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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a POV Game style Teleop for a PushBot
 * In this mode the left stick moves the robot FWD and back, the Right stick turns left and right.
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Driver Code", group="Pushbot")
//@Disabled
public class TeleopTest_Linear extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareTestbot robot = new HardwareTestbot();   // Use  Testbot's hardware
    /* double clawOffset = 0;                       // Servo mid position
    final double CLAW_SPEED = 0.02;   */                // sets rate to move servo

    @Override
    public void runOpMode() {
        /* double drive;
        double turn;
        double max; */
        double stop;
        double left;
        double right;
        double up;
        double encoder;
        double side;
        double leftstick;
        double rightstick;
        double left_out;
        double right_in;



        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        left = 0.5;
        right = 0.5;
        stop = 0;
        up = 0.5;
        encoder = 0;
        side = 0.5;
        leftstick = 0;
        rightstick = 0;
        left_out = 0.5;
        right_in = 0.5;


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {


            // Send telemetry message to signify robot running;
            telemetry.addData("left", "%.2f", left);
            telemetry.addData("right", "%.2f", right);
            telemetry.addData("left strafing", "%2.5f", left);
            telemetry.addData("right Strafing", "%2.5f", right);
            telemetry.addData("encoder", "%.2f", encoder);
            // telemetry.addData("arm pivot", "%.2f", up);
            telemetry.addData("stop", "%2.5f", stop);
            telemetry.addData("left_out", "%2.5f", left_out);
            telemetry.addData("right_in", "%2.5f", right_in);
            telemetry.addData("leftstick", "%.2f", leftstick);
            telemetry.addData("rightstick", "%.2f", rightstick);
            telemetry.update();

            // encoder = robot.upArm.getCurrentPosition();
            leftstick = gamepad1.left_stick_y;
            rightstick = gamepad1.right_stick_y;

            //you could simplify the forward and back stick if statements...
            //Do you really need 4 if statements, do you need if statements at all?


            // Left Side
            if (gamepad1.right_trigger == 0 && gamepad1.left_trigger == 0){
                robot.frontLeft.setPower(leftstick);
                 robot.backLeft.setPower(leftstick); }


            // Right Side
            if (gamepad1.right_trigger == 0 && gamepad1.left_trigger == 0) {
                robot.frontRight.setPower(-rightstick);
                robot.backRight.setPower(-rightstick); }


            // You could make the strafe a nested if - else if - else statement
            // Would this also allow you to get rid of the "stop" if statement?

            //Strafe
            if (gamepad1.left_trigger > 0) {
                left = 0.5;
                robot.frontLeft.setPower(left);
                robot.backLeft.setPower(-left);
                robot.frontRight.setPower(left);
                robot.backRight.setPower(-left);
            }

            else if (gamepad1.right_trigger > 0) {
                right = 0.5;
                robot.frontLeft.setPower(-right);
                robot.backLeft.setPower(right);
                robot.frontRight.setPower(-right);
                robot.backRight.setPower(right);
            }

            else if (gamepad1.right_trigger == 0 && gamepad1.left_trigger == 0 && gamepad1.left_stick_y == 0 && gamepad1.right_stick_y  == 0) {
                robot.frontLeft.setPower(0);
                robot.backLeft.setPower(0);
                robot.frontRight.setPower(0);
                robot.backRight.setPower(0);
            }

            // You could make the wheel spin a nested if - else if - else statement
            // Would this also allow you to get rid of the "stop" if statement?

            // Wheel Spin
            if (gamepad2.left_trigger > 0) {
                left_out = 1;
                robot.leftSpinWheel.setPower(left_out);
                robot.rightSpinWheel.setPower(-left_out);
            }

            else if (gamepad2.right_trigger > 0) {
                right_in = 1;
                robot.leftSpinWheel.setPower(-right_in);
                robot.rightSpinWheel.setPower(right_in);
            }

            else {
                robot.leftSpinWheel.setPower(0);
                robot.rightSpinWheel.setPower(0);
            }

            // Encoder
            encoder = robot.slide.getCurrentPosition();


            // Arm Pivot
            if (gamepad2.dpad_down && encoder < 1230) {
                up = 0.3;
                robot.slide.setPower(up);
            }

            else if (gamepad2.dpad_up && encoder > 0) {
                up = -0.3;
                robot.slide.setPower(up);

            }

            else {
                up = 0;
                robot.slide.setPower(up);
            }


        }

        // NEVER DOWNLOAD ON PINK PHONE/DRIVER STATION


    }

}





