package com.franklin.sample.haveibeenpwned.command;

import com.franklin.sample.haveibeenpwned.core.HttpSupport;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.List;

/**
 * command for the {@link CommandConstants#BREACH_COMMAND}
 */
@Component
public final class BreachCommand extends AbstractCommand {

  private final HttpSupport httpSupport;

  private static final String NAME_OPTION = "name";

  @Autowired
  public BreachCommand(HttpSupport httpSupport) {
    this.httpSupport = httpSupport;
    options.addOption(Option.builder()
            .hasArg()
            .longOpt(NAME_OPTION)
            .desc("The breach name to be searched for")
            .valueSeparator()
            .required()
            .type(String.class)
            .build());
  }

  @Override
  protected List<CommandFilter> filters() {
    return Lists.newArrayList();
  }

  @Override
  protected String commandString() {
    return CommandConstants.BREACH_COMMAND;
  }

  @Override
  public String runCommand(CommandLine cmd) {
    try {
      String baseUri = Joiner.on("/").join(CommandConstants.BREACH_SERVICE, cmd.getOptionValue(NAME_OPTION));
      URIBuilder uriBuilder = new URIBuilder(baseUri);
      return httpSupport.getResponse(uriBuilder.build());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
