package com.dcsl.cli.engine;

import java.util.Optional;

public interface InputProcessor {

  Optional<String> process(String input);

}
