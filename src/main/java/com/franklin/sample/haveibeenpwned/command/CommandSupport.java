package com.franklin.sample.haveibeenpwned.command;

import com.google.common.collect.Lists;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

class CommandSupport {

  private static final Logger LOGGER = LoggerFactory.getLogger(CommandSupport.class);

  private final HelpFormatter formatter = new HelpFormatter();

  /**
   * Show usage
   */
  void showUsage(String command, Options options) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    formatter.printUsage(pw, formatter.getWidth(), command, options);
    formatter.printOptions(pw, formatter.getWidth(), options, formatter.getLeftPadding(),
            formatter.getDescPadding());
    LOGGER.info("\n{}", sw);
  }

  /**
   * Convert {@link CommandFilter} to {@link NameValuePair}
   */
  List<NameValuePair> commandFiltersToNameValuePairs(CommandLine cmd, List<? extends CommandFilter> filters) {
    List<NameValuePair> nameValuePairs = Lists.newArrayList();
    for (CommandFilter filter : filters) {
      if (cmd.hasOption(filter.filterKey())) {
        nameValuePairs.add(new BasicNameValuePair(filter.filterKey(), cmd.getOptionValue(filter.filterKey())));
      }
    }
    return nameValuePairs;
  }


  void addFilterToOptions(List<? extends CommandFilter> filters, Options options) {
    for (CommandFilter commandFilter : filters) {
      options.addOption(Option.builder()
              .hasArg()
              .longOpt(commandFilter.filterKey())
              .desc(commandFilter.filterKeyDescription())
              .valueSeparator()
              .type(commandFilter.filterType())
              .build());
    }
  }

}
