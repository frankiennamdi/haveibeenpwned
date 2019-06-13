package com.franklin.sample.haveibeenpwned.command;

public class CommandConstants {

  private static final String SERVICE_BASE = "https://haveibeenpwned.com/api/v2/";

  public static final String BREACHED_ACCOUNT_COMMAND = "breachedaccount";
  public static final String BREACHES_COMMAND = "breaches";
  public static final String PASTEACCOUNT_COMMAND = "pasteaccount";
  public static final String BREACH_COMMAND = "breach";

  static final String BREACHED_ACCOUNT_SERVICE = SERVICE_BASE + BREACHED_ACCOUNT_COMMAND;
  static final String BREACHES_SERVICE = SERVICE_BASE + BREACHES_COMMAND;
  static final String PASTEACCOUNT_SERVICE = SERVICE_BASE + PASTEACCOUNT_COMMAND;
  static final String BREACH_SERVICE = SERVICE_BASE + BREACH_COMMAND;
}
