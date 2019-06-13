package com.franklin.sample.haveibeenpwned.test.integration.core;

import com.franklin.sample.haveibeenpwned.command.BreachedAccountCommand;
import com.franklin.sample.haveibeenpwned.command.CommandConstants;
import com.franklin.sample.haveibeenpwned.command.PasteAccountCommand;
import com.franklin.sample.haveibeenpwned.core.HttpSupport;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class PasteAccountCommandITest {

  @Rule
  public OutputCapture outputCapture = new OutputCapture();

  private PasteAccountCommand command;

  @Before
  public void setUp() {
    command = new PasteAccountCommand(new HttpSupport());
  }

  @Test
  public void testRunCommandWithFilters() {
    command.run(CommandConstants.PASTEACCOUNT_COMMAND, "-account=test@example.com");
    assertThat(outputCapture.toString(), hasJsonPath("$.[0].Id", notNullValue()));
    assertThat(outputCapture.toString(), hasJsonPath("$.[0].Source", notNullValue()));
  }

  @Test
  public void testRunCommandWithNoFilters() {
    command.run(CommandConstants.PASTEACCOUNT_COMMAND, "-account=test@example.com");
    assertThat(outputCapture.toString(), hasJsonPath("$.[0].Id"));
    assertThat(outputCapture.toString(), hasJsonPath("$.[0].Source"));
  }

  @Test
  public void testRunCommandThatRequeiredOptionsAreEnforced() {
    command.run(CommandConstants.PASTEACCOUNT_COMMAND);
    assertThat(outputCapture.toString(), containsString("usage: pasteaccount --account <arg>"));
  }
}
