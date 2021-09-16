package com.dcsl.cli.engine.state;

import java.util.Optional;

public interface InterfaceState {

  Optional<String> process(String input);

}
