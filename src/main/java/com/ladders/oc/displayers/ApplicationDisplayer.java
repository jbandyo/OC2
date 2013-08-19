package com.ladders.oc.displayers;

import java.util.Date;

import com.ladders.oc.displayables.DisplayableJobPosting;
import com.ladders.oc.displayables.DisplayableJobseeker;

public interface ApplicationDisplayer
{

  void displayApplication(DisplayableJobPosting jobPosting,
                          DisplayableJobseeker  jobseeker,
                          Date date);

}
