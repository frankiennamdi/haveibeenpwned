package com.franklin.sample.haveibeenpwned.test.integration.comand;

import com.franklin.sample.haveibeenpwned.command.BreachesCommand;
import com.franklin.sample.haveibeenpwned.command.CommandConstants;
import com.franklin.sample.haveibeenpwned.core.HttpSupport;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BreachesCommandITest {

  @Rule
  public OutputCapture outputCapture = new OutputCapture();

  private BreachesCommand command;

  @Before
  public void setUp() {
    command = new BreachesCommand(new HttpSupport());
  }

  @Test
  public void testRunCommand_WithDomainSpecified() {
    command.run(CommandConstants.BREACHES_COMMAND, "-domain=adobe.com");
    assertThat(outputCapture.toString(), hasJsonPath("$.[0].Name", equalTo("Adobe")));
  }

  @Test
  public void testRunCommand_WithNoDomainSpecified() {
    command.run(CommandConstants.BREACHES_COMMAND);
    assertThat(outputCapture.toString(), hasJsonPath("$.[0].Name"));
    assertThat(outputCapture.toString(), hasJsonPath("$.[0].Title"));
  }

}
