package com.ladders.oc.reporters;

import java.util.Set;

import com.ladders.oc.displayables.DisplayableApplication;
import com.ladders.oc.displayers.ApplicationsDisplayer;

public class CSVApplicationsReporter implements ApplicationsDisplayer
{
  String csvText = "";
  
  public String getApplicationsReport()
  {
    return csvText;
  }
  
  @Override
  public void displayApplications(Set<? extends DisplayableApplication> appSet)
  {
    for (DisplayableApplication app : appSet)
    {
      CSVApplicationReporter appReporter = new CSVApplicationReporter();
      app.displayTo(appReporter);
      csvText += appReporter.getApplicationText();
      csvText += "\n";
    }

  }

}
