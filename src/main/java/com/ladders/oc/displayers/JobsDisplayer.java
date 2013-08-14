package com.ladders.oc.displayers;

import java.util.Set;

import com.ladders.oc.displayables.DisplayableJob;

public interface JobsDisplayer
{

  void displayJobs(Set<? extends DisplayableJob> jobSet);

}
