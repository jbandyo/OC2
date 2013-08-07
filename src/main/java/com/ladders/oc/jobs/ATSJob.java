package com.ladders.oc.jobs;

public class ATSJob extends Job
{

  public ATSJob(JobTitle jobTitle)
  {
    super(jobTitle);
  }

  @Override
  public boolean RequiresResume()
  {
    return false;
  }

}
