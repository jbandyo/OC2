package com.ladders.oc.reporters;

import com.ladders.oc.displayables.DisplayableJobTitle;
import com.ladders.oc.displayers.JobDisplayer;

public class TextJobReporter implements JobDisplayer
{
  private String jobText;
  
  public String getJobText()
  {
    return jobText;
  }
  
  @Override
  public void displayJob(DisplayableJobTitle title)
  {
    TextJobTitleReporter titleReporter = new TextJobTitleReporter();
    title.displayTo(titleReporter);
    jobText = titleReporter.getTitle();
  }

}
