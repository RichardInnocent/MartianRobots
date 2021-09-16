package com.dcsl.cli.engine.state;

import java.util.Optional;

import com.dcsl.cli.engine.StateHolder;
import com.dcsl.cli.engine.factory.grid.GridFactory;

/**
 * The state induced when no grid has been configured.
 */
public class AwaitingGridConfigurationState implements InterfaceState {

  private final GridFactory gridFactory;
  private final StateHolder inputProcessor;

  public AwaitingGridConfigurationState(GridFactory gridFactory, StateHolder inputProcessor) {
    this.gridFactory = gridFactory;
    this.inputProcessor = inputProcessor;
  }

  @Override
  public Optional<String> process(String input) throws IllegalArgumentException {
    inputProcessor.setGrid(gridFactory.createGrid(input));
    return Optional.empty();
  }

}
