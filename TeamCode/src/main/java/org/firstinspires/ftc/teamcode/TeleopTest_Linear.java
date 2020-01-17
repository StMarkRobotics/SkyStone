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


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {


            // Send telemetry message to signify robot running;
            telemetry.addData("left", "%.2f", left);
            telemetry.addData("right", "%.2f", right);
            telemetry.addData("left strafing", "%2.5f", left);
            telemetry.addData("right Strafing", "%2.5f", right);
            telemetry.addData("encoder", "%.2f", encoder);
            telemetry.addData("side", "%.2f", side);
            telemetry.update();

            encoder = robot.upArm.getCurrentPosition();


            // Forward Left Stick
            if (gamepad1.left_stick_y > stop) {
                left = gamepad1.left_stick_y;
                robot.frontLeft.setPower(left);
                robot.backLeft.setPower(left);
            }
            // Forward Right Stick
            if (gamepad1.right_stick_y > stop) {
                right = gamepad1.right_stick_y;
                robot.frontRight.setPower(-right);
                robot.backRight.setPower(-right);
            }
            //Stop
            if (gamepad1.left_stick_y == stop && gamepad1.right_stick_y == stop && gamepad1.left_trigger == stop && gamepad1.right_trigger == stop) {
                stop = 0;
                robot.frontLeft.setPower(stop);
                robot.backLeft.setPower(stop);
                robot.frontRight.setPower(stop);
                robot.backRight.setPower(stop);

            }

            //  Backward Left Stick
            if (gamepad1.left_stick_y < stop) {
                left = gamepad1.left_stick_y;
                robot.frontLeft.setPower(left);
                robot.backLeft.setPower(left);
            }
            // Backward Right Stick
            if (gamepad1.right_stick_y < stop) {
                right = gamepad1.right_stick_y;
                robot.frontRight.setPower(-right);
                robot.backRight.setPower(-right);
            }
            // Left Strafe
            if (gamepad1.left_trigger > 0) {
                left = 0.5;
                robot.frontLeft.setPower(left);
                robot.backLeft.setPower(-left);
                robot.frontRight.setPower(left);
                robot.backRight.setPower(-left);
            }


            // Right Strafe
            if (gamepad1.right_trigger > 0) {
                right = 0.5;
                robot.frontLeft.setPower(-right);
                robot.backLeft.setPower(right);
                robot.frontRight.setPower(-right);
                robot.backRight.setPower(right);
            }

            // Arm Up
            if (gamepad2.left_stick_y > 0 /*&& encoder > -10500 */) {
                up = 0.7;
                robot.upArm.setPower(up); }

            else if (gamepad2.left_stick_y < 0 /*&& encoder > 0 */)  {
                up = -0.7;
                robot.upArm.setPower(up);
            }
            else {
                up = 0;
                robot.upArm.setPower(up);
            }


            // Arm Horizontal
            if (gamepad2.right_stick_y > 0) {
                side = 0.7;
                robot.horizonArm.setPower(side);
            } else if (gamepad2.right_stick_y < 0) {
                side = -0.7;
                robot.horizonArm.setPower(side);
            } else {
                side = 0;
                robot.horizonArm.setPower(side);
            }


        }

        // NEVER DOWNLOAD ON PINK PHONE/DRIVER STATION


    }

}