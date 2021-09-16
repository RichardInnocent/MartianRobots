package com.dcsl.position;

import java.util.Arrays;
import java.util.Optional;
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
    return values()[(ordinal() + 1) % values().length];
  }

  public Orientation getNextAnticlockwiseOrientation() {
    return values()[(3 + ordinal()) % values().length];
  }

  public Position getPositionInFront(Position position) {
    return movingForwardsFunction.apply(position);
  }

  public String getShortName() {
    return shortName;
  }

  public static Optional<Orientation> fromShortName(String shortName) {
    return Arrays
        .stream(values())
        .filter(orientation -> orientation.getShortName().equals(shortName))
        .findAny();
  }
}
