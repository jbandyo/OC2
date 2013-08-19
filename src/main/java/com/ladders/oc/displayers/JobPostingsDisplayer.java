package com.ladders.oc.displayers;

import java.util.Set;

import com.ladders.oc.displayables.DisplayableJobPosting;

public interface JobPostingsDisplayer
{
  void displayJobPostings(Set<? extends DisplayableJobPosting> jobPostingSet);

}
