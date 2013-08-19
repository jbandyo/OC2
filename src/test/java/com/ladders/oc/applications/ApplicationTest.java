package com.ladders.oc.applications;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.ladders.oc.jobs.ATSJob;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobseekers.Jobseeker;
import com.ladders.oc.recruiters.JobPosting;
import com.ladders.oc.recruiters.JobRepository;
import com.ladders.oc.recruiters.Recruiter;

public class ApplicationTest
{
  private JobRepository jobRepository;
  private Recruiter recruiter1;
  private Job job1;
  private Job job2;
  private JobPosting developerPosting;
  private JobPosting architectPosting;
  private Jobseeker jobseekerTom;
  private Jobseeker jobseekerDick;

  @Test
  public void checkingForJobWorks()
  {
    SetupEnvironment();
    Application app = new Application(developerPosting, jobseekerTom, new Date());
    assertTrue(app.containsJobPosting(developerPosting));
    assertFalse(app.containsJobPosting(architectPosting));
  }

  @Test
  public void checkingForJobseekerWorks()
  {
    SetupEnvironment();
    Application app = new Application(developerPosting, jobseekerDick, new Date());
    assertTrue(app.containsJobseeker(jobseekerDick));
    assertFalse(app.containsJobseeker(jobseekerTom));
  }

  @Test
  public void checkingForDateWorks()
  {
    SetupEnvironment();
    Date date = null;
    try
    {
      date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2013-07-04 12:30:00");
    }
    catch (ParseException e)
    {
      fail();
    }
    
    Application app = new Application(developerPosting, jobseekerTom, date);
    boolean result = app.containsDate(date);
    assertTrue(result);
  }

  private void SetupEnvironment()
  {
    jobRepository = new JobRepository();
    recruiter1 = Recruiter.named("George");
    job1 = ATSJob.titled("Developer");    
    job2 = ATSJob.titled("Programmer");    
    developerPosting = recruiter1.post(job1).to(jobRepository);
    architectPosting = recruiter1.post(job2).to(jobRepository);
    jobseekerTom = Jobseeker.named("Tom");    
    jobseekerDick = Jobseeker.named("Dick");;    
  }

}
