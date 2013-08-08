package com.ladders.oc.jobseekers;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import com.ladders.oc.Name;
import com.ladders.oc.applications.ApplicationProcessor;
import com.ladders.oc.applications.ApplicationRepository;
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
  private ApplicationRepository appRepo;
  private ApplicationProcessor  appProcessor;

  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {}

  private void setupApplicationRepository()
  {
    appRepo = new ApplicationRepository();
    appProcessor = new ApplicationProcessor(appRepo);
  }
  
  @Test
  public void jobseekerCanSaveATSJob()
  {
    Job job1 = new ATSJob(new JobTitle("Developer"));    
    Jobseeker jobseeker = new Jobseeker(new Name("Tom"));    
    jobseeker.saveJob(job1);
  }

  @Test
  public void jobseekerCanSaveJReqJob()
  {
    Job job1 = new JReqJob(new JobTitle("Programmer"));    
    Jobseeker jobseeker = new Jobseeker(new Name("Tom"));    
    jobseeker.saveJob(job1);
  }

  @Test
  public void jobseekerCanListSavedJobs()
  {
    Jobseeker jobseeker = new Jobseeker(new Name("Tom"));    
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
    Jobseeker jobseeker = new Jobseeker(new Name("Tom"));    
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
    setupApplicationRepository();
    Jobseeker jobseeker = new Jobseeker(new Name("Tom"));    
    Job job = new ATSJob(new JobTitle("Developer"));
    boolean applyStatus = jobseeker.applyToJob(appProcessor, job, null);
    assertTrue(applyStatus);
  }

  @Test
  public void jobSeekersCanApplyToJReqJobsWithResume()
  {
    setupApplicationRepository();
    Jobseeker jobseeker = new Jobseeker(new Name("Tom"));    
    Job job = new JReqJob(new JobTitle("Programmer"));
    Resume resume = new Resume(jobseeker);
    boolean applyStatus = jobseeker.applyToJob(appProcessor, job, resume);
    assertTrue(applyStatus);
  }

  @Test
  public void jobSeekersCannotApplyToJReqJobsWithoutResume()
  {
    setupApplicationRepository();
    Jobseeker jobseeker = new Jobseeker(new Name("Tom"));    
    Job job = new JReqJob(new JobTitle("Programmer"));
    boolean applyStatus = jobseeker.applyToJob(appProcessor, job, null);
    assertFalse(applyStatus);
  }

  @Test
  public void jobSeekersCannotApplyToJobsWithOthersResume()
  {
    setupApplicationRepository();
    Jobseeker tom = new Jobseeker(new Name("Tom"));    
    Jobseeker dick = new Jobseeker(new Name("Dick"));    
    Resume dicksResume = new Resume(dick);
    Job job = new JReqJob(new JobTitle("Programmer"));
    boolean applyStatus = tom.applyToJob(appProcessor, job, dicksResume);
    assertFalse(applyStatus);
  }

  @Test
  public void jobSeekersCanApplyToDifferentJobsWithDifferentResume()
  {
    setupApplicationRepository();
    Jobseeker jobseeker = new Jobseeker(new Name("Tom"));    
    Resume architectResume = new Resume(jobseeker);
    Resume developerResume = new Resume(jobseeker);
    Job architectJob = new JReqJob(new JobTitle("Architect"));
    Job developerJob = new JReqJob(new JobTitle("Developer"));
    boolean applyStatus = jobseeker.applyToJob(appProcessor, architectJob, architectResume);
    assertTrue(applyStatus);
    applyStatus = jobseeker.applyToJob(appProcessor, developerJob, developerResume);
    assertTrue(applyStatus);
  }

  @Test
  public void jobSeekersCanListJobsToWhichTheyApplied()
  {
    setupApplicationRepository();
    Jobseeker jobseeker = new Jobseeker(new Name("Tom"));    
    Resume architectResume = new Resume(jobseeker);
    Resume developerResume = new Resume(jobseeker);
    Job architectJob = new JReqJob(new JobTitle("Architect"));
    Job developerJob = new JReqJob(new JobTitle("Developer"));
    Job programmerJob = new ATSJob(new JobTitle("Programmer"));
    boolean applyStatus = jobseeker.applyToJob(appProcessor, architectJob, architectResume);
    assertTrue(applyStatus);
    applyStatus = jobseeker.applyToJob(appProcessor, developerJob, developerResume);
    assertTrue(applyStatus);
    applyStatus = jobseeker.applyToJob(appProcessor, programmerJob, null);
    assertTrue(applyStatus);
    Jobs jobs = jobseeker.getAppliedToJobs();
    assertTrue(jobs.contains(architectJob));
    assertTrue(jobs.contains(developerJob));
    assertTrue(jobs.contains(programmerJob));
  }

  @Test
  public void jobSeekersCanDisplayJobsToWhichTheyApplied()
  {
    setupApplicationRepository();
    Jobseeker jobseeker = new Jobseeker(new Name("Tom"));    
    Resume architectResume = new Resume(jobseeker);
    Resume developerResume = new Resume(jobseeker);
    Job architectJob = new JReqJob(new JobTitle("Architect"));
    Job developerJob = new JReqJob(new JobTitle("Developer"));
    Job programmerJob = new ATSJob(new JobTitle("Programmer"));
    boolean applyStatus = jobseeker.applyToJob(appProcessor, architectJob, architectResume);
    assertTrue(applyStatus);
    applyStatus = jobseeker.applyToJob(appProcessor, developerJob, developerResume);
    assertTrue(applyStatus);
    applyStatus = jobseeker.applyToJob(appProcessor, programmerJob, null);
    assertTrue(applyStatus);
    Jobs jobs = jobseeker.getAppliedToJobs();
    JobsDisplayer jobsDisplayer = new ConsoleJobsDisplayer();
    jobs.displayTo(jobsDisplayer);
  }

}
