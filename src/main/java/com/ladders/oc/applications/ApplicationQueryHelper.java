package com.ladders.oc.applications;

import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobseekers.Jobseeker;

public class ApplicationQueryHelper
{
  private ApplicationFilter filter = new ApplicationFilter();
  
  public ApplicationQueryHelper(Job job)
  {
    filter = filter.byJob(job);
  }

  public ApplicationQueryHelper(Jobseeker jobseeker)
  {
    filter = filter.byJobseeker(jobseeker);
  }

  public Applications from(ApplicationRepository appRepo)
  {
    return appRepo.getApplications(filter);
  }

}
