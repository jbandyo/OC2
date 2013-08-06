package com.ladders.oc.displayers;

import com.ladders.oc.jobs.JobTitle;

public class ConsoleJobDisplayer implements JobDisplayer
{

  @Override
  public void displayJob(JobTitle title)
  {
    JobTitleDisplayer titleDisplayer = new ConsoleJobTitleDisplayer();
    title.displayTo(titleDisplayer);
 
  }

}
