package com.ladders.oc.displayers;

import java.util.Set;

import com.ladders.oc.displayables.DisplayableJobPosting;


public class ConsoleJobPostingsDisplayer implements JobPostingsDisplayer
{

  @Override
  public void displayJobPostings(Set<? extends DisplayableJobPosting> jobPostingSet)
  {
    JobPostingDisplayer displayer = new ConsoleJobPostingDisplayer();
    for (DisplayableJobPosting posting : jobPostingSet)
    {
      System.out.print("- ");
      posting.displayTo(displayer);
      System.out.println();
    }
  }

}
