package com.ladders.oc.argmatchers;

import java.util.Set;

import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import com.ladders.oc.displayables.DisplayableApplication;
import com.ladders.oc.displayers.ApplicationDisplayer;
import com.ladders.oc.jobs.Job;

public class SetOfThreeAppsWithJobs extends ArgumentMatcher<Set<DisplayableApplication>>
{
  Job architectJob;
  Job developerJob;
  Job programmerJob;

  public SetOfThreeAppsWithJobs(Job architectJob,
                                Job developerJob,
                                Job programmerJob)
  {
    this.architectJob = architectJob;
    this.developerJob = developerJob;
    this.programmerJob = programmerJob;
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
      Mockito.verify(appDisplayer).displayApplication(Mockito.argThat(new OneJob(architectJob, developerJob, programmerJob)), 
                                                      Mockito.argThat(new AnyJobseeker()),
                                                      Mockito.argThat(new AnyDate()));
    }
    return true;
  }

}
