package com.ladders.oc.displayers;

import java.util.Set;

import com.ladders.oc.jobs.Job;

public class ConsoleJobsDisplayer implements JobsDisplayer
{

  @Override
  public void displayJobs(Set<Job> jobSet)
  {
    JobDisplayer displayer = new ConsoleJobDisplayer();
    for (Job job : jobSet)
    {
      System.out.print("- ");
      job.displayTo(displayer);
      System.out.println();
    }
    
  }

}
