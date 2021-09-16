package com.dcsl.robots;

import com.dcsl.position.OrientedPosition;

public interface MarsRobot extends MarsObject {

  OrientedPosition getOrientedPosition();
  OrientedPosition moveForward();
  OrientedPosition rotateClockwise();
  OrientedPosition rotateAnticlockwise();

}
