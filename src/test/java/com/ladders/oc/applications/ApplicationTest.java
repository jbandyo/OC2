package com.ladders.oc.applications;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.ladders.oc.jobs.ATSJob;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobseekers.Jobseeker;

public class ApplicationTest
{
  @Test
  public void checkingForJobWorks()
  {
    Job job1 = ATSJob.titled("Developer");    
    Job job2 = ATSJob.titled("Programmer");    
    Jobseeker jobseeker = Jobseeker.named("Tom");    
    Application app = new Application(job1, jobseeker, new Date());
    assertFalse(app.containsJob(job2));
    assertTrue(app.containsJob(job1));
  }

  @Test
  public void checkingForJobseekerWorks()
  {
    Job job = ATSJob.titled("Developer");    
    Jobseeker jobseekerTom = Jobseeker.named("Tom");    
    Jobseeker jobseekerDick = Jobseeker.named("Dick");;    
    Application app = new Application(job, jobseekerDick, new Date());
    assertFalse(app.containsJobseeker(jobseekerTom));
    assertTrue(app.containsJobseeker(jobseekerDick));
  }

  @Test
  public void checkingForDateWorks()
  {
    Job job = ATSJob.titled("Developer");    
    Jobseeker jobseeker = Jobseeker.named("Tom");
    Date date = null;
    try
    {
      date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2013-07-04 12:30:00");
    }
    catch (ParseException e)
    {
      fail();
    }
    //
    Application app = new Application(job, jobseeker, date);
    boolean result = app.containsDate(date);
    assertTrue(result);
  }

}
