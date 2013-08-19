package com.ladders.oc.reporters;

import com.ladders.oc.displayables.DisplayableJob;
import com.ladders.oc.displayables.DisplayableRecruiter;
import com.ladders.oc.displayers.JobPostingDisplayer;

public class TextJobPostingReporter implements JobPostingDisplayer
{
  private String jobPostingText = "";

  public String getJobPostingText()
  {
    return jobPostingText;
  }
  
  @Override
  public void displayJobPosting(DisplayableRecruiter recruiter,
                                DisplayableJob job)
  {
    TextJobReporter jobReporter = new TextJobReporter();
    job.displayTo(jobReporter);
    jobPostingText += jobReporter.getJobText();
    jobPostingText += " by ";
    TextRecruiterReporter recruiterReporter = new TextRecruiterReporter();
    recruiter.displayTo(recruiterReporter);
    jobPostingText += recruiterReporter.getRecruiterText();
  }

}
