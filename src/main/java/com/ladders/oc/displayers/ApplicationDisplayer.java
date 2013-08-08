package com.ladders.oc.displayers;

import java.util.Date;

import com.ladders.oc.displayables.DisplayableJob;
import com.ladders.oc.displayables.DisplayableJobseeker;

public interface ApplicationDisplayer
{

  void displayApplication(DisplayableJob job,
                          DisplayableJobseeker jobseeker,
                          Date date);

}
