package com.ladders.oc.applications;

import java.util.Date;

import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobseekers.Jobseeker;
import com.theladders.confident.Maybe;

class ApplicationFilter
{
  private Maybe<Job> job = Maybe.nothing();
  private Maybe<Jobseeker> jobseeker = Maybe.nothing();
  private Maybe<Date> date = Maybe.nothing();
  
  public ApplicationFilter byJob(Job job)
  {
    this.job = Maybe.just(job);
    return this;
  }

  public ApplicationFilter byJobseeke(Jobseeker jobseeker)
  {
    this.jobseeker = Maybe.just(jobseeker);
    return this;
  }

  public ApplicationFilter byDate(Date date)
  {
    this.date = Maybe.just(date);
    return this;
  }

  public boolean pass(Application application)
  {
    if ((job.isSomething()) && (!application.containsJob(job.get())))
      return false;
    
    if ((jobseeker.isSomething()) && (!application.containsJobseeker(jobseeker.get())))
      return false;
      
    if ((date.isSomething()) && (!application.containsDate(date.get())))
      return false;
    
    return true;
  }

}
