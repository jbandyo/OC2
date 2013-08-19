package com.ladders.oc.displayers;

import com.ladders.oc.displayables.DisplayableJob;
import com.ladders.oc.displayables.DisplayableRecruiter;

public interface JobPostingDisplayer
{

  void displayJobPosting(DisplayableRecruiter recruiter,
                         DisplayableJob job);

}
