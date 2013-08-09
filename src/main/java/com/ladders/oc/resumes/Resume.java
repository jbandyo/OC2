package com.ladders.oc.resumes;

import com.ladders.oc.jobseekers.Jobseeker;

public class Resume
{
  Jobseeker jobseeker;
  
  public static Resume createdBy(Jobseeker jobseeker)
  {
    return new Resume(jobseeker);
  }

  private Resume(Jobseeker jobseeker)
  {
    this.jobseeker = jobseeker;
  }

  public boolean ownedBy(Jobseeker jobseeker)
  {
    return (this.jobseeker == jobseeker);
  }

}
