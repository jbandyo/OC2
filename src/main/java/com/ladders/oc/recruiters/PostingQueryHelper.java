package com.ladders.oc.recruiters;

public class PostingQueryHelper
{
  private final Recruiter recruiter;

  public PostingQueryHelper(Recruiter recruiter)
  {
    this.recruiter = recruiter;
  }

  public JobPostings from(JobRepository jobRepository)
  {
    return jobRepository.getJobsPostedBy(recruiter);
  }

}
