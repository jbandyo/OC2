package com.ladders.oc.jobseekers;

import com.ladders.oc.applications.ApplicationProcessor;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobs.Jobs;
import com.ladders.oc.resumes.Resume;

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

  public boolean applyToJob(ApplicationProcessor appProcessor, Job job, Resume resume)
  {
    return appProcessor.apply(this, job, resume);
  }

}
