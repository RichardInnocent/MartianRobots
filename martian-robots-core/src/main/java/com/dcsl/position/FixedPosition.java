package com.dcsl.position;

import java.util.Objects;

public class FixedPosition implements Position {

  private final int x;
  private final int y;

  public FixedPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public FixedPosition(Position position) {
    this.x = position.getX();
    this.y = position.getY();
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
    if (!(o instanceof FixedPosition)) {
      return false;
    }
    FixedPosition that = (FixedPosition) o;
    return x == that.x && y == that.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}