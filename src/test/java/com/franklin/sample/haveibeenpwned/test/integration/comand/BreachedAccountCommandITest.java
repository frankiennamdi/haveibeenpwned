package com.franklin.sample.haveibeenpwned.test.integration.comand;

import com.franklin.sample.haveibeenpwned.command.BreachedAccountCommand;
import com.franklin.sample.haveibeenpwned.command.CommandConstants;
import com.franklin.sample.haveibeenpwned.core.HttpSupport;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BreachedAccountCommandITest {

  @Rule
  public OutputCapture outputCapture = new OutputCapture();

  private BreachedAccountCommand command;

  @Before
  public void setUp() {
    command = new BreachedAccountCommand(new HttpSupport());
  }

  @Test
  public void testRunCommand_WithFilters() {
    command.run(CommandConstants.BREACHED_ACCOUNT_COMMAND, "-account=test@example.com", "-domain=adobe.com",
            "-truncateResponse=true", "-includeUnverified=true");
    assertThat(outputCapture.toString(), hasJsonPath("$.[0].Name", equalTo("Adobe")));
  }

  @Test
  public void testRunCommand_WithNoFilters() {
    command.run(CommandConstants.BREACHED_ACCOUNT_COMMAND, "-account=test@example.com");
    assertThat(outputCapture.toString(), hasJsonPath("$.[0].Name"));
    assertThat(outputCapture.toString(), hasJsonPath("$.[0].Title"));
  }

  @Test
  public void testRunCommandThatRequeiredOptionsAreEnforced() {
    command.run(CommandConstants.BREACHED_ACCOUNT_COMMAND);
    assertThat(outputCapture.toString(), containsString("usage: breachedaccount --account <arg> [--domain <arg>]"));
  }
}
