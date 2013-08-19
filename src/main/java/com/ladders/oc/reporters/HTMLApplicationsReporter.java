package com.ladders.oc.reporters;

import java.util.Set;

import com.ladders.oc.displayables.DisplayableApplication;
import com.ladders.oc.displayers.ApplicationsDisplayer;

public class HTMLApplicationsReporter implements ApplicationsDisplayer
{
  private String htmlText = "";

  public String getApplicationsReport()
  {
    return htmlText;
  }
  
  public void createHeader()
  {
    htmlText += "<html>\n";
    htmlText += "<body>\n";
  }

  public void createFooter()
  {
    htmlText += "</body>\n";
    htmlText += "</html>\n";
  }

  @Override
  public void displayApplications(Set<? extends DisplayableApplication> appSet)
  {
    htmlText += "<table>\n";    
    for (DisplayableApplication app : appSet)
    {
      HTMLApplicationReporter appReporter = new HTMLApplicationReporter();
      app.displayTo(appReporter);
      htmlText += appReporter.getApplicationText();
    }
    htmlText += "</table>\n";
  }

}
