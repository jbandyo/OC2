package com.ladders.oc.jobseekers;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import com.ladders.oc.Name;
import com.ladders.oc.applications.ApplicationProcessor;
import com.ladders.oc.applications.ApplicationRepository;
import com.ladders.oc.applications.Applications;
import com.ladders.oc.displayers.ApplicationsDisplayer;
import com.ladders.oc.displayers.ConsoleApplicationsJobsDisplayer;
import com.ladders.oc.displayers.ConsoleApplicationsJobseekerDisplayer;
import com.ladders.oc.displayers.ConsoleJobsDisplayer;
import com.ladders.oc.displayers.JobsDisplayer;
import com.ladders.oc.jobs.ATSJob;
import com.ladders.oc.jobs.JReqJob;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobs.JobTitle;
import com.ladders.oc.jobs.Jobs;
import com.ladders.oc.recruiters.Recruiter;
import com.ladders.oc.resumes.Resume;
import com.theladders.confident.Maybe;

public class JobseekerTest
{
  private ApplicationRepository appRepo;
  private ApplicationProcessor  appProcessor;
  private Job programmerJob;
  private Job developerJob;
  private Job architectJob;

  @Test
  public void jobseekerCanSaveJob()
  {
    Job job1 = ATSJob.titled("Developer");    
    Jobseeker jobseeker = Jobseeker.named("Tom");    
    jobseeker.saveJob(job1);
  }

  @Test
  public void jobseekerCanListSavedJobs()
  {
    setupJobs();
    Jobseeker jobseeker = Jobseeker.named("Tom");    
    jobseeker.saveJob(programmerJob);
    jobseeker.saveJob(developerJob);
    jobseeker.saveJob(architectJob);
    Jobs jobs = jobseeker.getSavedJobs();
    assertTrue(jobs.contains(programmerJob));
    assertTrue(jobs.contains(developerJob));
    assertTrue(jobs.contains(architectJob));
  }
  
  @Test
  public void jobSeekersCanDisplayJobsTheySaved()
  {
    setupJobs();
    Jobseeker jobseeker = Jobseeker.named("Tom");    
    jobseeker.saveJob(programmerJob);
    jobseeker.saveJob(developerJob);
    jobseeker.saveJob(architectJob);
    Jobs jobs = jobseeker.getSavedJobs();
    JobsDisplayer jobsDisplayer = new ConsoleJobsDisplayer();
    System.out.println("Display saved Jobs:");
    jobs.displayTo(jobsDisplayer);
    System.out.println();
  }

  @Test
  public void jobSeekersCanApplyToATSJobsWithoutResume()
  {
    setupApplicationRepository();
    Jobseeker jobseekerTom = Jobseeker.named("Tom");    
    Job developerJob = ATSJob.titled("Developer");
    boolean applyStatus = jobseekerTom.applyFor(developerJob).to(appProcessor);
    assertTrue(applyStatus);
  }

  @Test
  public void jobSeekersCanApplyToJReqJobsWithResume()
  {
    setupApplicationRepository();
    Jobseeker jobseekerTom = Jobseeker.named("Tom");    
    Job programmerJob = JReqJob.titled("Programmer");
    Resume resume = Resume.createdBy(jobseekerTom);
    boolean applyStatus = jobseekerTom.applyFor(programmerJob).with(resume).to(appProcessor);
    assertTrue(applyStatus);
  }

  @Test
  public void jobSeekersCannotApplyToJReqJobsWithoutResume()
  {
    setupApplicationRepository();
    Jobseeker jobseekerTom = Jobseeker.named("Tom");    
    Job programmerJob = JReqJob.titled("Programmer");
    boolean applyStatus = jobseekerTom.applyFor(programmerJob).to(appProcessor);
    assertFalse(applyStatus);
  }

  @Test
  public void jobSeekersCannotApplyToJobsWithOthersResume()
  {
    setupApplicationRepository();
    Jobseeker jobseekerTom = Jobseeker.named("Tom");    
    Jobseeker jobseekerDick = Jobseeker.named("Dick");    
    Resume dicksResume = Resume.createdBy(jobseekerDick);
    Job programmerJob = JReqJob.titled("Programmer");
    boolean applyStatus = jobseekerTom.applyFor(programmerJob).with(dicksResume).to(appProcessor);
    assertFalse(applyStatus);
  }

  @Test
  public void jobSeekersCanApplyToDifferentJobsWithDifferentResume()
  {
    setupJobs();
    setupApplicationRepository();
    Jobseeker jobseekerTom = Jobseeker.named("Tom");    
    Resume architectResume = Resume.createdBy(jobseekerTom);
    Resume developerResume = Resume.createdBy(jobseekerTom);
    
    boolean applyStatus;
    applyStatus = jobseekerTom.applyFor(architectJob).with(architectResume).to(appProcessor);
    assertTrue(applyStatus);
    
    applyStatus = jobseekerTom.applyFor(developerJob).with(developerResume).to(appProcessor);
    assertTrue(applyStatus);
  }

  @Test
  public void jobSeekersCanListJobsToWhichTheyApplied()
  {
    setupApplicationRepository();
    setupJobs();
    Jobseeker jobseekerTom = Jobseeker.named("Tom");    
    Resume architectResume = Resume.createdBy(jobseekerTom);
    Resume developerResume = Resume.createdBy(jobseekerTom);
    
    boolean applyStatus;
    applyStatus = jobseekerTom.applyFor(architectJob).with(architectResume).to(appProcessor);
    assertTrue(applyStatus);

    applyStatus = jobseekerTom.applyFor(developerJob).with(developerResume).to(appProcessor);
    assertTrue(applyStatus);

    applyStatus = jobseekerTom.applyFor(programmerJob).to(appProcessor);
    assertTrue(applyStatus);
    
    Applications applications = jobseekerTom.getJobsAppliedTo().from(appRepo);
    //assertTrue(jobs.contains(architectJob));
    //assertTrue(jobs.contains(developerJob));
    //assertTrue(jobs.contains(programmerJob));
  }

  @Test
  public void jobSeekersCanDisplayJobsToWhichTheyApplied()
  {
    setupApplicationRepository();
    setupJobs();
    Jobseeker jobseekerTom = Jobseeker.named("Tom");    
    Resume architectResume = Resume.createdBy(jobseekerTom);
    Resume developerResume = Resume.createdBy(jobseekerTom);
    
    boolean applyStatus;
    applyStatus = jobseekerTom.applyFor(architectJob).with(architectResume).to(appProcessor);
    assertTrue(applyStatus);

    applyStatus = jobseekerTom.applyFor(developerJob).with(developerResume).to(appProcessor);
    assertTrue(applyStatus);

    applyStatus = jobseekerTom.applyFor(programmerJob).to(appProcessor);
    assertTrue(applyStatus);
    
    Applications applications = jobseekerTom.getJobsAppliedTo().from(appRepo);
    ApplicationsDisplayer appsDisplayer = new ConsoleApplicationsJobsDisplayer();
    System.out.println("Display Jobs applied to by Tom:");
    applications.displayTo(appsDisplayer);
    System.out.println();
  }

  private void setupApplicationRepository()
  {
    appRepo = new ApplicationRepository();
    appProcessor = new ApplicationProcessor(appRepo);
  }
  
  private void setupJobs()
  {
    developerJob = JReqJob.titled("Developer");
    architectJob = JReqJob.titled("Architect");
    programmerJob = ATSJob.titled("Programmer");
  }

}
