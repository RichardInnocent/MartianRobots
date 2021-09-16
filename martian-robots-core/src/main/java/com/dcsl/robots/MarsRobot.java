package com.dcsl.robots;

import com.dcsl.position.OrientedPosition;

/**
 * A robot on Mars. This is an object that can be instructed to move and rotate.
 */
public interface MarsRobot extends MarsObject {

  /**
   * Gets the robot's current position.
   * @return The robot's current position.
   */
  OrientedPosition getOrientedPosition();

  /**
   * Moves the robot forward one unit, based on its current orientation.
   * @return The new position.
   */
  OrientedPosition moveForward();

  /**
   * Rotates the robot 90 degrees clockwise.
   * @return The new position.
   */
  OrientedPosition rotateClockwise();

  /**
   * Rotates the robot 90 degrees anticlockwise.
   * @return The new position.
   */
  OrientedPosition rotateAnticlockwise();

}
