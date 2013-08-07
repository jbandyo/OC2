package com.ladders.oc.jobs;

public class JReqJob extends Job
{

  public JReqJob(JobTitle jobTitle)
  {
    super(jobTitle);
  }

  @Override
  public boolean RequiresResume()
  {
    return true;
  }

}
