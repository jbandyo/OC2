package com.ladders.oc.recruiters;

import com.ladders.oc.Name;
import com.ladders.oc.applications.ApplicationQueryHelper;
import com.ladders.oc.displayables.DisplayableRecruiter;
import com.ladders.oc.displayers.RecruiterDisplayer;
import com.ladders.oc.jobs.Job;

public class Recruiter implements DisplayableRecruiter
{
  private final Name name;

  public static Recruiter named(String name)
  {
    return new Recruiter(new Name(name));
  }
  
  private Recruiter(Name name)
  {
    this.name = name;
  }

  public PostingHelper post(Job job)
  {
    PostingHelper helper = new PostingHelper(this, job);
    return helper;
  }

  public PostingQueryHelper getPostedJobs()
  {
    PostingQueryHelper queryHelper = new PostingQueryHelper(this);
    return queryHelper;
  }

  public ApplicationQueryHelper getApplications()
  {
    return new ApplicationQueryHelper();
  }

  @Override
  public void displayTo(RecruiterDisplayer displayer)
  {
    displayer.displayRecruiter(name);    
  }

}
