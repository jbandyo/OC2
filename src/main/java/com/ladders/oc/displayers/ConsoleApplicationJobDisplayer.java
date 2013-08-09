package com.ladders.oc.displayers;

import java.util.Date;

import com.ladders.oc.displayables.DisplayableJob;
import com.ladders.oc.displayables.DisplayableJobseeker;

public class ConsoleApplicationJobDisplayer implements ApplicationDisplayer
{

  @Override
  public void displayApplication(DisplayableJob job,
                                 DisplayableJobseeker jobseeker,
                                 Date date)
  {
    JobDisplayer displayer = new ConsoleJobDisplayer();
    job.displayTo(displayer);

  }

}
