package com.ladders.oc.argmatchers;

import java.util.Set;

import org.mockito.ArgumentMatcher;

import com.ladders.oc.displayables.DisplayableJob;

public class SetOfThreeJobs extends ArgumentMatcher<Set<DisplayableJob>>
{
  DisplayableJob job1;
  DisplayableJob job2;
  DisplayableJob job3;
  
  public SetOfThreeJobs(DisplayableJob job1, DisplayableJob job2, DisplayableJob job3)
  {
    this.job1 = job1;
    this.job2 = job2;
    this.job3 = job3;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public boolean matches(Object jobs)
  {
    Set<DisplayableJob> jobset = (Set<DisplayableJob>)jobs;
    if ((jobset.size() == 3) && (jobset.contains(job1)) && (jobset.contains(job2)) && (jobset.contains(job3)))
        return true;
    else
      return false;
  }
}

