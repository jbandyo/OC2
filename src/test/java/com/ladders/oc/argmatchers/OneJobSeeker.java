package com.ladders.oc.argmatchers;

import org.mockito.ArgumentMatcher;

import com.ladders.oc.displayables.DisplayableJobseeker;
import com.ladders.oc.jobseekers.Jobseeker;

class OneJobseeker extends ArgumentMatcher<DisplayableJobseeker>
{
  Jobseeker jobseeker1;
  Jobseeker jobseeker2;
  
  OneJobseeker(Jobseeker jobseeker1, Jobseeker jobseeker2)
  {
    this.jobseeker1 = jobseeker1;
    this.jobseeker2 = jobseeker2;
  }

  @Override
  public boolean matches(Object argument)
  {
    Jobseeker jobseeker = (Jobseeker)argument;
    if (jobseeker == jobseeker1 || jobseeker == jobseeker2)
      return true;
    else
      return false;
  }
}

