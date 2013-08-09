package com.ladders.oc.jobs;

public class ATSJob extends Job
{

  public static ATSJob titled(String title)
  {
    return new ATSJob(new JobTitle(title));
  }
  
  private ATSJob(JobTitle jobTitle)
  {
    super(jobTitle);
  }

  @Override
  public boolean RequiresResume()
  {
    return false;
  }

}
