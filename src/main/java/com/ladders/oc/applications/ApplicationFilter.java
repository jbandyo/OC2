package com.ladders.oc.applications;

import java.util.Date;

import com.ladders.oc.jobseekers.Jobseeker;
import com.ladders.oc.recruiters.JobPosting;

class ApplicationFilter
{
  private JobPosting jobPosting;
  private Jobseeker jobseeker = null;
  private Date date = null;
  
  public ApplicationFilter byJobPosting(JobPosting jobPosting)
  {
    this.jobPosting = jobPosting;
    return this;
  }

  public ApplicationFilter byJobseeker(Jobseeker jobseeker)
  {
    this.jobseeker = jobseeker;
    return this;
  }

  public ApplicationFilter byDate(Date date)
  {
    this.date = date;
    return this;
  }

  public boolean pass(Application application)
  {
    if ((jobPosting != null) && (!application.containsJobPosting(jobPosting)))
      return false;
    
    if ((jobseeker != null) && (!application.containsJobseeker(jobseeker)))
      return false;
      
    if ((date != null) && (!application.containsDate(date)))
      return false;
    
    return true;
  }


}
