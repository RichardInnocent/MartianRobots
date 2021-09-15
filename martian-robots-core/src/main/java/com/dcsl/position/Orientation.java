package com.dcsl.position;

public enum Orientation {
  NORTH,
  EAST,
  SOUTH,
  WEST;

  public Orientation getNextClockwiseOrientation() {
    return Orientation.values()[(ordinal() + 1) % Orientation.values().length];
  }

  public Orientation getNextAnticlockwiseOrientation() {
    return Orientation.values()[(3 + ordinal()) % Orientation.values().length];
  }
}
