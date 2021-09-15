package com.dcsl.robots;

import java.util.Optional;

import com.dcsl.position.OrientedPosition;
import com.dcsl.position.Position;

public class MartianRobot implements MarsRobot {

  @Override
  public Optional<Position> getPosition() {
    return Optional.empty();
  }

  @Override
  public Optional<OrientedPosition> getOrientedPosition() {
    return Optional.empty();
  }

  @Override
  public Optional<OrientedPosition> moveForward() {
    return Optional.empty();
  }
}
