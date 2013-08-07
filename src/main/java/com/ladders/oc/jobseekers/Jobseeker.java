package com.ladders.oc.jobseekers;

import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobs.Jobs;

public class Jobseeker
{
  private final Jobs jobs = new Jobs();

  public void saveJob(Job job)
  {
    jobs.add(job);
  }

  public Jobs getSavedJobs()
  {
    return jobs;
  }

}
