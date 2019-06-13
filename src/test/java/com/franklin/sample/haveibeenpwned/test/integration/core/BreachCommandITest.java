package com.franklin.sample.haveibeenpwned.test.integration.core;

import com.franklin.sample.haveibeenpwned.command.BreachCommand;
import com.franklin.sample.haveibeenpwned.core.HttpSupport;
import com.franklin.sample.haveibeenpwned.command.CommandConstants;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.rule.OutputCapture;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BreachCommandITest {

  private static final Logger LOGGER = LoggerFactory.getLogger(BreachCommandITest.class);

  @Rule
  public OutputCapture outputCapture = new OutputCapture();

  private BreachCommand command;

  @Before
  public void setUp() {
    command = new BreachCommand(new HttpSupport());
  }

  @Test
  public void testRunCommandWithOptions() {
    command.run(CommandConstants.BREACH_COMMAND, "-name=adobe");
    assertThat(outputCapture.toString(), hasJsonPath("$.Name", equalTo("Adobe")));
  }

  @Test
  public void testRunCommandThatRequeiredOptionsAreEnforced() {
    command.run(CommandConstants.BREACH_COMMAND);
    assertThat(outputCapture.toString(), containsString("usage: breach --name <arg>"));
  }
}
