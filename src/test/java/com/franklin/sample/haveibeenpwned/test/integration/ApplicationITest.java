package com.franklin.sample.haveibeenpwned.test.integration;

import com.franklin.sample.haveibeenpwned.Application;
import com.franklin.sample.haveibeenpwned.command.CommandConstants;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class ApplicationITest {

  @Rule
  public OutputCapture outputCapture = new OutputCapture();

  @Test
  public void testApplicationShouldProcessArgumentPassedForBreachedAccount() {
    Application.main(new String[]{CommandConstants.BREACHED_ACCOUNT_COMMAND, "-account=test@example.com", "-domain=adobe.com",
            "-truncateResponse=true", "-includeUnverified=true"});
    assertThat(stripWhiteSpaceAndNewline(outputCapture.toString()),
            containsString(stripWhiteSpaceAndNewline("\"Name\": \"Adobe\"")));
  }

  @Test
  public void testApplicationShouldProcessArgumentPassedForPasteAccount() {
    Application.main(new String[]{CommandConstants.PASTEACCOUNT_COMMAND, "-account=test@example.com"});
    assertThat(stripWhiteSpaceAndNewline(outputCapture.toString()),
            containsString(stripWhiteSpaceAndNewline("\"Source\": \"AdHocUrl\"")));
  }

  @Test
  public void testApplicationShouldProcessArgumentPassedForBreaches() {
    Application.main(new String[]{CommandConstants.BREACHES_COMMAND, "-domain=adobe.com"});
    assertThat(stripWhiteSpaceAndNewline(outputCapture.toString()),
            containsString(stripWhiteSpaceAndNewline("\"Name\": \"Adobe\"")));
  }

  @Test
  public void testApplicationShouldProcessArgumentPassedForBreach() {
    Application.main(new String[]{CommandConstants.BREACH_COMMAND, "-name=adobe"});
    assertThat(stripWhiteSpaceAndNewline(outputCapture.toString()),
            containsString(stripWhiteSpaceAndNewline("\"Name\": \"Adobe\"")));
  }

  @Test
  public void testApplicationNoArgumentPassedIn() {
    Application.main(new String[]{});
    assertThat(outputCapture.toString(), containsString("usage: haveibeenpwned.jar"));
    assertThat(outputCapture.toString(), containsString("-breachedaccount"));
    assertThat(outputCapture.toString(), containsString("-breaches"));
    assertThat(outputCapture.toString(), containsString("-pasteaccount"));
  }

  private String stripWhiteSpaceAndNewline(String str) {
    return str.replaceAll("\r", "")
            .replaceAll("\n", "")
            .replaceAll("\\s+", "");
  }
}
