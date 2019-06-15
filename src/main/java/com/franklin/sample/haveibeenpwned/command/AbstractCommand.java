package com.franklin.sample.haveibeenpwned.command;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

/**
 * Base class for command line. Provides the base run method
 */
public abstract class AbstractCommand implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCommand.class);

  protected final Options options = new Options();

  private final CommandSupport commandSupport = new CommandSupport();

  protected abstract String commandString();

  protected abstract String runCommand(CommandLine cmd);

  protected abstract List<CommandFilter> filters();

  @Autowired
  AbstractCommand() {
    commandSupport.addFilterToOptions(filters(), options);
  }

  @Override
  public void run(String... args) {
    if (args.length > 0 && commandString().equals(args[0])) {
      try {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        LOGGER.info(runCommand(cmd));
      } catch (ParseException e) {
        commandSupport.showUsage(commandString(), options);
      }
    }
  }
}
