package com.ladders.oc.recruiters;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import com.ladders.oc.Name;
import com.ladders.oc.applications.ApplicationProcessor;
import com.ladders.oc.applications.ApplicationRepository;
import com.ladders.oc.applications.Applications;
import com.ladders.oc.displayers.ApplicationsDisplayer;
import com.ladders.oc.displayers.ConsoleApplicationsJobseekerDisplayer;
import com.ladders.oc.displayers.ConsoleJobsDisplayer;
import com.ladders.oc.displayers.ConsoleRecruiterDisplayer;
import com.ladders.oc.displayers.JobsDisplayer;
import com.ladders.oc.displayers.RecruiterDisplayer;
import com.ladders.oc.jobs.ATSJob;
import com.ladders.oc.jobs.JReqJob;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobs.JobTitle;
import com.ladders.oc.jobs.Jobs;
import com.ladders.oc.jobseekers.Jobseeker;
import com.ladders.oc.resumes.Resume;
import com.theladders.confident.Maybe;

public class RecruiterTest
{
  private ApplicationRepository appRepo;
  private ApplicationProcessor  appProcessor;
  private Job programmerJob;
  private Job developerJob;
  private Job architectJob;
  private Jobseeker jobseekerTom;    
  private Jobseeker jobseekerDick;    
  private Jobseeker jobseekerHarry;    
  private Recruiter recruiter;

  @Test
  public void recruiterCanPostATSJob()
  {
    Recruiter recruiter = Recruiter.named("George");
    Job developerJob = ATSJob.titled("Developer");
    recruiter.post(developerJob);
  }

  @Test
  public void recruiterCanPostJReqJob()
  {
    Recruiter recruiter = Recruiter.named("George");
    Job developerJob = JReqJob.titled("Developer");
    recruiter.post(developerJob);
  }

  @Test
  public void recruitersAreDisplayedByName()
  {
    Recruiter recruiter = Recruiter.named("George");
    RecruiterDisplayer recDisplayer = new ConsoleRecruiterDisplayer();
    System.out.println("Display Recruiter");
    recruiter.displayTo(recDisplayer);    
    System.out.println();
  }

  @Test
  public void recruitersCanListJobsTheyPosted()
  {
    setupActors();
    recruiter.post(developerJob);
    recruiter.post(architectJob);
    
    Jobs jobs = recruiter.getPostedJobs();
    
    assertTrue(jobs.contains(developerJob));
    assertTrue(jobs.contains(architectJob));
    assertFalse(jobs.contains(programmerJob));
  }

  @Test
  public void recruitersCanDisplayJobsTheyPosted()
  {
    setupActors();
    recruiter.post(developerJob);
    recruiter.post(architectJob);
    recruiter.post(programmerJob);
 
    Jobs jobs = recruiter.getPostedJobs();
    
    JobsDisplayer jobsDisplayer = new ConsoleJobsDisplayer();
    System.out.println("Posted Jobs");
    jobs.displayTo(jobsDisplayer);
    System.out.println();
  }
  
  @Test
  public void recruitersCanSeeJobseekersByJob()
  {
    setupActors();
    recruiter.post(developerJob);
    recruiter.post(architectJob);
    
    setupApplicationRepository();
    boolean applyStatus;

    applyStatus = jobseekerTom.applyFor(developerJob).to(appProcessor);
    assertTrue(applyStatus);
    applyStatus = jobseekerTom.applyFor(architectJob).to(appProcessor);
    assertTrue(applyStatus);
    applyStatus = jobseekerDick.applyFor(architectJob).to(appProcessor);
    assertTrue(applyStatus);
    applyStatus = jobseekerHarry.applyFor(developerJob).to(appProcessor);
    assertTrue(applyStatus);

    Applications developerApps = recruiter.getApplicationsBy(developerJob).from(appRepo);
    assertNotNull(developerApps);

    ApplicationsDisplayer appsDisplayer = new ConsoleApplicationsJobseekerDisplayer();
    System.out.println("Jobseekers for Developer job");
    developerApps.displayTo(appsDisplayer);

    Applications archApplications = recruiter.getApplicationsBy(architectJob).from(appRepo);
    assertNotNull(archApplications);

    System.out.println("Jobseekers for Architect job");
    archApplications.displayTo(appsDisplayer);
  }

  private void setupApplicationRepository()
  {
    appRepo = new ApplicationRepository();
    appProcessor = new ApplicationProcessor(appRepo);
  }
  
  private void setupActors()
  {
    developerJob  = ATSJob.titled("Developer");
    architectJob  = ATSJob.titled("Architect");
    programmerJob = JReqJob.titled("Programmer");
    jobseekerTom   = Jobseeker.named("Tom");   
    jobseekerDick  = Jobseeker.named("Dick");    
    jobseekerHarry = Jobseeker.named("Harry");    
    recruiter      = Recruiter.named("George");
  }

}
