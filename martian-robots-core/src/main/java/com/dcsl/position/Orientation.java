package com.dcsl.position;

import java.util.function.Function;

public enum Orientation {
  NORTH(position -> new FixedPosition(position.getX(), position.getY() + 1)),
  EAST(position -> new FixedPosition(position.getX() + 1, position.getY())),
  SOUTH(position -> new FixedPosition(position.getX(), position.getY() - 1)),
  WEST(position -> new FixedPosition(position.getX() - 1, position.getY()));

  private final Function<Position, Position> movingForwardsFunction;

  Orientation(Function<Position, Position> movingForwardsFunction) {
    this.movingForwardsFunction = movingForwardsFunction;
  }

  public Orientation getNextClockwiseOrientation() {
    return Orientation.values()[(ordinal() + 1) % Orientation.values().length];
  }

  public Orientation getNextAnticlockwiseOrientation() {
    return Orientation.values()[(3 + ordinal()) % Orientation.values().length];
  }

  public Position getPositionInFront(Position position) {
    return movingForwardsFunction.apply(position);
  }
}
