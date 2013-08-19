package com.ladders.oc.recruiters;

import java.util.HashSet;
import java.util.Set;

import com.ladders.oc.applications.ApplicationQueryHelper;
import com.ladders.oc.displayables.DisplayableJobPostings;
import com.ladders.oc.displayers.JobPostingsDisplayer;
import com.ladders.oc.jobs.Job;

public class JobPostings implements DisplayableJobPostings
{
  private final Set<JobPosting> jobPostingSet = new HashSet<JobPosting>();

  public void add(JobPosting posting)
  {
    jobPostingSet.add(posting);
  }

  public boolean contains(JobPosting jobPosting)
  {
    return jobPostingSet.contains(jobPosting);
  }

  public boolean contains(Job job)
  {
    for (JobPosting posting : jobPostingSet)
    {
      if (posting.contains(job))
        return true;
    }
    return false;
  }

  @Override
  public void displayTo(JobPostingsDisplayer postingsDisplayer)
  {
    postingsDisplayer.displayJobPostings(jobPostingSet);
    
  }

  public ApplicationQueryHelper thatReceivedApllications()
  {
    // TODO Auto-generated method stub
    return null;
  }


}
