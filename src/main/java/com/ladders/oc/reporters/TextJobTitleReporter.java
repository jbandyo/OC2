package com.ladders.oc.reporters;

import com.ladders.oc.displayers.JobTitleDisplayer;

public class TextJobTitleReporter implements JobTitleDisplayer
{
  private String title;
  
  public String getTitle()
  {
    return title;
  }
  
  @Override
  public void displayJobTitle(String title)
  {
    this.title = title;
  }

}
