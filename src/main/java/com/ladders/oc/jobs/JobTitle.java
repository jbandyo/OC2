package com.ladders.oc.jobs;

import com.ladders.oc.displayers.JobTitleDisplayer;

public class JobTitle
{

  private final String title;

  /**
   * Constructor.
   * @param _title    a string that describes Job Title
   */
  public JobTitle(String title)
  {
    this.title = title;
  }

  public void displayTo(JobTitleDisplayer titleDisplayer)
  {
    titleDisplayer.displayJobTitle(title);
    
  }

}
