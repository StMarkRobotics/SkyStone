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

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This file illustrates the concept of driving a path based on time.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code assumes that you do NOT have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByEncoder;
 *
 *   The desired path in this example is:
 *   - Drive forward for 3 seconds
 *   - Spin right for 1.3 seconds
 *   - Drive Backwards for 1 Second
 *   - Stop and close the claw.
 *
 *  The code is written in a simple form with no optimizations.
 *  However, there are several ways that this type of sequence could be streamlined,
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Red Line", group="Pushbot")
//@Disabled
public class Autonomous_RedLine extends LinearOpMode {
    OpticalDistanceSensor distanceSensor;  // Hardware Device Object
    /* Declare OpMode members. */
    HardwareTestbot robot   = new HardwareTestbot();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();


    static final double     FORWARD_SPEED = 0.6;
    static final double     TURN_SPEED    = 0.5;

    @Override
    public void runOpMode() {
        // get a reference to our Light Sensor object.
        distanceSensor = hardwareMap.get(OpticalDistanceSensor.class, "distanceSensor");

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to a start (driver presses PLAY)
        waitForStart();
            // send the info back to driver station using telemetry function.
            telemetry.addData("Don't use this number: ",    distanceSensor.getRawLightDetected());
            telemetry.addData("Use this one: ", distanceSensor.getLightDetected());
            telemetry.update();


        //Step 1: Strafe
            robot.frontRight.setPower(-FORWARD_SPEED);
            robot.backRight.setPower(FORWARD_SPEED);
            robot.frontLeft.setPower(-FORWARD_SPEED);
            robot.backLeft.setPower(FORWARD_SPEED);
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 3.5)) {
                telemetry.addData("Path", "Step 1: %2.5f S Elapsed", runtime.seconds());
                telemetry.update();
        }

        //Step 2: Move backwards
            robot.frontRight.setPower(-FORWARD_SPEED);
            robot.backRight.setPower(-FORWARD_SPEED);
            robot.frontLeft.setPower(FORWARD_SPEED);
            robot.backLeft.setPower(FORWARD_SPEED);
            while (opModeIsActive() && (runtime.seconds() < 0.3)) {
                telemetry.addData("Path", "Step 2: %2.5f S Elapsed", runtime.seconds());
                telemetry.update();
            }



        // Step 3: Turn to change the position of the do hiya to the red thingy
        robot.frontRight.setPower(-FORWARD_SPEED);
        robot.backRight.setPower(-FORWARD_SPEED);
        robot.frontLeft.setPower(-FORWARD_SPEED);
        robot.backLeft.setPower(-FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.55)) {
            telemetry.addData("Path", "Step 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        //Step 4: Strafe a bit more.
        robot.frontRight.setPower(-FORWARD_SPEED);
        robot.backRight.setPower(FORWARD_SPEED);
        robot.frontLeft.setPower(-FORWARD_SPEED);
        robot.backLeft.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.6)) {
            telemetry.addData("Path", "Step 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }


        // Step 5: Turn to change the position of the do hiya to the red thingy
        robot.frontRight.setPower(-FORWARD_SPEED);
        robot.backRight.setPower(-FORWARD_SPEED);
        robot.frontLeft.setPower(-FORWARD_SPEED);
        robot.backLeft.setPower(-FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.1)) {
            telemetry.addData("Path", "Step 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();

        }


        //Step 6: Move backwards.
        robot.frontRight.setPower(-FORWARD_SPEED);
        robot.backRight.setPower(-FORWARD_SPEED);
        robot.frontLeft.setPower(FORWARD_SPEED);
        robot.backLeft.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.45)) {
            telemetry.addData("Path", "Step 4: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 5: Turn to change the position of the do hiya to the red thingy
        robot.frontRight.setPower(-FORWARD_SPEED);
        robot.backRight.setPower(-FORWARD_SPEED);
        robot.frontLeft.setPower(-FORWARD_SPEED);
        robot.backLeft.setPower(-FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.1)) {
            telemetry.addData("Path", "Step 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        //Step 6: Move backwards.
        robot.frontRight.setPower(-FORWARD_SPEED);
        robot.backRight.setPower(-FORWARD_SPEED);
        robot.frontLeft.setPower(FORWARD_SPEED);
        robot.backLeft.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.45)) {
            telemetry.addData("Path", "Step 4: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);


    }
}
