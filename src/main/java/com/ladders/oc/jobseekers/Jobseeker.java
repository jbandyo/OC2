package com.ladders.oc.jobseekers;

import com.ladders.oc.Name;
import com.ladders.oc.applications.ApplicationProcessor;
import com.ladders.oc.displayables.DisplayableJobseeker;
import com.ladders.oc.displayers.JobseekerDisplayer;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobs.Jobs;
import com.ladders.oc.resumes.Resume;

public class Jobseeker implements DisplayableJobseeker
{

  private final Name name;
  private final Jobs savedJobs = new Jobs();
  private final Jobs appliedToJobs = new Jobs();

  public Jobseeker(Name name)
  {
    this.name = name;
  }
  
  public void saveJob(Job job)
  {
    savedJobs.add(job);
  }

  public Jobs getSavedJobs()
  {
    return savedJobs;
  }

  public boolean applyToJob(ApplicationProcessor appProcessor, Job job, Resume resume)
  {
    boolean applyStatus = appProcessor.apply(this, job, resume);
    if (applyStatus)
      appliedToJobs.add(job);
    return applyStatus;
  }

  public Jobs getAppliedToJobs()
  {
    return appliedToJobs;
  }

  @Override
  public void displayTo(JobseekerDisplayer jobseekerDisplayer)
  {
    jobseekerDisplayer.displayJobseeker(name);
    
  }

}
