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
 * Command for the {@link CommandConstants#PASTEACCOUNT_COMMAND}
 */
@Component
public class PasteAccountCommand extends AbstractCommand {

  private static final String ACCOUNT_OPTION = "account";

  @Autowired
  public PasteAccountCommand(HttpSupport httpSupport) {
    super(httpSupport);
    options.addOption(Option.builder()
            .hasArg()
            .longOpt(ACCOUNT_OPTION)
            .desc("The account to be searched for")
            .valueSeparator()
            .required()
            .type(String.class)
            .build());
  }

  @Override
  List<CommandFilter> filters() {
    return Lists.newArrayList();
  }

  @Override
  protected String commandString() {
    return CommandConstants.PASTEACCOUNT_COMMAND;
  }

  @Override
  public String process(CommandLine cmd) {
    try {
      String baseUri = Joiner.on("/").join(CommandConstants.PASTEACCOUNT_SERVICE, cmd.getOptionValue(ACCOUNT_OPTION));
      URIBuilder uriBuilder = new URIBuilder(baseUri);
      return httpSupport.getResponse(uriBuilder.build());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
