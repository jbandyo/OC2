package com.ladders.oc.jobs;

import java.util.HashSet;
import java.util.Set;

import com.ladders.oc.displayables.DisplayableJobs;
import com.ladders.oc.displayers.JobsDisplayer;

public class Jobs implements DisplayableJobs
{
  private final Set<Job> jobSet = new HashSet<Job>();

  public boolean add(Job job)
  {
    return jobSet.add(job);    
  }
    
  public boolean contains(Job job)
  {
    return jobSet.contains(job);
  }

  @Override
  public void displayTo(JobsDisplayer jobsDisplayer)
  {
    jobsDisplayer.displayJobs(jobSet);
  }

}
