package com.franklin.sample.haveibeenpwned.test.integration.comand;

import com.franklin.sample.haveibeenpwned.command.CommandConstants;
import com.franklin.sample.haveibeenpwned.command.PasteAccountCommand;
import com.franklin.sample.haveibeenpwned.core.HttpSupport;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.Matchers.*;
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
  public void testRunCommand() {
    command.run(CommandConstants.PASTEACCOUNT_COMMAND, "-account=test@example.com");
    assertThat(outputCapture.toString(), hasJsonPath("$.[0].Id", notNullValue()));
    assertThat(outputCapture.toString(), hasJsonPath("$.[0].Source", notNullValue()));
  }

  @Test
  public void testRunCommand_withNonEmailAccount_AndExpectBadRequestCode() {
    command.run(CommandConstants.PASTEACCOUNT_COMMAND, "-account=adobe");
    assertThat(outputCapture.toString(), hasJsonPath("$.statusCode", equalTo(HttpStatus.SC_BAD_REQUEST)));
    assertThat(outputCapture.toString(), hasJsonPath("$.message", containsString("Invalid email address")));
  }

  @Test
  public void testRunCommand_ThatRequiredOptionsAreEnforced() {
    command.run(CommandConstants.PASTEACCOUNT_COMMAND);
    assertThat(outputCapture.toString(), containsString("usage: pasteaccount --account <arg>"));
  }
}
