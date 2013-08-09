package com.ladders.oc.jobs;

public class JReqJob extends Job
{
  
  public static JReqJob titled(String title)
  {
    return new JReqJob(new JobTitle(title));
  }
  
  private JReqJob(JobTitle jobTitle)
  {
    super(jobTitle);
  }

  @Override
  public boolean RequiresResume()
  {
    return true;
  }

}
