package com.ladders.oc.recruiters;

import java.util.Date;

import com.ladders.oc.Name;
import com.ladders.oc.applications.ApplicationQueryHelper;
import com.ladders.oc.applications.RecruiterApplicationQueryHelper;
import com.ladders.oc.displayables.DisplayableRecruiter;
import com.ladders.oc.displayers.RecruiterDisplayer;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobs.Jobs;

public class Recruiter implements DisplayableRecruiter
{
  private final Name name;
  private final Jobs jobs = new Jobs();

  public static Recruiter named(String name)
  {
    return new Recruiter(new Name(name));
  }
  
  private Recruiter(Name name)
  {
    this.name = name;
  }

  public void post(Job job)
  {
    jobs.add(job);
  }

  public Jobs getPostedJobs()
  {
    return jobs;
  }

  public RecruiterApplicationQueryHelper getApplications()
  {
    return new RecruiterApplicationQueryHelper(this);
  }

  @Override
  public void displayTo(RecruiterDisplayer displayer)
  {
    displayer.displayRecruiter(name);    
  }

}
