package com.ladders.oc.recruiters;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.*;

import com.ladders.oc.Name;
import com.ladders.oc.argmatchers.*;
import com.ladders.oc.applications.ApplicationProcessor;
import com.ladders.oc.applications.ApplicationRepository;
import com.ladders.oc.applications.Applications;
import com.ladders.oc.applications.TimeServer;
import com.ladders.oc.displayables.DisplayableName;
import com.ladders.oc.displayers.ApplicationsDisplayer;
import com.ladders.oc.displayers.JobsDisplayer;
import com.ladders.oc.displayers.RecruiterDisplayer;
import com.ladders.oc.jobs.ATSJob;
import com.ladders.oc.jobs.JReqJob;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobs.Jobs;
import com.ladders.oc.jobseekers.Jobseeker;

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
  public void thereCanBeMoreThanOneJobseekersWithSameName()
  {
    Recruiter recruiter1 = Recruiter.named("George");
    Recruiter recruiter2 = Recruiter.named("George");
    Recruiter recruiter3 = Recruiter.named("George");
    
    Assert.assertNotEquals(recruiter1, recruiter2);
    Assert.assertNotEquals(recruiter1, recruiter3);
    Assert.assertNotEquals(recruiter2, recruiter3);
  }

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
    RecruiterDisplayer recDisplayer = Mockito.mock(RecruiterDisplayer.class);
    recruiter.displayTo(recDisplayer);

    DisplayableName name = new Name("George");
    Mockito.verify(recDisplayer).displayRecruiter(name);
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
    
    JobsDisplayer jobsDisplayer = Mockito.mock(JobsDisplayer.class);
    jobs.displayTo(jobsDisplayer);
    Mockito.verify(jobsDisplayer).displayJobs(Matchers.argThat(new SetOfThreeJobs(developerJob, architectJob, programmerJob)));
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
    applyStatus = jobseekerHarry.applyFor(developerJob).to(appProcessor);
    assertTrue(applyStatus);
    applyStatus = jobseekerTom.applyFor(architectJob).to(appProcessor);
    assertTrue(applyStatus);
    applyStatus = jobseekerDick.applyFor(architectJob).to(appProcessor);
    assertTrue(applyStatus);

    Applications developerApps = recruiter.getApplications().filterBy(developerJob).from(appRepo);

    ApplicationsDisplayer appsDisplayer = Mockito.mock(ApplicationsDisplayer.class);
    developerApps.displayTo(appsDisplayer);
    Mockito.verify(appsDisplayer).displayApplications(Mockito.argThat(new SetOfTwoAppsWithJobSeekers(jobseekerTom, jobseekerHarry)));

    Applications architectApps = recruiter.getApplications().filterBy(architectJob).from(appRepo);

    appsDisplayer = Mockito.mock(ApplicationsDisplayer.class);
    architectApps.displayTo(appsDisplayer);
    Mockito.verify(appsDisplayer).displayApplications(Mockito.argThat(new SetOfTwoAppsWithJobSeekers(jobseekerTom, jobseekerDick)));
  }

  @Test
  public void recruitersCanSeeJobseekersByJobAndDate()
  {
    setupActors();
    recruiter.post(developerJob);
    recruiter.post(architectJob);

    ApplicationRepository appRepo = new ApplicationRepository();
    TimeServer timeServerOne = Mockito.mock(TimeServer.class);
    TimeServer timeServerTwo = Mockito.mock(TimeServer.class);
    Date dayOne = null;
    Date dayTwo = null;
    try
    {
      dayOne = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2013-05-01 12:30:00");
      dayTwo = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2013-07-04 12:30:00");
    }
    catch (ParseException e)
    {
      fail();
    }
    Mockito.when(timeServerOne.getCurrentTime()).thenReturn(dayOne);
    Mockito.when(timeServerTwo.getCurrentTime()).thenReturn(dayTwo);
    ApplicationProcessor appProcessorOne = new ApplicationProcessor(appRepo, timeServerOne);
    ApplicationProcessor appProcessorTwo = new ApplicationProcessor(appRepo, timeServerTwo);

    boolean applyStatus;

    applyStatus = jobseekerTom.applyFor(developerJob).to(appProcessorOne);
    assertTrue(applyStatus);
    applyStatus = jobseekerHarry.applyFor(developerJob).to(appProcessorOne);
    assertTrue(applyStatus);
    applyStatus = jobseekerTom.applyFor(architectJob).to(appProcessorTwo);
    assertTrue(applyStatus);
    applyStatus = jobseekerDick.applyFor(architectJob).to(appProcessorTwo);
    assertTrue(applyStatus);

    Applications appsOnDayOne = recruiter.getApplications().filterBy(dayOne).from(appRepo);

    ApplicationsDisplayer appsDisplayer = Mockito.mock(ApplicationsDisplayer.class);
    appsOnDayOne.displayTo(appsDisplayer);
    Mockito.verify(appsDisplayer).displayApplications(Mockito.argThat(new SetOfTwoAppsWithDates(dayOne)));

    Applications appsOnDayTwoForArchitect = recruiter.getApplications().filterBy(architectJob).filterBy(dayTwo).from(appRepo);

    appsDisplayer = Mockito.mock(ApplicationsDisplayer.class);
    appsOnDayTwoForArchitect.displayTo(appsDisplayer);
    Mockito.verify(appsDisplayer).displayApplications(Mockito.argThat(new SetOfTwoAppsWithJobsAndDates(dayTwo, architectJob)));
  }
  
  private void setupApplicationRepository()
  {
    appRepo = new ApplicationRepository();
    TimeServer timeServer = new TimeServer();
    appProcessor = new ApplicationProcessor(appRepo, timeServer);
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
