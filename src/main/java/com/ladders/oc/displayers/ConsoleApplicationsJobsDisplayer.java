package com.ladders.oc.displayers;

import java.util.Set;

import com.ladders.oc.displayables.DisplayableApplication;

public class ConsoleApplicationsJobsDisplayer implements ApplicationsDisplayer
{

  @Override
  public void displayApplications(Set<? extends DisplayableApplication> appSet)
  {
    ApplicationDisplayer displayer = new ConsoleApplicationJobDisplayer();
    for (DisplayableApplication app : appSet)
    {
      System.out.print("- ");
      app.displayTo(displayer);
      System.out.println();
    }
    
  }

}
