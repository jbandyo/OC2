package com.ladders.oc.jobs;

import org.junit.AfterClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.ladders.oc.displayables.DisplayableJobTitle;
import com.ladders.oc.displayers.JobDisplayer;

public class ATSJobTest
{

  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {}

  @Test
  public void jobsAreDisplayedByTitle()
  {
    Job job = ATSJob.titled("Developer");
    JobDisplayer jobDisplayer = Mockito.mock(JobDisplayer.class);
    job.displayTo(jobDisplayer);
    
    DisplayableJobTitle title = new JobTitle("Developer");
    Mockito.verify(jobDisplayer).displayJob(title);

  }

}
