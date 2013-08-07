package com.ladders.oc.jobs;

import com.ladders.oc.displayables.DisplayableJob;
import com.ladders.oc.displayers.JobDisplayer;

/**
 * Abstract base class for all job types.
 */
public abstract class Job implements DisplayableJob
{

  protected final JobTitle title;

  Job(JobTitle title) 
  {
    this.title = title;
  }
  
  @Override
  public void displayTo(JobDisplayer displayer)
 {
   displayer.displayJob(title);
 }

  public abstract boolean RequiresResume();

}
