package com.dcsl.robots;

import com.dcsl.position.FixedOrientedPosition;
import com.dcsl.position.OrientedPosition;
import com.dcsl.position.Position;

public class MartianRobot implements MarsRobot {

  private OrientedPosition position;

  public MartianRobot(OrientedPosition startingPosition) {
    this.position = startingPosition;
  }

  @Override
  public Position getPosition() {
    return position;
  }

  @Override
  public OrientedPosition getOrientedPosition() {
    return position;
  }

  @Override
  public OrientedPosition moveForward() {
    position = position.getPositionInFront();
    return position;
  }

  @Override
  public OrientedPosition rotateClockwise() {
    position = new FixedOrientedPosition(
        position.getX(),
        position.getY(),
        position.getOrientation().getNextClockwiseOrientation()
    );
    return position;
  }

  @Override
  public OrientedPosition rotateAnticlockwise() {
    position = new FixedOrientedPosition(
        position.getX(),
        position.getY(),
        position.getOrientation().getNextAnticlockwiseOrientation()
    );
    return position;
  }
}
