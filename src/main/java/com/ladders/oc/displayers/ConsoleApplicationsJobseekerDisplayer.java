package com.ladders.oc.displayers;

import java.util.Set;

import com.ladders.oc.displayables.DisplayableApplication;

public class ConsoleApplicationsJobseekerDisplayer implements ApplicationsDisplayer
{

  @Override
  public void displayApplications(Set<? extends DisplayableApplication> appSet)
  {
    ApplicationDisplayer displayer = new ConsoleApplicationJobseekerDisplayer();
    for (DisplayableApplication app : appSet)
    {
      System.out.print("- ");
      app.displayTo(displayer);
      System.out.println();
    }
    
  }

}
