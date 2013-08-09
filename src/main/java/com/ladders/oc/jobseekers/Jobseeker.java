package com.ladders.oc.jobseekers;

import com.ladders.oc.Name;
import com.ladders.oc.applications.ApplicationQueryHelper;
import com.ladders.oc.displayables.DisplayableJobseeker;
import com.ladders.oc.displayers.JobseekerDisplayer;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobs.Jobs;

public class Jobseeker implements DisplayableJobseeker
{

  private final Name name;
  private final Jobs savedJobs = new Jobs();

  public static Jobseeker named(String name)
  {
    return new Jobseeker(new Name(name));
  }
  
  private Jobseeker (Name name)
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

  public ApplicationHelper applyFor(Job developerJob)
  {
    return new ApplicationHelper(this, developerJob);
  }
/*
  public boolean applyToJob(ApplicationProcessor appProcessor, Job job, Maybe<Resume> resume)
  {
    boolean applyStatus = appProcessor.apply(this, job, resume);
    if (applyStatus)
      appliedToJobs.add(job);
    return applyStatus;
  }
*/
  public ApplicationQueryHelper getJobsAppliedTo()
  {
    return new ApplicationQueryHelper(this);
  }

  @Override
  public void displayTo(JobseekerDisplayer jobseekerDisplayer)
  {
    jobseekerDisplayer.displayJobseeker(name);    
  }

}
