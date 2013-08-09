package com.ladders.oc.jobseekers;

import com.ladders.oc.applications.ApplicationProcessor;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.resumes.Resume;
import com.theladders.confident.Maybe;

public class ApplicationHelper
{
  private final Jobseeker jobseeker;
  private final Job job;
  
  ApplicationHelper(Jobseeker jobseeker,
                           Job job)
  {
    this.jobseeker = jobseeker;
    this.job = job;
  }

  public SubmitHelper with(Resume resume)
  {
    Maybe<Resume> actualResume = Maybe.just(resume);
    return new SubmitHelper(jobseeker, job, actualResume);
  }

  public boolean to(ApplicationProcessor applicationProcessor)
  {
    Maybe<Resume> noResume = Maybe.nothing();
    return applicationProcessor.apply(jobseeker, job, noResume);
  }

}
