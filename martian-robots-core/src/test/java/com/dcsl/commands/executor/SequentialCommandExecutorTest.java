package com.dcsl.commands.executor;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import com.dcsl.commands.parser.CommandSequenceParser;
import com.dcsl.position.OrientedPosition;
import com.dcsl.robots.MarsRobot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SequentialCommandExecutorTest {
  private static final String COMMAND_SEQUENCE = "Test command";

  private final CommandSequenceParser parser = mock(CommandSequenceParser.class);
  private final MarsRobot robot = mock(MarsRobot.class);
  private final OrientedPosition robotPosition = mock(OrientedPosition.class);
  private final SequentialCommandExecutor executor = new SequentialCommandExecutor(parser);

  @BeforeEach
  public void setUp() {
    when(robot.getOrientedPosition()).thenReturn(robotPosition);
  }

  @Test
  public void execute_ParserReturnsNoCommands_NoCommandsExecuted() {
    when(parser.getCommands(COMMAND_SEQUENCE)).thenReturn(Collections.emptyList());
    assertEquals(robotPosition, executor.execute(COMMAND_SEQUENCE, robot));
  }

  @Test
  public void execute_ParseReturnsSingleCommand_CommandExecuted() {
    Consumer<MarsRobot> consumer = createConsumerMock();
    when(parser.getCommands(COMMAND_SEQUENCE)).thenReturn(Collections.singletonList(consumer));
    assertEquals(robotPosition, executor.execute(COMMAND_SEQUENCE, robot));
    verify(consumer, times(1)).accept(robot);
  }

  @Test
  public void execute_ParserReturnsMultipleCommands_CommandsExecutedInOrder() {
    Consumer<MarsRobot> consumer1 = createConsumerMock();
    Consumer<MarsRobot> consumer2 = createConsumerMock();
    Consumer<MarsRobot> consumer3 = createConsumerMock();

    when(parser.getCommands(COMMAND_SEQUENCE)).thenReturn(Arrays.asList(
        consumer1,
        consumer2,
        consumer3
    ));

    assertEquals(robotPosition, executor.execute(COMMAND_SEQUENCE, robot));

    InOrder inOrder = inOrder(consumer1, consumer2, consumer3);
    inOrder.verify(consumer1, times(1)).accept(robot);
    inOrder.verify(consumer2, times(1)).accept(robot);
    inOrder.verify(consumer3, times(1)).accept(robot);
  }

  @SuppressWarnings("unchecked")
  private Consumer<MarsRobot> createConsumerMock() {
    return mock(Consumer.class);
  }

}