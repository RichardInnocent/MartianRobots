package com.dcsl.cli.engine.state;

import java.util.Optional;

import com.dcsl.cli.engine.StateHolder;
import com.dcsl.cli.engine.factory.grid.GridFactory;

public class AwaitingGridConfigurationState implements InterfaceState {

  private final GridFactory gridFactory;
  private final StateHolder inputProcessor;

  public AwaitingGridConfigurationState(GridFactory gridFactory, StateHolder inputProcessor) {
    this.gridFactory = gridFactory;
    this.inputProcessor = inputProcessor;
  }

  @Override
  public Optional<String> process(String input) {
    inputProcessor.setGrid(gridFactory.createGrid(input));
    return Optional.empty();
  }

}
