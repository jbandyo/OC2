package com.ladders.oc.applications;

import java.util.Date;

import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobseekers.Jobseeker;

class ApplicationFilter
{
  private Job job = null;
  private Jobseeker jobseeker = null;
  private Date date = null;
  
  public void setJobFilter(Job job)
  {
    this.job = job;    
  }

  public void setJobseekerFilter(Jobseeker jobseeker)
  {
    this.jobseeker = jobseeker;
  }

  public void setDate(Date date)
  {
    this.date = date;    
  }

  public boolean pass(Application app)
  {
    if ((job != null) && (!app.containsJob(job)))
      return false;
    
    if ((jobseeker != null) && (!app.containsJobseeker(jobseeker)))
      return false;
      
    if ((date != null) && (!app.containsDate(date)))
      return false;
    
    return true;
  }

}
