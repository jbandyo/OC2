package com.ladders.oc.argmatchers;

import org.mockito.ArgumentMatcher;

import com.ladders.oc.displayables.DisplayableJob;

class AnyJob extends ArgumentMatcher<DisplayableJob>
{
  @Override
  public boolean matches(Object argument)
  {
    return true;
  }
} 

