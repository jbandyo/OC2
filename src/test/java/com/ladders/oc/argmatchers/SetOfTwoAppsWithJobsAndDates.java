package com.ladders.oc.argmatchers;

import java.util.Date;
import java.util.Set;

import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import com.ladders.oc.displayables.DisplayableApplication;
import com.ladders.oc.displayers.ApplicationDisplayer;
import com.ladders.oc.jobs.Job;

public class SetOfTwoAppsWithJobsAndDates extends ArgumentMatcher<Set<DisplayableApplication>>
{
  private final Date date;
  private final Job  job;

  public SetOfTwoAppsWithJobsAndDates(Date date,
                                      Job  job)
  {
    this.date = date;
    this.job = job;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean matches(Object apps)
  {
    Set<DisplayableApplication> appset = (Set<DisplayableApplication>)apps;
    if (appset.size() != 2)
        return false;
    for (DisplayableApplication app : appset)
    {
      ApplicationDisplayer appDisplayer = Mockito.mock(ApplicationDisplayer.class);
      app.displayTo(appDisplayer);
      Mockito.verify(appDisplayer).displayApplication(Mockito.argThat(new OneJob(job)), 
                                                      Mockito.argThat(new AnyJobseeker()),
                                                      Mockito.argThat(new OneDate(date)));
    }
    return true;
  }

}
