package com.dcsl.position;

/**
 * A {@link Position} with an orientation.
 */
public interface OrientedPosition extends Position {
  Orientation getOrientation();
  OrientedPosition getPositionInFront();
}
