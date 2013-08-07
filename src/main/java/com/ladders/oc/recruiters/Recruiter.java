package com.ladders.oc.recruiters;

import com.ladders.oc.Name;
import com.ladders.oc.displayables.DisplayableRecruiter;
import com.ladders.oc.displayers.RecruiterDisplayer;
import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobs.Jobs;

public class Recruiter implements DisplayableRecruiter
{

  private final Name name;
  private final Jobs jobs = new Jobs();

  public Recruiter(Name name)
  {
    this.name = name;
  }

  public void postJob(Job job)
  {
    jobs.add(job);
  }

  public Jobs listPostedJobs()
  {
    return jobs;
  }

  @Override
  public void displayTo(RecruiterDisplayer displayer)
  {
    displayer.displayRecruiter(name);
    
  }

}
