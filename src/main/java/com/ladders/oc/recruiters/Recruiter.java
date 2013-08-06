package com.ladders.oc.recruiters;

import com.ladders.oc.Name;
import com.ladders.oc.displayables.DisplayableName;
import com.ladders.oc.displayables.DisplayableRecruiter;
import com.ladders.oc.displayers.RecruiterDisplayer;
import com.ladders.oc.jobs.Job;

public class Recruiter implements DisplayableRecruiter
{

  private final Name name;

  public Recruiter(Name name)
  {
    this.name = name;
  }

  public void postJob(Job job)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void displayTo(RecruiterDisplayer displayer)
  {
    displayer.displayRecruiter((DisplayableName)name);
    
  }

}
