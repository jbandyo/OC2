package com.ladders.oc.displayers;

import java.util.Date;

import com.ladders.oc.displayables.DisplayableJobPosting;
import com.ladders.oc.displayables.DisplayableJobseeker;

public class ConsoleApplicationJobseekerDisplayer implements ApplicationDisplayer
{

  @Override
  public void displayApplication(DisplayableJobPosting job,
                                 DisplayableJobseeker  jobseeker,
                                 Date date)
  {
    JobseekerDisplayer displayer = new ConsoleJobseekerDisplayer();
    jobseeker.displayTo(displayer);
  }

}
