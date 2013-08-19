package com.ladders.oc.recruiters;

import com.ladders.oc.jobs.Job;

public class PostingHelper
{
  private final Recruiter recruiter;
  private final Job job;

  public PostingHelper(Recruiter recruiter,
                       Job job)
  {
    this.recruiter = recruiter;
    this.job = job;
  }

  public JobPosting to(JobRepository jobRepository)
  {
    JobPosting jobPosting = new JobPosting(recruiter, job);
    jobRepository.add(jobPosting);
    return jobPosting;
  }

}
