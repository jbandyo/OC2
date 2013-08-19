package com.ladders.oc.jobs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.ladders.oc.displayables.DisplayableJobs;
import com.ladders.oc.displayers.JobsDisplayer;

public class Jobs implements DisplayableJobs, Iterable<Job>
{
  private final Set<Job> jobSet = new HashSet<Job>();

  public void add(Job job)
  {
    jobSet.add(job);    
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

  @Override
  public Iterator<Job> iterator()
  {
    return jobSet.iterator();
  }

}
