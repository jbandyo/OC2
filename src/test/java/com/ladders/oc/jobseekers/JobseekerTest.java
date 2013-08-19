package com.ladders.oc.jobseekers;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.ladders.oc.applications.ApplicationProcessor;
import com.ladders.oc.applications.ApplicationRepository;
import com.ladders.oc.applications.Applications;
import com.ladders.oc.applications.TimeServer;
import com.ladders.oc.argmatchers.SetOfThreeAppsWithJobPostings;
import com.ladders.oc.argmatchers.SetOfThreeJobPostings;
import com.ladders.oc.displayers.ApplicationsDisplayer;
import com.ladders.oc.displayers.JobPostingsDisplayer;
import com.ladders.oc.jobs.ATSJob;
import com.ladders.oc.jobs.JReqJob;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.recruiters.JobPosting;
import com.ladders.oc.recruiters.JobPostings;
import com.ladders.oc.recruiters.JobRepository;
import com.ladders.oc.recruiters.Recruiter;
import com.ladders.oc.resumes.Resume;

public class JobseekerTest
{
  private ApplicationRepository appRepo;
  private ApplicationProcessor  appProcessor;
  private JobRepository jobRepository;
  private JobPosting developerPosting;
  private JobPosting architectPosting;
  private JobPosting programmerPosting;

  @Test
  public void thereCanBeMoreThanOneJobseekersWithSameName()
  {
    Jobseeker jobseeker1 = Jobseeker.named("Tom");    
    Jobseeker jobseeker2 = Jobseeker.named("Tom");    
    Jobseeker jobseeker3 = Jobseeker.named("Tom");   
    
    Assert.assertNotEquals(jobseeker1, jobseeker2);
    Assert.assertNotEquals(jobseeker1, jobseeker3);
    Assert.assertNotEquals(jobseeker2, jobseeker3);
  }
  
  @Test
  public void jobseekerCanSaveJob()
  {
    Jobseeker jobseeker = Jobseeker.named("Tom");    
    jobseeker.saveJobPosting(programmerPosting);
  }

  @Test
  public void jobseekerCanListSavedJobs()
  {
    setupJobs();
    Jobseeker jobseeker = Jobseeker.named("Tom");    
    jobseeker.saveJobPosting(programmerPosting);
    jobseeker.saveJobPosting(developerPosting);
    jobseeker.saveJobPosting(architectPosting);

    JobPostings postings = jobseeker.getSavedJobPostings();
 
    assertTrue(postings.contains(programmerPosting));
    assertTrue(postings.contains(developerPosting));
    assertTrue(postings.contains(architectPosting));
  }
  
  @Test
  public void jobSeekersCanDisplayJobsTheySaved()
  {
    setupJobs();
    Jobseeker jobseeker = Jobseeker.named("Tom");    
    jobseeker.saveJobPosting(programmerPosting);
    jobseeker.saveJobPosting(developerPosting);
    jobseeker.saveJobPosting(architectPosting);
    
    JobPostings postings = jobseeker.getSavedJobPostings();
   
    JobPostingsDisplayer postingsDisplayer = Mockito.mock(JobPostingsDisplayer.class);
    postings.displayTo(postingsDisplayer);
    Mockito.verify(postingsDisplayer).displayJobPostings(Matchers.argThat(new SetOfThreeJobPostings(developerPosting, architectPosting, programmerPosting)));
  }

  @Test
  public void jobSeekersCanApplyToATSJobsWithoutResume()
  {
    setupRepositories();
    setupJobs();
    Jobseeker jobseekerTom = Jobseeker.named("Tom");    
    boolean applyStatus = jobseekerTom.applyFor(programmerPosting).to(appProcessor);
    assertTrue(applyStatus);
  }

  @Test
  public void jobSeekersCanApplyToJReqJobsWithResume()
  {
    setupRepositories();
    setupJobs();
    Jobseeker jobseekerTom = Jobseeker.named("Tom");    
    Resume resume = Resume.createdBy(jobseekerTom);
    boolean applyStatus = jobseekerTom.applyFor(developerPosting).with(resume).to(appProcessor);
    assertTrue(applyStatus);
  }

  @Test
  public void jobSeekersCannotApplyToJReqJobsWithoutResume()
  {
    setupRepositories();
    setupJobs();
    Jobseeker jobseekerTom = Jobseeker.named("Tom");    
    boolean applyStatus = jobseekerTom.applyFor(developerPosting).to(appProcessor);
    assertFalse(applyStatus);
  }

  @Test
  public void jobSeekersCannotApplyToJobsWithOthersResume()
  {
    setupRepositories();
    setupJobs();
    Jobseeker jobseekerTom = Jobseeker.named("Tom");    
    Jobseeker jobseekerDick = Jobseeker.named("Dick");    
    Resume dicksResume = Resume.createdBy(jobseekerDick);
    boolean applyStatus = jobseekerTom.applyFor(developerPosting).with(dicksResume).to(appProcessor);
    assertFalse(applyStatus);
  }

  @Test
  public void jobSeekersCanApplyToDifferentJobsWithDifferentResume()
  {
    setupRepositories();
    setupJobs();
    Jobseeker jobseekerTom = Jobseeker.named("Tom");    
    Resume architectResume = Resume.createdBy(jobseekerTom);
    Resume developerResume = Resume.createdBy(jobseekerTom);
    
    boolean applyStatus;
    applyStatus = jobseekerTom.applyFor(architectPosting).with(architectResume).to(appProcessor);
    assertTrue(applyStatus);
    
    applyStatus = jobseekerTom.applyFor(developerPosting).with(developerResume).to(appProcessor);
    assertTrue(applyStatus);
  }

  @Test
  public void jobSeekersCanDisplayJobsToWhichTheyApplied()
  {
    setupRepositories();
    setupJobs();
    Jobseeker jobseekerTom = Jobseeker.named("Tom");    
    Resume architectResume = Resume.createdBy(jobseekerTom);
    Resume developerResume = Resume.createdBy(jobseekerTom);

    Applications applications = jobseekerTom.getJobsAppliedTo().from(appRepo);
    assertEquals(applications.count(), 0);
    
    boolean applyStatus;
    applyStatus = jobseekerTom.applyFor(architectPosting).with(architectResume).to(appProcessor);
    assertTrue(applyStatus);

    applyStatus = jobseekerTom.applyFor(developerPosting).with(developerResume).to(appProcessor);
    assertTrue(applyStatus);

    applyStatus = jobseekerTom.applyFor(programmerPosting).to(appProcessor);
    assertTrue(applyStatus);
    
    applications = jobseekerTom.getJobsAppliedTo().from(appRepo);
    assertEquals(applications.count(), 3);

    ApplicationsDisplayer appsDisplayer = Mockito.mock(ApplicationsDisplayer.class);
    applications.displayTo(appsDisplayer);
    Mockito.verify(appsDisplayer).displayApplications(Mockito.argThat(new SetOfThreeAppsWithJobPostings(architectPosting, developerPosting, programmerPosting)));
  }

  private void setupRepositories()
  {
    appRepo = new ApplicationRepository();
    TimeServer timeServer = new TimeServer();
    appProcessor = new ApplicationProcessor(appRepo, timeServer);
  }
  
  private void setupJobs()
  {
    jobRepository = new JobRepository();
    Recruiter recruiter = Recruiter.named("George");

    Job developerJob = JReqJob.titled("Developer");
    Job architectJob = JReqJob.titled("Architect");
    Job programmerJob = ATSJob.titled("Programmer");

    developerPosting = recruiter.post(developerJob).to(jobRepository);
    architectPosting = recruiter.post(architectJob).to(jobRepository);
    programmerPosting = recruiter.post(programmerJob).to(jobRepository);
  }

}
