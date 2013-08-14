package com.ladders.oc.reporters;

import com.ladders.oc.displayables.DisplayableName;
import com.ladders.oc.displayers.JobseekerDisplayer;

public class TextJobseekerReporter implements JobseekerDisplayer
{
  private String jobseekerText;
 
  public String getJobseekerText()
  {
    return jobseekerText;
  }
  
  @Override
  public void displayJobseeker(DisplayableName name)
  {
    TextNameReporter nameReporter = new TextNameReporter();
    name.displayTo(nameReporter);
    jobseekerText = nameReporter.getName();
  }

}
