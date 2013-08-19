package com.ladders.oc.reporters;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ladders.oc.displayables.DisplayableJobPosting;
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
  public void displayApplication(DisplayableJobPosting jobPosting,
                                 DisplayableJobseeker jobseeker,
                                 Date date)
  {
    TextJobPostingReporter jobPostingReporter = new TextJobPostingReporter();
    jobPosting.displayTo(jobPostingReporter);
    appText += jobPostingReporter.getJobPostingText();
    appText += ",";

    TextJobseekerReporter jobseekerReporter = new TextJobseekerReporter();
    jobseeker.displayTo(jobseekerReporter);
    appText += jobseekerReporter.getJobseekerText();
    appText += ",";
    
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
    appText += sdf.format(date);
  }

}
