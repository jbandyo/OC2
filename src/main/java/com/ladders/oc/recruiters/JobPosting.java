package com.ladders.oc.recruiters;

import com.ladders.oc.displayables.DisplayableJobPosting;
import com.ladders.oc.displayers.JobPostingDisplayer;
import com.ladders.oc.jobs.Job;

public class JobPosting implements DisplayableJobPosting
{
  private final Recruiter recruiter;
  private final Job job;

  public JobPosting(Recruiter recruiter,
                    Job job)
  {
    this.recruiter = recruiter;
    this.job = job;
  }

  public boolean contains(Recruiter recruiter)
  {
    return this.recruiter == recruiter;
  }

  public boolean contains(Job job)
  {
    return this.job == job;
  }

  public boolean RequiresResume()
  {
    return job.RequiresResume();
  }

  @Override
  public boolean equals(Object o)
  {
    if (o == this)
      return true;
    if (o == null)
      return false;
    if (!(o instanceof JobPosting))
      return false;
    JobPosting posting = (JobPosting)o;
    return (posting.recruiter == recruiter) && (posting.job == job);
  }

  @Override
  public int hashCode()
  {
    return 31*recruiter.hashCode() + job.hashCode();
  }
  
  @Override
  public void displayTo(JobPostingDisplayer displayer)
  {
    displayer.displayJobPosting(recruiter, job);
    
  }

}
