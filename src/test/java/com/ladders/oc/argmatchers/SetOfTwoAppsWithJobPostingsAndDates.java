package com.ladders.oc.argmatchers;

import java.util.Date;
import java.util.Set;

import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import com.ladders.oc.displayables.DisplayableApplication;
import com.ladders.oc.displayers.ApplicationDisplayer;
import com.ladders.oc.recruiters.JobPosting;

public class SetOfTwoAppsWithJobPostingsAndDates extends ArgumentMatcher<Set<DisplayableApplication>>
{
  private final Date date;
  private final JobPosting  jobPosting;

  public SetOfTwoAppsWithJobPostingsAndDates(Date date,
                                      JobPosting  jobPosting)
  {
    this.date = date;
    this.jobPosting = jobPosting;
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
      Mockito.verify(appDisplayer).displayApplication(Mockito.argThat(new OneJobPosting(jobPosting)), 
                                                      Mockito.argThat(new AnyJobseeker()),
                                                      Mockito.argThat(new OneDate(date)));
    }
    return true;
  }

}
