package com.ladders.oc.applications;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.ladders.oc.displayables.DisplayableApplications;
import com.ladders.oc.displayers.ApplicationsDisplayer;

public class Applications implements DisplayableApplications, Iterable<Application>
{
  private final Set<Application> appSet = new HashSet<Application>();

  public void add(Application app)
  {
    appSet.add(app);
  }

  public int count()
  {
    return appSet.size();
  }

  @Override
  public void displayTo(ApplicationsDisplayer appsDisplayer)
  {
    appsDisplayer.displayApplications(appSet);
  }

  @Override
  public Iterator<Application> iterator()
  {
    return appSet.iterator();
  }

}
