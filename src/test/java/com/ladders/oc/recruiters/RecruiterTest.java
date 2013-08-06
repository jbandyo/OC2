package com.ladders.oc.recruiters;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import com.ladders.oc.jobs.Job;

public class RecruiterTest
{

  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {}

  @Test
  public void recruiterCanPostJob()
  {
    Recruiter recruiter = new Recruiter();
    Job job = new Job();
    recruiter.postJob(job);
  }

}
