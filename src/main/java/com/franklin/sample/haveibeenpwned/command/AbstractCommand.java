package com.franklin.sample.haveibeenpwned.command;

import com.franklin.sample.haveibeenpwned.core.HttpSupport;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Base class for command line. Provides the base run method
 */
@Component
public abstract class AbstractCommand implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCommand.class);

  final Options options = new Options();

  private final CommandLineParser parser = new DefaultParser();

  final HttpSupport httpSupport;

  private final CommandSupport commandSupport = new CommandSupport();

  abstract String commandString();

  abstract String process(CommandLine cmd);

  abstract List<CommandFilter> filters();

  @Autowired
  AbstractCommand(HttpSupport httpSupport) {
    this.httpSupport = httpSupport;
    commandSupport.addFilterToOptions(filters(), options);
  }

  @Override
  public void run(String... args) {
    if (args.length > 0 && commandString().equals(args[0])) {
      try {
        CommandLine cmd = parser.parse(options, args);
        LOGGER.info(process(cmd));
      } catch (ParseException e) {
        commandSupport.showUsage(commandString(), options);
      }
    }
  }
}
