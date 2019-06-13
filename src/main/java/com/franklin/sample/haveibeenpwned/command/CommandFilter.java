package com.franklin.sample.haveibeenpwned.command;

public interface CommandFilter {

  String filterKey();

  String filterKeyDescription();

  Class<?> filterType();
}
