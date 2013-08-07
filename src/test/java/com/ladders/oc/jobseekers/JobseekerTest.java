package com.ladders.oc.jobseekers;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import com.ladders.oc.displayers.ConsoleJobsDisplayer;
import com.ladders.oc.displayers.JobsDisplayer;
import com.ladders.oc.jobs.ATSJob;
import com.ladders.oc.jobs.JReqJob;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobs.JobTitle;
import com.ladders.oc.jobs.Jobs;

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

  @Test
  public void jobseekerCanListSavedJobs()
  {
    Jobseeker jobseeker = new Jobseeker();    
    Job job1 = new ATSJob(new JobTitle("Developer"));
    jobseeker.saveJob(job1);
    Job job2 = new ATSJob(new JobTitle("Architect"));
    jobseeker.saveJob(job2);
    Job job3 = new JReqJob(new JobTitle("Programmer"));
    jobseeker.saveJob(job3);
    Jobs jobs = jobseeker.getSavedJobs();
    assertTrue(jobs.contains(job1));
    assertTrue(jobs.contains(job2));
    assertTrue(jobs.contains(job3));
  }
  
  @Test
  public void jobSeekersCanDisplayJobsTheySaved()
  {
    Jobseeker jobseeker = new Jobseeker();    
    Job job1 = new ATSJob(new JobTitle("Developer"));
    jobseeker.saveJob(job1);
    Job job2 = new ATSJob(new JobTitle("Architect"));
    jobseeker.saveJob(job2);
    Job job3 = new JReqJob(new JobTitle("Programmer"));
    jobseeker.saveJob(job3);
    Jobs jobs = jobseeker.getSavedJobs();
    JobsDisplayer jobsDisplayer = new ConsoleJobsDisplayer();
    jobs.displayTo(jobsDisplayer);
  }

  @Test
  public void jobSeekersCanApplyToATSJobs()
  {
    Jobseeker jobseeker = new Jobseeker();    
    Job job1 = new ATSJob(new JobTitle("Developer"));
    jobseeker.applyToJob(job1);
  }
  
}
