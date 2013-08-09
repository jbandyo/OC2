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
    Job job1 = new ATSJob(new JobTitle("Developer"));    
    Jobseeker jobseeker = new Jobseeker(new Name("Tom"));    
    jobseeker.saveJob(job1);
  }

  @Test
  public void jobseekerCanListSavedJobs()
  {
    setupJobs();
    Jobseeker jobseeker = new Jobseeker(new Name("Tom"));    
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
    Jobseeker jobseeker = new Jobseeker(new Name("Tom"));    
    jobseeker.saveJob(programmerJob);
    jobseeker.saveJob(developerJob);
    jobseeker.saveJob(architectJob);
    Jobs jobs = jobseeker.getSavedJobs();
    JobsDisplayer jobsDisplayer = new ConsoleJobsDisplayer();
    jobs.displayTo(jobsDisplayer);
  }

  @Test
  public void jobSeekersCanApplyToATSJobsWithoutResume()
  {
    setupApplicationRepository();
    Jobseeker jobseekerTom = new Jobseeker(new Name("Tom"));    
    Job developerJob = new ATSJob(new JobTitle("Developer"));
    Maybe<Resume> noResume = Maybe.nothing();
    boolean applyStatus = jobseekerTom.applyToJob(appProcessor, developerJob, noResume);
    assertTrue(applyStatus);
  }

  @Test
  public void jobSeekersCanApplyToJReqJobsWithResume()
  {
    setupApplicationRepository();
    Jobseeker jobseekerTom = new Jobseeker(new Name("Tom"));    
    Job programmerJob = new JReqJob(new JobTitle("Programmer"));
    Maybe<Resume> tomsResume = Maybe.just(new Resume(jobseekerTom));
    boolean applyStatus = jobseekerTom.applyToJob(appProcessor, programmerJob, tomsResume);
    assertTrue(applyStatus);
  }

  @Test
  public void jobSeekersCannotApplyToJReqJobsWithoutResume()
  {
    setupApplicationRepository();
    Jobseeker jobseekerTom = new Jobseeker(new Name("Tom"));    
    Job programmerJob = new JReqJob(new JobTitle("Programmer"));
    Maybe<Resume> noResume = Maybe.nothing();
    boolean applyStatus = jobseekerTom.applyToJob(appProcessor, programmerJob, noResume);
    assertFalse(applyStatus);
  }

  @Test
  public void jobSeekersCannotApplyToJobsWithOthersResume()
  {
    setupApplicationRepository();
    Jobseeker jobseekerTom = new Jobseeker(new Name("Tom"));    
    Jobseeker jobseekerDick = new Jobseeker(new Name("Dick"));    
    Maybe<Resume> dicksResume = Maybe.just(new Resume(jobseekerDick));
    Job programmerJob = new JReqJob(new JobTitle("Programmer"));
    boolean applyStatus = jobseekerTom.applyToJob(appProcessor, programmerJob, dicksResume);
    assertFalse(applyStatus);
  }

  @Test
  public void jobSeekersCanApplyToDifferentJobsWithDifferentResume()
  {
    setupJobs();
    setupApplicationRepository();
    Jobseeker jobseekerTom = new Jobseeker(new Name("Tom"));    
    Maybe<Resume> architectResume = Maybe.just(new Resume(jobseekerTom));
    Maybe<Resume> developerResume = Maybe.just(new Resume(jobseekerTom));
    boolean applyStatus = jobseekerTom.applyToJob(appProcessor, architectJob, architectResume);
    assertTrue(applyStatus);
    applyStatus = jobseekerTom.applyToJob(appProcessor, developerJob, developerResume);
    assertTrue(applyStatus);
  }

  @Test
  public void jobSeekersCanListJobsToWhichTheyApplied()
  {
    setupApplicationRepository();
    setupJobs();
    Jobseeker jobseekerTom = new Jobseeker(new Name("Tom"));    
    Maybe<Resume> noResume = Maybe.nothing();
    Maybe<Resume> architectResume = Maybe.just(new Resume(jobseekerTom));
    Maybe<Resume> developerResume = Maybe.just(new Resume(jobseekerTom));
    boolean applyStatus = jobseekerTom.applyToJob(appProcessor, architectJob, architectResume);
    assertTrue(applyStatus);
    applyStatus = jobseekerTom.applyToJob(appProcessor, developerJob, developerResume);
    assertTrue(applyStatus);
    applyStatus = jobseekerTom.applyToJob(appProcessor, programmerJob, noResume);
    assertTrue(applyStatus);
    Jobs jobs = jobseekerTom.getAppliedToJobs();
    assertTrue(jobs.contains(architectJob));
    assertTrue(jobs.contains(developerJob));
    assertTrue(jobs.contains(programmerJob));
  }

  @Test
  public void jobSeekersCanDisplayJobsToWhichTheyApplied()
  {
    setupApplicationRepository();
    setupJobs();
    Jobseeker jobseekerTom = new Jobseeker(new Name("Tom"));    
    Maybe<Resume> noResume = Maybe.nothing();
    Maybe<Resume> architectResume = Maybe.just(new Resume(jobseekerTom));
    Maybe<Resume> developerResume = Maybe.just(new Resume(jobseekerTom));
    boolean applyStatus = jobseekerTom.applyToJob(appProcessor, architectJob, architectResume);
    assertTrue(applyStatus);
    applyStatus = jobseekerTom.applyToJob(appProcessor, developerJob, developerResume);
    assertTrue(applyStatus);
    applyStatus = jobseekerTom.applyToJob(appProcessor, programmerJob, noResume);
    assertTrue(applyStatus);
    Jobs jobs = jobseekerTom.getAppliedToJobs();
    JobsDisplayer jobsDisplayer = new ConsoleJobsDisplayer();
    jobs.displayTo(jobsDisplayer);
  }

  private void setupApplicationRepository()
  {
    appRepo = new ApplicationRepository();
    appProcessor = new ApplicationProcessor(appRepo);
  }
  
  private void setupJobs()
  {
    developerJob = new JReqJob(new JobTitle("Developer"));
    architectJob = new JReqJob(new JobTitle("Architect"));
    programmerJob = new ATSJob(new JobTitle("Programmer"));
  }

}
