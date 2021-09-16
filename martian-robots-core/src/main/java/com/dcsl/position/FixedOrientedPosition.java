package com.dcsl.position;

import java.util.Objects;

/**
 * An immutable implementation of an {@link OrientedPosition}.
 */
public final class FixedOrientedPosition implements OrientedPosition {

  private final int x;
  private final int y;
  private final Orientation orientation;

  public FixedOrientedPosition(OrientedPosition position) {
    this(position.getX(), position.getY(), position.getOrientation());
  }

  public FixedOrientedPosition(Position position, Orientation orientation) {
    this(position.getX(), position.getY(), orientation);
  }

  public FixedOrientedPosition(int x, int y, Orientation orientation) {
    this.x = x;
    this.y = y;
    this.orientation = orientation;
  }

  @Override
  public Orientation getOrientation() {
    return orientation;
  }

  @Override
  public OrientedPosition getPositionInFront() {
    return new FixedOrientedPosition(
        orientation.getPositionInFront(new FixedPosition(x, y)),
        orientation
    );
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FixedOrientedPosition)) {
      return false;
    }
    FixedOrientedPosition that = (FixedOrientedPosition) o;
    return x == that.x && y == that.y && orientation == that.orientation;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, orientation);
  }
}
