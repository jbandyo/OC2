package com.ladders.oc.displayers;

import java.util.Set;

import com.ladders.oc.displayables.DisplayableApplication;

public class ConsoleApplicationsJobseekerDisplayer implements ApplicationsDisplayer
{

  @Override
  public void displayApplications(Set<DisplayableApplication> appSet)
  {
    ApplicationDisplayer displayer = new ConsoleApplicationJobseekerDisplayer();
    System.out.println("Applications--");
    for (DisplayableApplication app : appSet)
    {
      System.out.print("- ");
      app.displayTo(displayer);
      System.out.println();
    }
    
  }

}
