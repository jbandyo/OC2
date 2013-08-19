package com.ladders.oc.displayers;

import java.util.Date;

import com.ladders.oc.displayables.DisplayableJobPosting;
import com.ladders.oc.displayables.DisplayableJobseeker;

public class ConsoleApplicationJobPostingDisplayer implements ApplicationDisplayer
{

  @Override
  public void displayApplication(DisplayableJobPosting jobPosting,
                                 DisplayableJobseeker  jobseeker,
                                 Date date)
  {
    JobPostingDisplayer displayer = new ConsoleJobPostingDisplayer();
    jobPosting.displayTo(displayer);

  }

}
