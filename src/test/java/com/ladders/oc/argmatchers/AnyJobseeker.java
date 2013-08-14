package com.ladders.oc.argmatchers;

import org.mockito.ArgumentMatcher;

import com.ladders.oc.displayables.DisplayableJobseeker;

public class AnyJobseeker extends ArgumentMatcher<DisplayableJobseeker>
{

  @Override
  public boolean matches(Object argument)
  {
    return true;
  }

}
