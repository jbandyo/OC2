package com.ladders.oc.argmatchers;

import org.mockito.ArgumentMatcher;

import com.ladders.oc.displayables.DisplayableJobPosting;
import com.ladders.oc.recruiters.JobPosting;

public class OneJobPosting extends ArgumentMatcher<DisplayableJobPosting>
{
  JobPosting jobPosting1;
  JobPosting jobPosting2;
  JobPosting jobPosting3;

  public OneJobPosting(JobPosting jobPosting)
  {
    this.jobPosting1 = jobPosting;
  }

  public OneJobPosting(JobPosting jobPosting1,
                       JobPosting jobPosting2,
                       JobPosting jobPosting3)
  {
    this.jobPosting1 = jobPosting1;
    this.jobPosting2 = jobPosting2;
    this.jobPosting3 = jobPosting3;
  }

  @Override
  public boolean matches(Object argument)
  {
    JobPosting posting = (JobPosting)argument;
    if (posting == jobPosting1 || posting == jobPosting2 || posting == jobPosting3)
      return true;
    else
      return false;
  }

}
