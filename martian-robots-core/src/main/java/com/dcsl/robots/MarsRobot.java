package com.dcsl.robots;

import java.util.Optional;

import com.dcsl.position.OrientedPosition;

public interface MarsRobot extends MarsObject {

  Optional<OrientedPosition> getOrientedPosition();
  Optional<OrientedPosition> moveForward();

}
