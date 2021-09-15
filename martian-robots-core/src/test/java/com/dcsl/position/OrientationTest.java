package com.dcsl.position;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrientationTest {

  @Test
  public void getNextClockwiseOrientation_Always_Correct() {
    assertEquals(Orientation.EAST, Orientation.NORTH.getNextClockwiseOrientation());
    assertEquals(Orientation.SOUTH, Orientation.EAST.getNextClockwiseOrientation());
    assertEquals(Orientation.WEST, Orientation.SOUTH.getNextClockwiseOrientation());
    assertEquals(Orientation.NORTH, Orientation.WEST.getNextClockwiseOrientation());
  }

  @Test
  public void getNextAnticlockwiseOrientation_Always_Correct() {
    assertEquals(Orientation.WEST, Orientation.NORTH.getNextAnticlockwiseOrientation());
    assertEquals(Orientation.SOUTH, Orientation.WEST.getNextAnticlockwiseOrientation());
    assertEquals(Orientation.EAST, Orientation.SOUTH.getNextAnticlockwiseOrientation());
    assertEquals(Orientation.NORTH, Orientation.EAST.getNextAnticlockwiseOrientation());
  }

}