package com.ladders.oc.jobs;

import org.junit.AfterClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.ladders.oc.displayables.DisplayableJobTitle;
import com.ladders.oc.displayers.JobDisplayer;

public class JReqJobTest
{

  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {}

  @Test
  public void jobsAreDisplayedByTitle()
  {
    Job job = JReqJob.titled("Programmer");
    JobDisplayer jobDisplayer = Mockito.mock(JobDisplayer.class);
    job.displayTo(jobDisplayer);
    
    DisplayableJobTitle title = new JobTitle("Programmer");
    Mockito.verify(jobDisplayer).displayJob(title);
  }

}
