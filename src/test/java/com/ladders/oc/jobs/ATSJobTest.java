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
    Job job = ATSJob.titled("Developer");
    JobDisplayer jobDisplayer = new ConsoleJobDisplayer();
    System.out.println("Display Job:");
    job.displayTo(jobDisplayer);
    System.out.println();
  }

}
