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

public class RecruiterTest
{
  private ApplicationRepository appRepo;
  private ApplicationProcessor  appProcessor;
  private Job jobProgrammer;
  private Job jobDeveloper;
  private Job jobArchitect;
  private Jobseeker jobseekerTom;    
  private Jobseeker jobseekerDick;    
  private Jobseeker jobseekerHarry;    
  private Recruiter recruiter;

  @Test
  public void recruiterCanPostATSJob()
  {
    Recruiter recruiter = new Recruiter(new Name("George"));
    Job job = new ATSJob(new JobTitle("Developer"));
    recruiter.postJob(jobDeveloper);
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
    System.out.println("Display Recruiter");
    recruiter.displayTo(recDisplayer);    
    System.out.println();
  }

  @Test
  public void recruitersCanListJobsTheyPosted()
  {
    setupActors();
    recruiter.postJob(jobDeveloper);
    recruiter.postJob(jobArchitect);
    Jobs jobs = recruiter.listPostedJobs();
    assertTrue(jobs.contains(jobDeveloper));
    assertTrue(jobs.contains(jobArchitect));
    assertFalse(jobs.contains(jobProgrammer));
  }

  @Test
  public void recruitersCanDisplayJobsTheyPosted()
  {
    setupActors();
    recruiter.postJob(jobDeveloper);
    recruiter.postJob(jobArchitect);
    recruiter.postJob(jobProgrammer);
    Jobs jobs = recruiter.listPostedJobs();
    JobsDisplayer jobsDisplayer = new ConsoleJobsDisplayer();
    System.out.println("Posted JObs");
    jobs.displayTo(jobsDisplayer);
    System.out.println();
  }
  
  @Test
  public void recruitersCanSeeJobseekersByJob()
  {
    setupActors();
    recruiter.postJob(jobDeveloper);
    recruiter.postJob(jobArchitect);
    
    setupApplicationRepository();
    boolean applyStatus;
    applyStatus = jobseekerTom.applyToJob(appProcessor, jobDeveloper, null);
    assertTrue(applyStatus);
    applyStatus = jobseekerTom.applyToJob(appProcessor, jobArchitect, null);
    assertTrue(applyStatus);
    applyStatus = jobseekerDick.applyToJob(appProcessor, jobArchitect, null);
    assertTrue(applyStatus);
    applyStatus = jobseekerHarry.applyToJob(appProcessor, jobDeveloper, null);
    assertTrue(applyStatus);

    Applications devApplications = recruiter.listApplicationsByJob(appRepo, jobDeveloper);
    assertNotNull(devApplications);

    ApplicationsDisplayer appsDisplayer = new ConsoleApplicationsJobseekerDisplayer();
    System.out.println("Jobseekers for Developer job");
    devApplications.displayTo(appsDisplayer);

    Applications archApplications = recruiter.listApplicationsByJob(appRepo, jobArchitect);
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
    jobDeveloper = new ATSJob(new JobTitle("Developer"));
    jobArchitect = new ATSJob(new JobTitle("Architect"));
    jobProgrammer = new JReqJob(new JobTitle("Programmer"));
    jobseekerTom = new Jobseeker(new Name("Tom"));    
    jobseekerDick = new Jobseeker(new Name("Dick"));    
    jobseekerHarry = new Jobseeker(new Name("Harry"));    
    recruiter = new Recruiter(new Name("George"));
  }

}
