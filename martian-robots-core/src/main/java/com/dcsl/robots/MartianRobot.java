package com.dcsl.robots;

import java.util.Optional;

import com.dcsl.position.FixedOrientedPosition;
import com.dcsl.position.FixedPosition;
import com.dcsl.position.OrientedPosition;
import com.dcsl.position.Position;
import com.dcsl.position.grid.Grid;

public class MartianRobot implements MarsRobot {

  private final Grid grid;
  private OrientedPosition position;

  public MartianRobot(Grid grid) {
    this(grid, null);
  }

  public MartianRobot(Grid grid, OrientedPosition startingPosition) {
    this.grid = grid;
    this.position = startingPosition;
  }

  @Override
  public Optional<Position> getPosition() {
    return Optional.ofNullable(position).map(FixedPosition::new);
  }

  @Override
  public Optional<OrientedPosition> getOrientedPosition() {
    return Optional.ofNullable(position).map(FixedOrientedPosition::new);
  }

  @Override
  public Optional<OrientedPosition> moveForward() {
    if (position == null) {
      return Optional.empty();
    }
    OrientedPosition newPosition = position.getPositionInFront();
    this.position = grid.containsPosition(newPosition) ? newPosition : null;
    return Optional.ofNullable(position);
  }

  @Override
  public Optional<OrientedPosition> rotateClockwise() {
    if (position == null) {
      return Optional.empty();
    }
    position = new FixedOrientedPosition(
        position.getX(),
        position.getY(),
        position.getOrientation().getNextClockwiseOrientation()
    );
    return Optional.of(position);
  }

  @Override
  public Optional<OrientedPosition> rotateAnticlockwise() {
    if (position == null) {
      return Optional.empty();
    }
    position = new FixedOrientedPosition(
        position.getX(),
        position.getY(),
        position.getOrientation().getNextAnticlockwiseOrientation()
    );
    return Optional.of(position);
  }
}
