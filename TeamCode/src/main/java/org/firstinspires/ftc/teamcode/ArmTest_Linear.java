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
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

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

@TeleOp(name="Arm Test", group="Pushbot")
@Disabled
public class ArmTest_Linear extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareArmtest robot = new HardwareArmtest();   // Use  Arm Test hardware
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
        double arm_test;
        double arm_pos;
        double claw_pos;


        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // left = robot.frontLeft.setPower (0.5) {
        //     robot.backLeft.setPower(0.5)
        left = 0.5;
        right = 0.5;
        stop = 0;
        arm_test = 0;


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Run wheels in POV mode (note: The joystick goes negative when pushed forwards, so negate it)
            // In this mode the Left stick moves the robot fwd and back, the Right stick turns left and right.
            // This way it's also easy to just drive straight, or just turn.
            /*drive = -gamepad1.left_stick_y;
            turn = gamepad1.right_stick_x;


            // Combine drive and turn for blended motion.
            left = drive + turn;
            right = drive - turn;

            // Normalize the values so neither exceed +/- 1.0
            max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0) {
                left /= max;
                right /= max;
            } */


            // Output the safe vales to the motor drives.
          /*  robot.frontRight.setPower(0.5);
            robot.backRight.setPower(0.5);
            robot.frontLeft.setPower(0.5);
            robot.backLeft.setPower(0.5);*/
            // robot.leftDrive.setPower(left);
            // robot.rightDrive.setPower(right);

            /* Use gamepad left & right Bumpers to open and close the claw
            if (gamepad1.right_bumper)
                clawOffset += CLAW_SPEED;
            else if (gamepad1.left_bumper)
                clawOffset -= CLAW_SPEED; */

            // Move both servos to new position.  Assume servos are mirror image of each other.
            /*
            clawOffset = Range.clip(clawOffset, -0.5, 0.5);
            robot.leftClaw.setPosition(robot.MID_SERVO + clawOffset);
            robot.rightClaw.setPosition(robot.MID_SERVO - clawOffset);

            // Use gamepad buttons to move arm up (Y) and down (A)
            /*
            if (gamepad1.y)
                robot.leftArm.setPower(robot.ARM_UP_POWER);
            else if (gamepad1.a)
                robot.leftArm.setPower(robot.ARM_DOWN_POWER);
            else
                robot.leftArm.setPower(0.0);

             */

            arm_pos = robot.arm.getCurrentPosition();
           // claw_pos = robot.claw

            // Send telemetry message to signify robot running;
            telemetry.addData("left", "%.2f", left);
            telemetry.addData("right", "%.2f", right);
            telemetry.addData("left strafing", "%2.5f", left);
            telemetry.addData("right Strafing", "%2.5f", right);
            telemetry.addData("arm", "%2.5f", arm_test);
            telemetry.addData("arm_pos", "%2.5f", arm_pos);
            telemetry.update();


            // Forward Left Stick
            if (gamepad1.left_stick_y > stop) {
                left = 0.5;
                robot.frontLeft.setPower(left);
                robot.backLeft.setPower(left);
            }
            // Forward Right Stick
            if (gamepad1.right_stick_y > stop) {
                right = 0.5;
                robot.frontRight.setPower(-right);
                robot.backRight.setPower(-right);
            }
            //Stop
            if (gamepad1.left_stick_y == stop && gamepad1.left_trigger == 0 && gamepad1.right_trigger == 0) {
                stop = 0;
                robot.frontLeft.setPower(stop);
                robot.backLeft.setPower(stop);
                robot.frontRight.setPower(stop);
                robot.backRight.setPower(stop);
            }

            //  Backward Left Stick
            if (gamepad1.left_stick_y < stop) {
                left = 0.5;
                robot.frontLeft.setPower(-left);
                robot.backLeft.setPower(-left);
            }
            // Backward Right Stick
            if (gamepad1.right_stick_y < stop) {
                right = 0.5;
                robot.frontRight.setPower(right);
                robot.backRight.setPower(right);
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

            //Arm Control

            if (gamepad1.dpad_up && (arm_pos < 9000) ){
                robot.arm.setPower(0.5);
                arm_test = 1;}
            else if (gamepad1.dpad_down && (arm_pos > 0) ){
                robot.arm.setPower(-0.5);
                arm_test = -1;}
            else {
                robot.arm.setPower(0);
                arm_test = 0;}

            //Claw Control with normal Servo
            /*
            if (gamepad1.dpad_left)
                robot.claw.setPosition(1);
            else if (gamepad1.dpad_right)
                robot.claw.setPosition(0);

             */



            //Claw Control with Continuous Rotation Servo
            if (gamepad1.dpad_left)
                robot.claw.setPower(1);
            else if (gamepad1.dpad_right)
                robot.claw.setPower(-1);
            else
                robot.claw.setPower(0);




        }






            // NEVER DOWNLOAD ON PINK PHONE/DRIVER STATION


            // Pace this loop so jaw action is reasonable speed.
            // sleep(50);

            // Varible inside () define, then set power to varible
        }
    }

