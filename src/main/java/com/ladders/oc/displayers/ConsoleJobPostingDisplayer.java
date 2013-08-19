package com.ladders.oc.displayers;

import com.ladders.oc.displayables.DisplayableJob;
import com.ladders.oc.displayables.DisplayableRecruiter;

public class ConsoleJobPostingDisplayer implements JobPostingDisplayer
{

  @Override
  public void displayJobPosting(DisplayableRecruiter recruiter,
                                DisplayableJob job)
  {
    JobDisplayer jobDisplayer = new ConsoleJobDisplayer();
    job.displayTo(jobDisplayer);
    System.out.print(" by ");
    RecruiterDisplayer recruiterDispolayer = new ConsoleRecruiterDisplayer();
    recruiter.displayTo(recruiterDispolayer); 
  }

}
