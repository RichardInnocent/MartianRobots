package com.dcsl.position;

public class FixedOrientedPosition implements OrientedPosition {

  private final int x;
  private final int y;
  private final Orientation orientation;

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
}
