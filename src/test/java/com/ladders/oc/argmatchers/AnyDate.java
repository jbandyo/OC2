package com.ladders.oc.argmatchers;

import java.util.Date;

import org.mockito.ArgumentMatcher;

class AnyDate extends ArgumentMatcher<Date>
{
  @Override
  public boolean matches(Object argument)
  {
    return true;
  }
} 

