package com.ladders.oc.argmatchers;

import org.mockito.ArgumentMatcher;

import com.ladders.oc.displayables.DisplayableJobPosting;

public class AnyJobPosting extends ArgumentMatcher<DisplayableJobPosting>
{

  @Override
  public boolean matches(Object arg0)
  {
    return true;
  }

}
