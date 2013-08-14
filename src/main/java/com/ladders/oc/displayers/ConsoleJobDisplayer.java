package com.ladders.oc.displayers;

import com.ladders.oc.displayables.DisplayableJobTitle;

public class ConsoleJobDisplayer implements JobDisplayer
{

  @Override
  public void displayJob(DisplayableJobTitle title)
  {
    JobTitleDisplayer titleDisplayer = new ConsoleJobTitleDisplayer();
    title.displayTo(titleDisplayer);
 
  }

}
