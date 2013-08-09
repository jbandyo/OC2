package com.ladders.oc.jobseekers;

import com.ladders.oc.applications.ApplicationProcessor;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.resumes.Resume;
import com.theladders.confident.Maybe;

public class SubmitHelper
{
  private final Jobseeker jobseeker;
  private final Job job;
  private final Maybe<Resume> resume;
  
  SubmitHelper(Jobseeker jobseeker,
                      Job job,
                      Maybe<Resume> resume)
  {
    this.jobseeker = jobseeker;
    this.job = job;
    this.resume = resume;
  }
  
  public boolean to(ApplicationProcessor applicationProcessor)
  {
    return applicationProcessor.apply(jobseeker, job, resume);
  }

}
