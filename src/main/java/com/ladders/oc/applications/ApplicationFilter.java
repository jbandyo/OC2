package com.ladders.oc.applications;

import java.util.Date;

import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobseekers.Jobseeker;
import com.theladders.confident.Maybe;

class ApplicationFilter
{
  private Job job = null;
  private Jobseeker jobseeker = null;
  private Date date = null;
  
  public ApplicationFilter byJob(Job job)
  {
    this.job = job;
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
    if ((job != null) && (!application.containsJob(job)))
      return false;
    
    if ((jobseeker != null) && (!application.containsJobseeker(jobseeker)))
      return false;
      
    if ((date != null) && (!application.containsDate(date)))
      return false;
    
    return true;
  }

}
