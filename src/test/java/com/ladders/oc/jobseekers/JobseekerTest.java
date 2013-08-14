package com.ladders.oc.jobseekers;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.ladders.oc.applications.ApplicationProcessor;
import com.ladders.oc.applications.ApplicationRepository;
import com.ladders.oc.applications.Applications;
import com.ladders.oc.applications.TimeServer;
import com.ladders.oc.argmatchers.SetOfThreeAppsWithJobs;
import com.ladders.oc.argmatchers.SetOfThreeJobs;
import com.ladders.oc.displayers.ApplicationsDisplayer;
import com.ladders.oc.displayers.JobsDisplayer;
import com.ladders.oc.jobs.ATSJob;
import com.ladders.oc.jobs.JReqJob;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobs.Jobs;
import com.ladders.oc.resumes.Resume;

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
    assertNotNull(jobs);
   
    JobsDisplayer jobsDisplayer = Mockito.mock(JobsDisplayer.class);
    jobs.displayTo(jobsDisplayer);
    Mockito.verify(jobsDisplayer).displayJobs(Matchers.argThat(new SetOfThreeJobs(developerJob, architectJob, programmerJob)));
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

    ApplicationsDisplayer appsDisplayer = Mockito.mock(ApplicationsDisplayer.class);
    applications.displayTo(appsDisplayer);
    Mockito.verify(appsDisplayer).displayApplications(Mockito.argThat(new SetOfThreeAppsWithJobs(architectJob, developerJob, programmerJob)));
  }

  private void setupApplicationRepository()
  {
    appRepo = new ApplicationRepository();
    TimeServer timeServer = new TimeServer();
    appProcessor = new ApplicationProcessor(appRepo, timeServer);
  }
  
  private void setupJobs()
  {
    developerJob = JReqJob.titled("Developer");
    architectJob = JReqJob.titled("Architect");
    programmerJob = ATSJob.titled("Programmer");
  }

}
