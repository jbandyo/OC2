package com.ladders.oc;

import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.mockito.Mockito;

import com.ladders.oc.admin.Admin;
import com.ladders.oc.applications.ApplicationProcessor;
import com.ladders.oc.applications.ApplicationRepository;
import com.ladders.oc.applications.Applications;
import com.ladders.oc.applications.TimeServer;
import com.ladders.oc.displayers.ApplicationsDisplayer;
import com.ladders.oc.displayers.ConsoleApplicationsJobPostingsDisplayer;
import com.ladders.oc.displayers.ConsoleApplicationsJobseekerDisplayer;
import com.ladders.oc.displayers.ConsoleJobPostingsDisplayer;
import com.ladders.oc.displayers.JobPostingsDisplayer;
import com.ladders.oc.jobs.ATSJob;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobseekers.Jobseeker;
import com.ladders.oc.recruiters.JobPosting;
import com.ladders.oc.recruiters.JobPostings;
import com.ladders.oc.recruiters.JobRepository;
import com.ladders.oc.recruiters.Recruiter;


public class App
{

  public static void main(String[] args)
  {
    ApplicationRepository appRepo = new ApplicationRepository();
    JobRepository jobRepository = new JobRepository();

    // recruiter postings
    Recruiter recruiterGeorge = Recruiter.named("George");
    Recruiter recruiterAdam = Recruiter.named("Adam");

    Job developerJob1  = ATSJob.titled("Developer@Google");
    Job developerJob2  = ATSJob.titled("Developer@Facebook");
    Job architectJob  = ATSJob.titled("Architect@Microsoft");
    Job programmerJob = ATSJob.titled("Programmer@Apple");

    JobPosting developerPosting1 = recruiterGeorge.post(developerJob1).to(jobRepository);
    JobPosting developerPosting2 = recruiterGeorge.post(developerJob2).to(jobRepository);
    JobPosting architectPosting = recruiterAdam.post(architectJob).to(jobRepository);
    JobPosting programmerPosting = recruiterAdam.post(programmerJob).to(jobRepository);

    // recruiters check posted jobs
    System.out.println("Jobs posted:");
    JobPostingsDisplayer postingsDisplayer = new ConsoleJobPostingsDisplayer();
    JobPostings jobPostings = recruiterGeorge.getPostedJobs().from(jobRepository);
    jobPostings.displayTo(postingsDisplayer);
    jobPostings = recruiterAdam.getPostedJobs().from(jobRepository);
    jobPostings.displayTo(postingsDisplayer);
    System.out.println();
    
    // jobseekers save and check jobs
    Jobseeker jobseekerTom   = Jobseeker.named("Tom");   
    Jobseeker jobseekerDick  = Jobseeker.named("Dick");    
    Jobseeker jobseekerHarry = Jobseeker.named("Harry");    

    jobseekerTom.saveJobPosting(developerPosting1);
    jobseekerTom.saveJobPosting(developerPosting2);
    jobseekerTom.saveJobPosting(programmerPosting);
    jobseekerTom.saveJobPosting(architectPosting);

    jobseekerDick.saveJobPosting(developerPosting1);
    jobseekerDick.saveJobPosting(programmerPosting);
    jobseekerDick.saveJobPosting(architectPosting);
    
    jobseekerHarry.saveJobPosting(architectPosting);
    jobseekerHarry.saveJobPosting(developerPosting1);
    jobseekerHarry.saveJobPosting(developerPosting2);

    System.out.println("Jobs saved by Tom:");
    jobPostings = jobseekerTom.getSavedJobPostings();
    jobPostings.displayTo(postingsDisplayer);
    System.out.println();
   
    System.out.println("Jobs saved by Dick:");
    jobPostings = jobseekerDick.getSavedJobPostings();
    jobPostings.displayTo(postingsDisplayer);
    System.out.println();

    System.out.println("Jobs saved by Harry:");
    jobPostings = jobseekerHarry.getSavedJobPostings();
    jobPostings.displayTo(postingsDisplayer);
    System.out.println();

    // setup fake dates
    Date dayOne = null;
    Date dayTwo = null;
    Date dayThree = null;
    TimeServer timeServer1 = Mockito.mock(TimeServer.class);
    TimeServer timeServer2 = Mockito.mock(TimeServer.class);
    TimeServer timeServer3 = Mockito.mock(TimeServer.class);

    try
    {
      dayOne = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2013-05-01 12:30:00");
      dayTwo = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2013-07-04 12:30:00");
      dayThree = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2013-11-23 12:30:00");
    }
    catch (ParseException e)
    {
      fail();
    }
    Mockito.when(timeServer1.getCurrentTime()).thenReturn(dayOne);
    Mockito.when(timeServer2.getCurrentTime()).thenReturn(dayTwo);
    Mockito.when(timeServer3.getCurrentTime()).thenReturn(dayThree);
    
    ApplicationProcessor appProcessor1 = new ApplicationProcessor(appRepo, timeServer1);
    ApplicationProcessor appProcessor2 = new ApplicationProcessor(appRepo, timeServer2);
    ApplicationProcessor appProcessor3 = new ApplicationProcessor(appRepo, timeServer3);

    // jobseeker applies to and check jobs
    jobseekerTom.applyFor(developerPosting1).to(appProcessor1);
    
    jobseekerTom.applyFor(developerPosting2).to(appProcessor2);
    jobseekerHarry.applyFor(developerPosting2).to(appProcessor2);
    jobseekerDick.applyFor(developerPosting2).to(appProcessor2);

    jobseekerTom.applyFor(architectPosting).to(appProcessor3);
    jobseekerTom.applyFor(programmerPosting).to(appProcessor3);
    jobseekerDick.applyFor(architectPosting).to(appProcessor3);
    jobseekerHarry.applyFor(developerPosting1).to(appProcessor3);

    System.out.println("Jobs applied to by Tom:");
    Applications applications = jobseekerTom.getJobsAppliedTo().from(appRepo);
    ApplicationsDisplayer appsDisplayer = new ConsoleApplicationsJobPostingsDisplayer();
    applications.displayTo(appsDisplayer);
    System.out.println();

    System.out.println("Jobs applied to by Dick:");
    applications = jobseekerDick.getJobsAppliedTo().from(appRepo);
    applications.displayTo(appsDisplayer);
    System.out.println();

    System.out.println("Jobs applied to by Harry:");
    applications = jobseekerHarry.getJobsAppliedTo().from(appRepo);
    applications.displayTo(appsDisplayer);
    System.out.println();

    // recruiters check jobseekers by job
    System.out.println("Jobseekers applied for Developer@Google:");
    ApplicationsDisplayer appsDisplayer2 = new ConsoleApplicationsJobseekerDisplayer();
    applications = recruiterGeorge.getApplications().filterBy(developerPosting1).from(appRepo);
    applications.displayTo(appsDisplayer2);
    System.out.println();
    
    System.out.println("Jobseekers applied for Developer@Facebook:");
    applications = recruiterGeorge.getApplications().filterBy(developerPosting2).from(appRepo);
    applications.displayTo(appsDisplayer2);
    System.out.println();

    System.out.println("Jobseekers applied for Architect@Microsoft:");
    applications = recruiterAdam.getApplications().filterBy(architectPosting).from(appRepo);
    applications.displayTo(appsDisplayer2);
    System.out.println();

    System.out.println("Jobseekers applied for Programmer@Apple");
    applications = recruiterAdam.getApplications().filterBy(programmerPosting).from(appRepo);
    applications.displayTo(appsDisplayer2);
    System.out.println();

    // recruiters check jobseekers by Date
    System.out.println("Jobseekers applied on 11/23/13");
    applications = recruiterGeorge.getApplications().filterBy(architectPosting).from(appRepo);
    applications.displayTo(appsDisplayer2);
    System.out.println();
    
    // recruiters check jobseekers by Date and Time
    System.out.println("Jobseekers applied for Architect@Microsoft on 11/23/13");
    applications = recruiterGeorge.getApplications().filterBy(architectPosting).filterBy(dayThree).from(appRepo);
    applications.displayTo(appsDisplayer2);
    System.out.println();

    // theladders report using csv
    // report by date 
    Admin theLadders = new Admin(appRepo);

    System.out.println("Jobseekers applied on 5/1/13:");
    String csvReport = theLadders.getApplicationsReportInCSVFor(dayOne);
    System.out.println(csvReport);

    System.out.println("Jobseekers applied on 7/4/13:");
    csvReport = theLadders.getApplicationsReportInCSVFor(dayTwo);
    System.out.println(csvReport);

    System.out.println("Jobseekers applied on 11/23/13:");
    csvReport = theLadders.getApplicationsReportInCSVFor(dayThree);
    System.out.println(csvReport);

    System.out.println("Number of Jobseekers applied for Developer@Google:");    
    System.out.println(theLadders.getAggregateApplicationsBy(developerPosting1));

    System.out.println("Number of Jobseekers applied for Developer@Facebook:");
    System.out.println(theLadders.getAggregateApplicationsBy(developerPosting2));

    System.out.println("Number of Jobseekers applied for Architect@Microsoft:");
    System.out.println(theLadders.getAggregateApplicationsBy(architectPosting));

    System.out.println("Number of Jobseekers applied for Programmer@Apple:");
    System.out.println(theLadders.getAggregateApplicationsBy(programmerPosting));

  }

}
