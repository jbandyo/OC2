package com.ladders.oc.jobs;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import com.ladders.oc.displayers.ConsoleJobDisplayer;
import com.ladders.oc.displayers.JobDisplayer;

public class ATSJobTest
{

  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {}

  @Test
  public void jobsAreDisplayedByTitle()
  {
    Job job = new ATSJob(new JobTitle("Developer"));
    JobDisplayer jobDisplayer = new ConsoleJobDisplayer();
    job.displayTo(jobDisplayer);    
  }

}
