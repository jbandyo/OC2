package com.ladders.oc.displayers;

import java.util.Set;

import com.ladders.oc.displayables.DisplayableApplication;

public interface ApplicationsDisplayer
{

  void displayApplications(Set<? extends DisplayableApplication> appSet);

}
