package com.ladders.oc.jobseekers;

import com.ladders.oc.applications.ApplicationProcessor;
import com.ladders.oc.recruiters.JobPosting;
import com.ladders.oc.resumes.Resume;
import com.theladders.confident.Maybe;

public class ApplicationHelper
{
  private final Jobseeker  jobseeker;
  private final JobPosting jobPosting;
  
  ApplicationHelper(Jobseeker jobseeker,
                           JobPosting jobPosting)
  {
    this.jobseeker  = jobseeker;
    this.jobPosting = jobPosting;
  }

  public SubmitHelper with(Resume resume)
  {
    Maybe<Resume> actualResume = Maybe.just(resume);
    return new SubmitHelper(jobseeker, jobPosting, actualResume);
  }

  public boolean to(ApplicationProcessor applicationProcessor)
  {
    Maybe<Resume> noResume = Maybe.nothing();
    return applicationProcessor.apply(jobseeker, jobPosting, noResume);
  }

}
