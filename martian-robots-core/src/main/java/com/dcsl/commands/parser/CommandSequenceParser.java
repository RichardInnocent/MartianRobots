package com.dcsl.commands.parser;

import java.util.List;

public interface CommandSequenceParser {

  List<RobotCommand> getCommands(String commandText) throws InvalidCommandException;

}
