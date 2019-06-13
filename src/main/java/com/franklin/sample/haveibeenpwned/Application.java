package com.franklin.sample.haveibeenpwned;

import com.franklin.sample.haveibeenpwned.command.CommandConstants;
import org.apache.commons.cli.*;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.franklin.sample.haveibeenpwned")
public class Application {

  public static void main(String[] args) {
    Options options = options();
    if (args.length == 0 || !options.hasOption(args[0])) {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("haveibeenpwned.jar", options);
    } else {
      SpringApplication app = new SpringApplication(Application.class);
      app.setBannerMode(Banner.Mode.OFF);
      app.run(args);
    }
  }

  private static Options options() {
    Options options = new Options();
    Option breachedAccountOpt = Option.builder(CommandConstants.BREACHED_ACCOUNT_COMMAND).build();
    Option breachesOpt = Option.builder(CommandConstants.BREACHES_COMMAND).build();
    Option pasteAccountOpt = Option.builder(CommandConstants.PASTEACCOUNT_COMMAND).build();
    Option breachOpt = Option.builder(CommandConstants.BREACH_COMMAND).build();

    options.addOption(breachedAccountOpt);
    options.addOption(breachesOpt);
    options.addOption(pasteAccountOpt);
    options.addOption(breachOpt);

    return options;
  }
}
