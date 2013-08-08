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
  public void recruitersCanListJobsTheyPosted()
  {
    Recruiter recruiter = new Recruiter(new Name("George"));    
    Job job1 = new ATSJob(new JobTitle("Developer"));
    recruiter.postJob(job1);
    Job job2 = new ATSJob(new JobTitle("Architect"));
    recruiter.postJob(job2);
    Job job3 = new JReqJob(new JobTitle("Programmer"));
    Jobs jobs = recruiter.listPostedJobs();
    assertTrue(jobs.contains(job1));
    assertTrue(jobs.contains(job2));
    assertFalse(jobs.contains(job3));
  }

  @Test
  public void recruitersCanDisplayJobsTheyPosted()
  {
    Recruiter recruiter = new Recruiter(new Name("George"));    
    Job job1 = new ATSJob(new JobTitle("Developer"));
    recruiter.postJob(job1);
    Job job2 = new ATSJob(new JobTitle("Architect"));
    recruiter.postJob(job2);
    Job job3 = new JReqJob(new JobTitle("Programmer"));
    recruiter.postJob(job3);
    Jobs jobs = recruiter.listPostedJobs();
    JobsDisplayer jobsDisplayer = new ConsoleJobsDisplayer();
    jobs.displayTo(jobsDisplayer);
  }
  
  @Test
  public void recruitersCanSeeJobseekersByJob()
  {
    Recruiter recruiter = new Recruiter(new Name("George"));    
    Job jobDeveloper = new ATSJob(new JobTitle("Developer"));
    Job jobArchitect = new ATSJob(new JobTitle("Architect"));
    recruiter.postJob(jobDeveloper);
    recruiter.postJob(jobArchitect);
    
    setupApplicationRepository();
    Jobseeker tom = new Jobseeker(new Name("Tom"));    
    Jobseeker dick = new Jobseeker(new Name("Dick"));    
    Jobseeker harry = new Jobseeker(new Name("Harry"));    
    boolean applyStatus;
    applyStatus = tom.applyToJob(appProcessor, jobDeveloper, null);
    assertTrue(applyStatus);
    applyStatus = tom.applyToJob(appProcessor, jobArchitect, null);
    assertTrue(applyStatus);
    applyStatus = harry.applyToJob(appProcessor, jobDeveloper, null);
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

}
