package com.ladders.oc.admin;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.ladders.oc.applications.ApplicationProcessor;
import com.ladders.oc.applications.ApplicationRepository;
import com.ladders.oc.applications.TimeServer;
import com.ladders.oc.jobs.ATSJob;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobseekers.Jobseeker;

public class AdminTest
{
  private ApplicationRepository appRepo;
  private ApplicationProcessor appProcessorOne;
  private ApplicationProcessor appProcessorTwo;
  private Admin theLadders;
  private Date dayOne = null;
  private Date dayTwo = null;
  private Job developerJob;
  private Job architectJob;
  private Job programmerJob;
  
  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {}

  @Test
  public void theLadderShouldBeAbleToGetApplicationsReportInCSV()
  {
    setupApplicationEnvironment();
    setupApplications();
    String csvReport = theLadders.getApplicationsReportInCSVFor(dayOne);
    System.out.println(csvReport);
  }

  @Test
  public void theLadderShouldBeAbleToGetApplicationsReportInHtml()
  {
    setupApplicationEnvironment();
    setupApplications();
    String htmlReport = theLadders.getApplicationsReportInHTMLFor(dayOne);
    System.out.println(htmlReport);
  }

  @Test
  public void theLadderShouldBeAbleToGetAggregateNumbersByJob()
  {
    int numDeveloperApplications = 0;
    int numArchitectApplications = 0;
    int numProgrammerApplications = 0;
    
    setupApplicationEnvironment();
    
    assertEquals(numDeveloperApplications, 0);
    assertEquals(numArchitectApplications, 0);
    assertEquals(numProgrammerApplications, 0);

    setupApplications();

    numDeveloperApplications = theLadders.getAggregateApplicationsBy(developerJob);
    assertEquals(numDeveloperApplications, 2);
    
    numArchitectApplications = theLadders.getAggregateApplicationsBy(architectJob);
    assertEquals(numArchitectApplications, 2);

    numProgrammerApplications = theLadders.getAggregateApplicationsBy(programmerJob);
    assertEquals(numProgrammerApplications, 0);    
  }
  
  private void setupApplicationEnvironment()
  {
    appRepo = new ApplicationRepository();
    TimeServer timeServerOne = Mockito.mock(TimeServer.class);
    TimeServer timeServerTwo = Mockito.mock(TimeServer.class);

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
    
    appProcessorOne = new ApplicationProcessor(appRepo, timeServerOne);
    appProcessorTwo = new ApplicationProcessor(appRepo, timeServerTwo);

    theLadders = new Admin(appRepo);
  }

  private void setupApplications()
  {
    developerJob = ATSJob.titled("Developer");
    architectJob = ATSJob.titled("Architect");
    programmerJob = ATSJob.titled("Programmer");
    Jobseeker jobseekerTom   = Jobseeker.named("Tom");   
    Jobseeker jobseekerDick  = Jobseeker.named("Dick");    
    Jobseeker jobseekerHarry = Jobseeker.named("Harry");    

    boolean applyStatus;
    applyStatus = jobseekerTom.applyFor(developerJob).to(appProcessorOne);
    assertTrue(applyStatus);
    applyStatus = jobseekerHarry.applyFor(developerJob).to(appProcessorOne);
    assertTrue(applyStatus);
    applyStatus = jobseekerTom.applyFor(architectJob).to(appProcessorOne);
    assertTrue(applyStatus);
    applyStatus = jobseekerDick.applyFor(architectJob).to(appProcessorTwo);
    assertTrue(applyStatus);
  }
}
