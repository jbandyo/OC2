package com.ladders.oc.jobseekers;

import com.ladders.oc.Name;
import com.ladders.oc.applications.ApplicationQueryHelper;
import com.ladders.oc.displayables.DisplayableJobseeker;
import com.ladders.oc.displayers.JobseekerDisplayer;
import com.ladders.oc.recruiters.JobPosting;
import com.ladders.oc.recruiters.JobPostings;

public class Jobseeker implements DisplayableJobseeker
{

  private final Name name;
  private final JobPostings savedJobPostings = new JobPostings();

  public static Jobseeker named(String name)
  {
    return new Jobseeker(new Name(name));
  }
  
  private Jobseeker (Name name)
  {
    this.name = name;
  }
  
  public void saveJobPosting(JobPosting programmerPosting)
  {
    savedJobPostings.add(programmerPosting);
  }

  public JobPostings getSavedJobPostings()
  {
    return savedJobPostings;
  }

  public ApplicationHelper applyFor(JobPosting jobPosting)
  {
    return new ApplicationHelper(this, jobPosting);
  }

  public ApplicationQueryHelper getJobsAppliedTo()
  {
    ApplicationQueryHelper queryHelper = new ApplicationQueryHelper();
    queryHelper.filterBy(this);
    return queryHelper;
  }

  @Override
  public void displayTo(JobseekerDisplayer jobseekerDisplayer)
  {
    jobseekerDisplayer.displayJobseeker(name);    
  }

}
