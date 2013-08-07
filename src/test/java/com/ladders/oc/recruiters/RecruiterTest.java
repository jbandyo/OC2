package com.ladders.oc.recruiters;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import com.ladders.oc.Name;
import com.ladders.oc.displayers.ConsoleRecruiterDisplayer;
import com.ladders.oc.displayers.RecruiterDisplayer;
import com.ladders.oc.jobs.ATSJob;
import com.ladders.oc.jobs.JReqJob;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobs.JobTitle;
import com.ladders.oc.jobs.Jobs;

public class RecruiterTest
{

  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {}

  @Test
  public void recruiterCanPostATSJob()
  {
    Recruiter recruiter = new Recruiter(new Name("George"));
    Job job = new ATSJob(new JobTitle("Developer"));
    recruiter.postJob(job);
  }

  @Test
  public void recruiterCanPostJReqJob()
  {
    Recruiter recruiter = new Recruiter(new Name("George"));
    Job job = new JReqJob(new JobTitle("Developer"));
    recruiter.postJob(job);
  }

  @Test
  public void recruitersAreDisplayedByName()
  {
    Recruiter recruiter = new Recruiter(new Name("George"));
    RecruiterDisplayer recDisplayer = new ConsoleRecruiterDisplayer();
    recruiter.displayTo(recDisplayer);    
  }

  @Test
  public void recruitersCanSeeJobsTheyPosted()
  {
    Recruiter recruiter = new Recruiter(new Name("George"));
    
    Job job1 = new ATSJob(new JobTitle("Developer"));
    recruiter.postJob(job1);
    Job job2 = new ATSJob(new JobTitle("Architect"));
    recruiter.postJob(job2);
    Job job3 = new JReqJob(new JobTitle("Programmer"));
    recruiter.postJob(job3);
    Jobs jobs = recruiter.listPostedJobs();
    assertTrue(jobs.contains(job1));
    assertTrue(jobs.contains(job2));
    assertTrue(jobs.contains(job3));
  }
  
}
