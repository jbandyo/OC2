package com.ladders.oc.admin;

import java.util.Date;


import com.ladders.oc.applications.ApplicationQueryHelper;
import com.ladders.oc.applications.ApplicationRepository;
import com.ladders.oc.applications.Applications;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.reporters.CSVApplicationsReporter;
import com.ladders.oc.reporters.HTMLApplicationsReporter;

public class Admin
{
  private final ApplicationRepository appRepo;
  
  public Admin(ApplicationRepository appRepo)
  {
    this.appRepo = appRepo;
  }
  
  public String getApplicationsReportInCSVFor(Date date)
  {
    ApplicationQueryHelper queryHelper = new ApplicationQueryHelper();
    Applications applications = queryHelper.filterBy(date).from(appRepo);
    
    CSVApplicationsReporter csvReporter = new CSVApplicationsReporter();
    applications.displayTo(csvReporter);
    return csvReporter.getApplicationsReport();
  }

  public String getApplicationsReportInHTMLFor(Date date)
  {
    ApplicationQueryHelper queryHelper = new ApplicationQueryHelper();
    Applications applications = queryHelper.filterBy(date).from(appRepo);
    HTMLApplicationsReporter htmlReporter = new HTMLApplicationsReporter();
    
    htmlReporter.createHeader();
    
    applications.displayTo(htmlReporter);
    
    htmlReporter.createFooter();
    
    return htmlReporter.getApplicationsReport();
  }

  public int getAggregateApplicationsBy(Job job)
  {
    ApplicationQueryHelper queryHelper = new ApplicationQueryHelper();
    Applications applications = queryHelper.filterBy(job).from(appRepo);
    return applications.count();
  }

}
