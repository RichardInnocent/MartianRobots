package com.dcsl.position;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

/**
 * Describes an orientation, or direction. In this case, only four directions are permitted.
 */
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

  /**
   * Gets the direction 90 degrees clockwise from this one.
   * @return The direction 90 degrees clockwise from this one.
   */
  public Orientation getNextClockwiseOrientation() {
    return values()[(ordinal() + 1) % values().length];
  }

  /**
   * Gets the direction 90 degrees anticlockwise from this one.
   * @return The direction 90 degrees anticlockwise from this one.
   */
  public Orientation getNextAnticlockwiseOrientation() {
    return values()[(3 + ordinal()) % values().length];
  }

  /**
   * Gets the position one unit in front of the provided position, given this orientation.
   * @param position The current position.
   * @return The position one unit in front of the provided position, given this orientation.
   */
  public Position getPositionInFront(Position position) {
    return movingForwardsFunction.apply(position);
  }

  public String getShortName() {
    return shortName;
  }

  /**
   * Retrieves an orientation based on the provided short name.
   * @param shortName The name to search for.
   * @return The orientation if the short name matches an orientation, or an empty {@link Optional}
   * otherwise.
   */
  public static Optional<Orientation> fromShortName(String shortName) {
    return Arrays
        .stream(values())
        .filter(orientation -> orientation.getShortName().equals(shortName))
        .findAny();
  }
}
