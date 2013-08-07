package com.ladders.oc.resumes;

import com.ladders.oc.jobseekers.Jobseeker;

public class Resume
{
  Jobseeker jobseeker;

  public Resume(Jobseeker jobseeker)
  {
    this.jobseeker = jobseeker;
  }

  public boolean ownedBy(Jobseeker jobseeker)
  {
    return (this.jobseeker == jobseeker);
  }

}
