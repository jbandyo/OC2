package com.ladders.oc.applications;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.ladders.oc.Name;
import com.ladders.oc.jobs.ATSJob;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobs.JobTitle;
import com.ladders.oc.jobseekers.Jobseeker;

public class ApplicationTest
{
  @Test
  public void checkingForJobWorks()
  {
    Job job1 = new ATSJob(new JobTitle("Developer"));    
    Job job2 = new ATSJob(new JobTitle("Programmer"));    
    Jobseeker jobseeker = new Jobseeker(new Name("Tom"));    
    Application app = new Application(job1, jobseeker);
    assertFalse(app.containsJob(job2));
    assertTrue(app.containsJob(job1));
  }

  @Test
  public void checkingForJobseekerWorks()
  {
    Job job = new ATSJob(new JobTitle("Developer"));    
    Jobseeker jobseekerTom = new Jobseeker(new Name("Tom"));    
    Jobseeker jobseekerDick = new Jobseeker(new Name("Dick"));    
    Application app = new Application(job, jobseekerDick);
    assertFalse(app.containsJobseeker(jobseekerTom));
    assertTrue(app.containsJobseeker(jobseekerDick));
  }

  @Test
  public void checkingForDateWorks()
  {
    Job job = new ATSJob(new JobTitle("Developer"));    
    Jobseeker jobseeker = new Jobseeker(new Name("Tom"));    
    Application app = new Application(job, jobseeker);
    Date now = new Date();
    boolean result = app.containsDate(now);
    if (!result)
    {
      // take care of date rollover
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(now);
      calendar.add(Calendar.DATE, -1); // prev day
      result = app.containsDate(now);
    }
    assertTrue(result);
  }

}
