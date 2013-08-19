package com.ladders.oc.reporters;

import com.ladders.oc.displayables.DisplayableName;
import com.ladders.oc.displayers.RecruiterDisplayer;

public class TextRecruiterReporter implements RecruiterDisplayer
{
  private String recruiterText;
  
  public String getRecruiterText()
  {
    return recruiterText;
  }
  

  @Override
  public void displayRecruiter(DisplayableName name)
  {
    TextNameReporter nameReporter = new TextNameReporter();
    name.displayTo(nameReporter);
    recruiterText = nameReporter.getName();
  }

}
