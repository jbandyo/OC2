package com.ladders.oc.applications;

import java.util.HashSet;
import java.util.Set;

import com.ladders.oc.displayables.DisplayableApplication;
import com.ladders.oc.displayables.DisplayableApplications;
import com.ladders.oc.displayers.ApplicationsDisplayer;

public class Applications implements DisplayableApplications
{
  private final Set<Application> appSet = new HashSet<Application>();

  public void add(Application app)
  {
    appSet.add(app);
  }

  @Override
  public void displayTo(ApplicationsDisplayer appsDisplayer)
  {
    Set<DisplayableApplication> displayableApps = new HashSet<DisplayableApplication>();
    for (Application app : appSet)
    {
      DisplayableApplication displayableApp = (DisplayableApplication)app;
      displayableApps.add(displayableApp);
    }
    appsDisplayer.displayApplications(displayableApps);
  }

}
