package com.ladders.oc.jobs;

import com.ladders.oc.displayables.DisplayableJobTitle;
import com.ladders.oc.displayers.JobTitleDisplayer;

public class JobTitle implements DisplayableJobTitle
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

  public boolean equals(Object o)
  {
    JobTitle otherTitle = (JobTitle)o;
    return title.equals(otherTitle.title);
  }
  
  @Override
  public void displayTo(JobTitleDisplayer titleDisplayer)
  {
    titleDisplayer.displayJobTitle(title);
    
  }

}
