package com.dcsl.robots;

import com.dcsl.position.FixedOrientedPosition;
import com.dcsl.position.OrientedPosition;
import com.dcsl.position.Position;

/**
 * An implementation of the {@link MarsRobot}.
 */
public class MarsRobotImpl implements MarsRobot {

  private OrientedPosition position;

  public MarsRobotImpl(OrientedPosition startingPosition) {
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
