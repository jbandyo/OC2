package com.ladders.oc.jobseekers;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import com.ladders.oc.jobs.ATSJob;
import com.ladders.oc.jobs.JReqJob;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobs.JobTitle;

public class JobseekerTest
{

  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {}

  @Test
  public void jobseekerCanSaveATSJob()
  {
    Job job1 = new ATSJob(new JobTitle("Developer"));    
    Jobseeker jobseeker = new Jobseeker();    
    jobseeker.saveJob(job1);
  }

  @Test
  public void jobseekerCanSaveJReqJob()
  {
    Job job1 = new JReqJob(new JobTitle("Programmer"));    
    Jobseeker jobseeker = new Jobseeker();    
    jobseeker.saveJob(job1);
  }

}
