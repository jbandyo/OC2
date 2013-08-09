package com.ladders.oc.recruiters;

import com.ladders.oc.Name;
import com.ladders.oc.applications.ApplicationQueryHelper;
import com.ladders.oc.applications.ApplicationRepository;
import com.ladders.oc.applications.Applications;
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

  public ApplicationQueryHelper getApplicationsBy(Job job)
  {
    return new ApplicationQueryHelper(job);
  }

  @Override
  public void displayTo(RecruiterDisplayer displayer)
  {
    displayer.displayRecruiter(name);    
  }



}
