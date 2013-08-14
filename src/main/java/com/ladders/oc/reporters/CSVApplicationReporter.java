package com.ladders.oc.reporters;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ladders.oc.displayables.DisplayableJob;
import com.ladders.oc.displayables.DisplayableJobseeker;
import com.ladders.oc.displayers.ApplicationDisplayer;

public class CSVApplicationReporter implements ApplicationDisplayer
{
  private String appText = "";
  
  public String getApplicationText()
  {
    return appText;
  }
  
  @Override
  public void displayApplication(DisplayableJob job,
                                 DisplayableJobseeker jobseeker,
                                 Date date)
  {
    TextJobReporter jobReporter = new TextJobReporter();
    job.displayTo(jobReporter);
    appText += jobReporter.getJobText();
    appText += ",";

    TextJobseekerReporter jobseekerReporter = new TextJobseekerReporter();
    jobseeker.displayTo(jobseekerReporter);
    appText += jobseekerReporter.getJobseekerText();
    appText += ",";
    
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
    appText += sdf.format(date);
  }

}
