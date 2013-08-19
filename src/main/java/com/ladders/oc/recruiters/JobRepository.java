package com.ladders.oc.recruiters;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class JobRepository
{
  private final Set<JobPosting> jobPostingSet = Collections.synchronizedSet(new HashSet<JobPosting>());

  public void add(JobPosting jobPosting)
  {
    jobPostingSet.add(jobPosting);
  }

  public JobPostings getJobsPostedBy(Recruiter recruiter)
  {
    JobPostings postings = new JobPostings();
    synchronized (jobPostingSet)
    {
      for (JobPosting posting : jobPostingSet)
      {
        if (posting.contains(recruiter))
          postings.add(posting);
      }
      return postings;
    }
  }

}
