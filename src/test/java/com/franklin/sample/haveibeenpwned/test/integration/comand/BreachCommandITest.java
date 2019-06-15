package com.franklin.sample.haveibeenpwned.test.integration.comand;

import com.franklin.sample.haveibeenpwned.command.BreachCommand;
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

public class BreachCommandITest {

  @Rule
  public OutputCapture outputCapture = new OutputCapture();

  private BreachCommand command;

  @Before
  public void setUp() {
    command = new BreachCommand(new HttpSupport());
  }

  @Test
  public void testRunCommand_WithOptions() {
    command.run(CommandConstants.BREACH_COMMAND, "-name=adobe");
    assertThat(outputCapture.toString(), hasJsonPath("$.Name", equalTo("Adobe")));
  }

  @Test
  public void testRunCommand_ThatRequiredOptionsAreEnforced() {
    command.run(CommandConstants.BREACH_COMMAND);
    assertThat(outputCapture.toString(), containsString("usage: breach --name <arg>"));
  }
}
