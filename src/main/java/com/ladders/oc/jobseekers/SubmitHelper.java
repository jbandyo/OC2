package com.ladders.oc.jobseekers;

import com.ladders.oc.applications.ApplicationProcessor;
import com.ladders.oc.recruiters.JobPosting;
import com.ladders.oc.resumes.Resume;
import com.theladders.confident.Maybe;

public class SubmitHelper
{
  private final Jobseeker  jobseeker;
  private final JobPosting jobPosting;
  private final Maybe<Resume> resume;
  
  SubmitHelper(Jobseeker jobseeker,
               JobPosting jobPosting,
               Maybe<Resume> resume)
  {
    this.jobseeker  = jobseeker;
    this.jobPosting = jobPosting;
    this.resume = resume;
  }
  
  public boolean to(ApplicationProcessor applicationProcessor)
  {
    return applicationProcessor.apply(jobseeker, jobPosting, resume);
  }

}
