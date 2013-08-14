package com.ladders.oc.argmatchers;

import org.mockito.ArgumentMatcher;

import com.ladders.oc.displayables.DisplayableJob;
import com.ladders.oc.jobs.Job;

public class OneJob extends ArgumentMatcher<DisplayableJob>
{
  Job job1 = null;
  Job job2 = null;
  Job job3 = null;

  public OneJob(Job job)
  {
    this.job1 = job;
  }

  public OneJob(Job job1,
                Job job2,
                Job job3)
  {
    this.job1 = job1;
    this.job2 = job2;
    this.job3 = job3;
  }

  @Override
  public boolean matches(Object argument)
  {
    Job job = (Job)argument;
    if (job == job1 || job == job2 || job == job3)
      return true;
    else
      return false;
  }

}
