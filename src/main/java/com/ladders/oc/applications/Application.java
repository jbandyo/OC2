package com.ladders.oc.applications;

import java.util.Date;

import com.ladders.oc.displayables.DisplayableApplication;
import com.ladders.oc.displayers.ApplicationDisplayer;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobseekers.Jobseeker;

public class Application implements DisplayableApplication
{
  private final Job   job;
  private final Jobseeker  jobseeker;
  private final Date  date;

  public Application(Job job,
                     Jobseeker jobseeker)
  {
    this.job = job;
    this.jobseeker = jobseeker;
    date = new Date();
  }

  public boolean containsJob(Job job)
  {
    return this.job == job;
  }

  public boolean containsJobseeker(Jobseeker jobseeker)
  {
    return this.jobseeker == jobseeker;
  }

  public boolean containsDate(Date queryDate)
  {
    return AppDateComparator.isEqual(date, queryDate);
  }

  @Override
  public void displayTo(ApplicationDisplayer appDisplayer)
  {
    appDisplayer.displayApplication(job, jobseeker, date);
  }

}
