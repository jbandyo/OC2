package com.ladders.oc.jobseekers;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import com.ladders.oc.applications.ApplicationProcessor;
import com.ladders.oc.displayers.ConsoleJobsDisplayer;
import com.ladders.oc.displayers.JobsDisplayer;
import com.ladders.oc.jobs.ATSJob;
import com.ladders.oc.jobs.JReqJob;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobs.JobTitle;
import com.ladders.oc.jobs.Jobs;
import com.ladders.oc.resumes.Resume;

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
  public void jobSeekersCanApplyToATSJobsWithoutResume()
  {
    ApplicationProcessor appProcessor = new ApplicationProcessor();
    Jobseeker jobseeker = new Jobseeker();    
    Job job = new ATSJob(new JobTitle("Developer"));
    boolean success = jobseeker.applyToJob(appProcessor, job, null);
    assertTrue(success);
  }

  @Test
  public void jobSeekersCanApplyToJReqJobsWithResume()
  {
    ApplicationProcessor appProcessor = new ApplicationProcessor();
    Jobseeker jobseeker = new Jobseeker();    
    Job job = new JReqJob(new JobTitle("Programmer"));
    Resume resume = new Resume(jobseeker);
    boolean success = jobseeker.applyToJob(appProcessor, job, resume);
    assertTrue(success);
  }

  @Test
  public void jobSeekersCannotApplyToJReqJobsWithoutResume()
  {
    ApplicationProcessor appProcessor = new ApplicationProcessor();
    Jobseeker jobseeker = new Jobseeker();    
    Job job = new JReqJob(new JobTitle("Programmer"));
    boolean success = jobseeker.applyToJob(appProcessor, job, null);
    assertFalse(success);
  }

  @Test
  public void jobSeekersCannotApplyToJobsWithOthersResume()
  {
    ApplicationProcessor appProcessor = new ApplicationProcessor();
    Jobseeker tom = new Jobseeker();    
    Resume tomsResume = new Resume(tom);
    Jobseeker dick = new Jobseeker();    
    Resume dicksResume = new Resume(dick);
    Job job = new JReqJob(new JobTitle("Programmer"));
    boolean success = tom.applyToJob(appProcessor, job, dicksResume);
    assertFalse(success);
  }

  @Test
  public void jobSeekersCanApplyToDifferentJobsWithDifferentResume()
  {
    ApplicationProcessor appProcessor = new ApplicationProcessor();
    Jobseeker jobseeker = new Jobseeker();   
    Resume architectResume = new Resume(jobseeker);
    Resume developerResume = new Resume(jobseeker);
    Job architectJob = new JReqJob(new JobTitle("Architect"));
    Job developerJob = new JReqJob(new JobTitle("Developer"));
    boolean success = jobseeker.applyToJob(appProcessor, architectJob, architectResume);
    assertTrue(success);
    success = jobseeker.applyToJob(appProcessor, developerJob, developerResume);
    assertTrue(success);
  }
  
}
