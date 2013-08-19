package com.ladders.oc.applications;

import java.util.Date;

import com.ladders.oc.jobseekers.Jobseeker;
import com.ladders.oc.recruiters.JobPosting;

public class ApplicationQueryHelper
{
  protected ApplicationFilter filter = new ApplicationFilter();

  public ApplicationQueryHelper()
  {    
  }

  public ApplicationQueryHelper filterBy(JobPosting jobPosting)
  {
    filter = filter.byJobPosting(jobPosting);
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
