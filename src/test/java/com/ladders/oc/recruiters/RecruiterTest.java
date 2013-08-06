package com.ladders.oc.recruiters;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import com.ladders.oc.jobs.ATSJob;
import com.ladders.oc.jobs.JReqJob;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobs.JobTitle;

public class RecruiterTest
{

  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {}

  @Test
  public void recruiterCanPostATSJob()
  {
    Recruiter recruiter = new Recruiter();
    Job job = new ATSJob(new JobTitle("Developer"));
    recruiter.postJob(job);
  }

  @Test
  public void recruiterCanPostJReqJob()
  {
    Recruiter recruiter = new Recruiter();
    Job job = new JReqJob(new JobTitle("Developer"));
    recruiter.postJob(job);
  }

}
