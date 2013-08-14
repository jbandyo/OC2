package com.ladders.oc.argmatchers;

import java.util.Date;
import java.util.Set;

import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import com.ladders.oc.displayables.DisplayableApplication;
import com.ladders.oc.displayers.ApplicationDisplayer;

public class SetOfTwoAppsWithDates extends ArgumentMatcher<Set<DisplayableApplication>>
{
  private final Date date;
  
  public SetOfTwoAppsWithDates(Date date)
  {
    this.date = date;
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
                                                      Mockito.argThat(new AnyJobseeker()),
                                                      Mockito.argThat(new OneDate(date)));
    }
    return true;
  }

}
