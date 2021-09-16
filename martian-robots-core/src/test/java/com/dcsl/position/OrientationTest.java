package com.dcsl.position;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrientationTest {

  private static final Position ORIGIN = createPositionMock(0, 0);

  @Test
  public void getShortName_Always_Correct() {
    assertEquals("N", Orientation.NORTH.getShortName());
    assertEquals("E", Orientation.EAST.getShortName());
    assertEquals("S", Orientation.SOUTH.getShortName());
    assertEquals("W", Orientation.WEST.getShortName());
  }

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

  @Test
  public void getPositionInFront_FacingNorthAtOrigin_YIncreases() {
    Position position = Orientation.NORTH.getPositionInFront(ORIGIN);
    assertEquals(0, position.getX());
    assertEquals(1, position.getY());
  }

  @Test
  public void getPositionInFront_FacingNorthInPositiveXYCoordinates_YIncreases() {
    int xPosition = 5;
    int yPosition = 5;
    Position position =
        Orientation.NORTH.getPositionInFront(createPositionMock(xPosition, yPosition));
    assertEquals(xPosition, position.getX());
    assertEquals(yPosition + 1, position.getY());
  }

  @Test
  public void getPositionInFront_FacingNorthInNegativeXYCoordinates_YIncreases() {
    int xPosition = -5;
    int yPosition = -5;
    Position position =
        Orientation.NORTH.getPositionInFront(createPositionMock(xPosition, yPosition));
    assertEquals(xPosition, position.getX());
    assertEquals(yPosition + 1, position.getY());
  }

  @Test
  public void getPositionInFront_FacingEastAtOrigin_XIncreases() {
    Position position = Orientation.EAST.getPositionInFront(ORIGIN);
    assertEquals(1, position.getX());
    assertEquals(0, position.getY());
  }

  @Test
  public void getPositionInFront_FacingEastInPositiveXYCoordinates_XIncreases() {
    int xPosition = 5;
    int yPosition = 5;
    Position position =
        Orientation.EAST.getPositionInFront(createPositionMock(xPosition, yPosition));
    assertEquals(xPosition + 1, position.getX());
    assertEquals(yPosition, position.getY());
  }

  @Test
  public void getPositionInFront_FacingEastInNegativeXYCoordinates_XIncreases() {
    int xPosition = -5;
    int yPosition = -5;
    Position position =
        Orientation.EAST.getPositionInFront(createPositionMock(xPosition, yPosition));
    assertEquals(xPosition + 1, position.getX());
    assertEquals(yPosition, position.getY());
  }

  @Test
  public void getPositionInFront_FacingSouthAtOrigin_YDecreases() {
    Position position = Orientation.SOUTH.getPositionInFront(ORIGIN);
    assertEquals(0, position.getX());
    assertEquals(-1, position.getY());
  }

  @Test
  public void getPositionInFront_FacingSouthInPositiveXYCoordinates_YDecreases() {
    int xPosition = 5;
    int yPosition = 5;
    Position position =
        Orientation.SOUTH.getPositionInFront(createPositionMock(xPosition, yPosition));
    assertEquals(xPosition, position.getX());
    assertEquals(yPosition - 1, position.getY());
  }

  @Test
  public void getPositionInFront_FacingSouthInNegativeXYCoordinates_YDecreases() {
    int xPosition = -5;
    int yPosition = -5;
    Position position =
        Orientation.SOUTH.getPositionInFront(createPositionMock(xPosition, yPosition));
    assertEquals(xPosition, position.getX());
    assertEquals(yPosition - 1, position.getY());
  }

  @Test
  public void getPositionInFront_FacingWestAtOrigin_XDecreases() {
    Position position = Orientation.WEST.getPositionInFront(ORIGIN);
    assertEquals(-1, position.getX());
    assertEquals(0, position.getY());
  }

  @Test
  public void getPositionInFront_FacingWestInPositiveXYCoordinates_XDecreases() {
    int xPosition = 5;
    int yPosition = 5;
    Position position =
        Orientation.WEST.getPositionInFront(createPositionMock(xPosition, yPosition));
    assertEquals(xPosition - 1, position.getX());
    assertEquals(yPosition, position.getY());
  }

  @Test
  public void getPositionInFront_FacingWestInNegativeXYCoordinates_XDecreases() {
    int xPosition = -5;
    int yPosition = -5;
    Position position =
        Orientation.WEST.getPositionInFront(createPositionMock(xPosition, yPosition));
    assertEquals(xPosition - 1, position.getX());
    assertEquals(yPosition, position.getY());
  }

  @Test
  public void fromShortName_ShortNameIsFound_ReturnsThatOrientation() {
    assertEquals(
        Optional.of(Orientation.NORTH),
        Orientation.fromShortName(Orientation.NORTH.getShortName())
    );
    assertEquals(
        Optional.of(Orientation.EAST),
        Orientation.fromShortName(Orientation.EAST.getShortName())
    );
    assertEquals(
        Optional.of(Orientation.SOUTH),
        Orientation.fromShortName(Orientation.SOUTH.getShortName())
    );
    assertEquals(
        Optional.of(Orientation.WEST),
        Orientation.fromShortName(Orientation.WEST.getShortName())
    );
  }

  @Test
  public void fromShortName_ShortNameIsNotFound_ReturnsEmptyOptional() {
    assertTrue(Orientation.fromShortName("Not an orientation").isEmpty());
  }

  private static Position createPositionMock(int x, int y) {
    Position position = mock(Position.class);
    when(position.getX()).thenReturn(x);
    when(position.getY()).thenReturn(y);
    return position;
  }

}