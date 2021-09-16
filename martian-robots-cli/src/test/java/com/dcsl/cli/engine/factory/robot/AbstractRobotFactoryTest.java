package com.dcsl.cli.engine.factory.robot;

import org.junit.jupiter.api.Test;

import com.dcsl.position.FixedOrientedPosition;
import com.dcsl.position.Orientation;
import com.dcsl.position.OrientedPosition;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

class AbstractRobotFactoryTest {

  private final RobotFactory factory = spy(AbstractRobotFactory.class);

  @Test
  public void createRobot_PositionProvided_RobotCreatedWithPosition() {
    OrientedPosition position = new FixedOrientedPosition(1, 3, Orientation.NORTH);
    assertEquals(position, factory.createRobot(position).getOrientedPosition());
  }

}