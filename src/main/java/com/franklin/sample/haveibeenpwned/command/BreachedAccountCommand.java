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
 * handler for the {@link CommandConstants#BREACHED_ACCOUNT_COMMAND}
 */
@Component
public final class BreachedAccountCommand extends AbstractCommand {

  private static final String ACCOUNT_OPTION = "account";

  private final CommandSupport commandSupport = new CommandSupport();

  private final HttpSupport httpSupport;

  @Autowired
  public BreachedAccountCommand(HttpSupport httpSupport) {
    this.httpSupport = httpSupport;
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
  protected List<CommandFilter> filters() {
    return Lists.newArrayList(Filter.values());
  }

  @Override
  protected String commandString() {
    return CommandConstants.BREACHED_ACCOUNT_COMMAND;
  }

  @Override
  public String runCommand(CommandLine cmd) {
    try {
      String baseUri = Joiner.on("/").join(CommandConstants.BREACHED_ACCOUNT_SERVICE, cmd.getOptionValue(ACCOUNT_OPTION));
      URIBuilder uriBuilder = new URIBuilder(baseUri);
      uriBuilder.addParameters(commandSupport.commandFiltersToNameValuePairs(cmd, filters()));
      return httpSupport.getResponse(uriBuilder.build());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  public enum Filter implements CommandFilter {
    TRUNCATE_RESPONSE("truncateResponse", "Returns only the name of the breach", Boolean.class),
    DOMAIN("domain", "Filters the result set to only breaches against the domain specified", String.class),
    INCLUDE_UNVERIFIED("includeUnverified", "Returns breaches that have been flagged as \"unverified\"", Boolean.class);

    private final String key;
    private final String description;
    private final Class<?> type;

    Filter(String key, String description, Class<?> type) {
      this.key = key;
      this.description = description;
      this.type = type;
    }

    @Override
    public String filterKey() {
      return key;
    }

    @Override
    public String filterKeyDescription() {
      return description;
    }

    @Override
    public Class<?> filterType() {
      return type;
    }
  }
}
