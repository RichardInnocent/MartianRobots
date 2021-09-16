package com.dcsl.position;

import java.util.function.Function;

public enum Orientation {
  NORTH("N", position -> new FixedPosition(position.getX(), position.getY() + 1)),
  EAST("E", position -> new FixedPosition(position.getX() + 1, position.getY())),
  SOUTH("S", position -> new FixedPosition(position.getX(), position.getY() - 1)),
  WEST("W", position -> new FixedPosition(position.getX() - 1, position.getY()));

  private final String shortName;
  private final Function<Position, Position> movingForwardsFunction;

  Orientation(String shortName, Function<Position, Position> movingForwardsFunction) {
    this.shortName = shortName;
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

  public String getShortName() {
    return shortName;
  }
}
