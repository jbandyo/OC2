package com.ladders.oc.argmatchers;

import java.util.Set;

import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import com.ladders.oc.displayables.DisplayableApplication;
import com.ladders.oc.displayers.ApplicationDisplayer;
import com.ladders.oc.jobseekers.Jobseeker;

public class SetOfTwoAppsWithJobSeekers extends ArgumentMatcher<Set<DisplayableApplication>>
{
  Jobseeker jobseeker1;
  Jobseeker jobseeker2;
  
  public SetOfTwoAppsWithJobSeekers(Jobseeker jobseeker1, Jobseeker jobseeker2)
  {
    this.jobseeker1 = jobseeker1;
    this.jobseeker2 = jobseeker2;
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
      Mockito.verify(appDisplayer).displayApplication(Mockito.argThat(new AnyJob()), 
                                                      Mockito.argThat(new OneJobseeker(jobseeker1, jobseeker2)),
                                                      Mockito.argThat(new AnyDate()));
    }
    return true;
  }
}

