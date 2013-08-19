package com.ladders.oc.applications;

import java.util.Date;

import com.ladders.oc.displayables.DisplayableApplication;
import com.ladders.oc.displayers.ApplicationDisplayer;
import com.ladders.oc.jobseekers.Jobseeker;
import com.ladders.oc.recruiters.JobPosting;

public class Application implements DisplayableApplication
{
  private final JobPosting jobPosting;
  private final Jobseeker  jobseeker;
  private final Date  date;

  public Application(JobPosting jobPosting,
                     Jobseeker  jobseeker,
                     Date date)
  {
    this.jobPosting = jobPosting;
    this.jobseeker  = jobseeker;
    this.date = date;
  }

  public boolean containsJobPosting(JobPosting jobPosting)
  {
    return this.jobPosting == jobPosting;
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
    appDisplayer.displayApplication(jobPosting, jobseeker, date);
  }

}
