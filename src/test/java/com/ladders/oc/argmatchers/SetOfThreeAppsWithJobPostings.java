package com.ladders.oc.argmatchers;

import java.util.Set;

import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import com.ladders.oc.displayables.DisplayableApplication;
import com.ladders.oc.displayers.ApplicationDisplayer;
import com.ladders.oc.recruiters.JobPosting;

public class SetOfThreeAppsWithJobPostings extends ArgumentMatcher<Set<DisplayableApplication>>
{
  JobPosting jobPosting1;
  JobPosting jobPosting2;
  JobPosting jobPosting3;

  public SetOfThreeAppsWithJobPostings(JobPosting jobPosting1,
                                       JobPosting jobPosting2,
                                       JobPosting jobPosting3)
  {
    this.jobPosting1 = jobPosting1;
    this.jobPosting2 = jobPosting2;
    this.jobPosting3 = jobPosting3;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean matches(Object apps)
  {
    Set<DisplayableApplication> appset = (Set<DisplayableApplication>)apps;
    if (appset.size() != 3)
        return false;
    for (DisplayableApplication app : appset)
    {
      ApplicationDisplayer appDisplayer = Mockito.mock(ApplicationDisplayer.class);
      app.displayTo(appDisplayer);
      Mockito.verify(appDisplayer).displayApplication(Mockito.argThat(new OneJobPosting(jobPosting1, jobPosting2, jobPosting3)), 
                                                      Mockito.argThat(new AnyJobseeker()),
                                                      Mockito.argThat(new AnyDate()));
    }
    return true;
  }

}
