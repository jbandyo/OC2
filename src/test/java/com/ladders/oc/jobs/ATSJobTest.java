package com.ladders.oc.jobs;

import org.junit.AfterClass;
import org.junit.Assert;
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
  
  @Test
  public void thereCanBemoreThanOneATSJobsWithSameTitle()
  {
    Job developerJob1  = ATSJob.titled("Developer");
    Job developerJob2  = ATSJob.titled("Developer");
    Job developerJob3  = ATSJob.titled("Developer");
    
    Assert.assertNotEquals(developerJob1, developerJob2);
    Assert.assertNotEquals(developerJob1, developerJob3);
    Assert.assertNotEquals(developerJob2, developerJob3);
  }

}
