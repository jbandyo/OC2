package com.ladders.oc.applications;

import java.util.Date;

import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobseekers.Jobseeker;

public class ApplicationQueryHelper
{
  protected ApplicationFilter filter = new ApplicationFilter();

  public ApplicationQueryHelper()
  {    
  }

  public ApplicationQueryHelper filterBy(Job job)
  {
    filter = filter.byJob(job);
    return this;
  }

  public ApplicationQueryHelper filterBy(Jobseeker jobseeker)
  {
    filter = filter.byJobseeker(jobseeker);
    return this;
  }

  public ApplicationQueryHelper filterBy(Date date)
  {
    filter = filter.byDate(date);
    return this;
  }

  public Applications from(ApplicationRepository appRepo)
  {
    return appRepo.getApplications(filter);
  }

}
