package com.dcsl.position.grid;

import org.junit.jupiter.api.Test;

import com.dcsl.position.Position;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MarsGridTest {

  private static final int MAX_X_MAX = 50;
  private static final int MAX_Y_MAX = 50;
  private static final int X_MIN = 0;
  private static final int X_MAX = MAX_X_MAX;
  private static final int Y_MIN = 0;
  private static final int Y_MAX = MAX_Y_MAX;

  private final Grid grid = new MarsGrid(X_MIN, X_MAX, Y_MIN, Y_MAX);

  @Test
  public void equalsAndHashCode_Always_Valid() {
    EqualsVerifier.forClass(MarsGrid.class).suppress(Warning.STRICT_INHERITANCE).verify();
  }

  @Test
  public void constructor_xMaxIsEqualToXMin_ExceptionThrown() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new MarsGrid(X_MIN, X_MIN, Y_MIN, Y_MAX)
    );
  }

  @Test
  public void constructor_xMaxIsLessThanXMin_ExceptionThrown() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new MarsGrid(X_MIN, X_MIN - 1, Y_MIN, Y_MAX)
    );
  }

  @Test
  public void constructor_yMaxIsEqualToYMin_ExceptionThrown() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new MarsGrid(X_MIN, X_MAX, Y_MIN, Y_MIN)
    );
  }

  @Test
  public void constructor_yMaxIsLessThanYMin_ExceptionThrown() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new MarsGrid(X_MIN, X_MAX, Y_MIN, Y_MIN - 1)
    );
  }

  @Test
  public void constructor_xMaxIsGreaterThanMaxAllowedValue_ExceptionThrown() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new MarsGrid(X_MIN, MAX_X_MAX + 1, Y_MIN, Y_MAX)
    );
  }

  @Test
  public void constructor_yMaxIsGreaterThanMaxAllowedValue_ExceptionThrown() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new MarsGrid(X_MIN, X_MAX, Y_MIN, MAX_Y_MAX + 1)
    );
  }

  @Test
  public void containsPosition_PositionIsInMiddleOfGrid_ReturnsTrue() {
    assertTrue(grid.containsPosition(createPositionMock((X_MAX - X_MIN) / 2, (Y_MAX - Y_MIN) / 2)));
  }

  @Test
  public void containsPosition_PositionIsAtMinXAndYPosition_ReturnsTrue() {
    assertTrue(grid.containsPosition(createPositionMock(X_MIN, Y_MIN)));
  }

  @Test
  public void containsPosition_PositionIsAtMaxXAndYPosition_ReturnsTrue() {
    assertTrue(grid.containsPosition(createPositionMock(X_MAX, Y_MAX)));
  }

  @Test
  public void containsPosition_PositionBelowMinX_ReturnsFalse() {
    assertFalse(grid.containsPosition(createPositionMock(X_MIN - 1, Y_MIN)));
  }

  @Test
  public void containsPosition_PositionAboveMaxX_ReturnsFalse() {
    assertFalse(grid.containsPosition(createPositionMock(X_MAX + 1, Y_MIN)));
  }

  @Test
  public void containsPosition_PositionBelowMinY_ReturnsFalse() {
    assertFalse(grid.containsPosition(createPositionMock(X_MIN, Y_MIN - 1)));
  }

  @Test
  public void containsPosition_PositionAboveMaxY_ReturnsFalse() {
    assertFalse(grid.containsPosition(createPositionMock(X_MIN, Y_MAX + 1)));
  }

  private Position createPositionMock(int x, int y) {
    Position position = mock(Position.class);
    when(position.getX()).thenReturn(x);
    when(position.getY()).thenReturn(y);
    return position;
  }

}