package com.ladders.oc.displayers;

import java.util.Set;

import com.ladders.oc.displayables.DisplayableJob;

public class ConsoleJobsDisplayer implements JobsDisplayer
{

  @Override
  public void displayJobs(Set<? extends DisplayableJob> jobSet)
  {
    JobDisplayer displayer = new ConsoleJobDisplayer();
    for (DisplayableJob job : jobSet)
    {
      System.out.print("- ");
      job.displayTo(displayer);
      System.out.println();
    }
    
  }

}
