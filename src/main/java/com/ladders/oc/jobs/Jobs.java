package com.ladders.oc.jobs;

import java.util.HashSet;
import java.util.Set;

public class Jobs
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

}
