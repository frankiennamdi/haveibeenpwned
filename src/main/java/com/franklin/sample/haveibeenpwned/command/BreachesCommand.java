package com.franklin.sample.haveibeenpwned.command;

import com.franklin.sample.haveibeenpwned.core.HttpSupport;
import com.google.common.collect.Lists;
import org.apache.commons.cli.CommandLine;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.List;

/**
 * command handler for {@link CommandConstants#BREACHES_COMMAND}
 */
@Component
public final class BreachesCommand extends AbstractCommand {

  private final CommandSupport commandSupport = new CommandSupport();

  private final HttpSupport httpSupport;

  @Autowired
  public BreachesCommand(HttpSupport httpSupport) {
    this.httpSupport = httpSupport;
  }

  @Override
  protected List<CommandFilter> filters() {
    return Lists.newArrayList(Filter.values());
  }

  @Override
  protected String commandString() {
    return CommandConstants.BREACHES_COMMAND;
  }

  @Override
  public String runCommand(CommandLine cmd) {
    try {
      URIBuilder uriBuilder = new URIBuilder(CommandConstants.BREACHES_SERVICE);
      uriBuilder.addParameters(commandSupport.commandFiltersToNameValuePairs(cmd, filters()));
      return httpSupport.getResponse(uriBuilder.build());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  public enum Filter implements CommandFilter {

    DOMAIN("domain", "Filters the result set to only breaches against the domain specified", String.class);

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
