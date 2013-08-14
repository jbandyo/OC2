package com.ladders.oc.applications;

import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobs.Jobs;
import com.ladders.oc.recruiters.Recruiter;

public class RecruiterApplicationQueryHelper extends ApplicationQueryHelper
{
  private final Recruiter recruiter;
  
  public RecruiterApplicationQueryHelper(Recruiter recruiter)
  {
    this.recruiter = recruiter;
  }

  @Override
  public Applications from(ApplicationRepository appRepo)
  {
    Applications filteredApps = appRepo.getApplications(filter);
    Applications recruiterApps = new Applications();
    Jobs jobs = recruiter.getPostedJobs();

    for (Job job : jobs)
    {
      for (Application application : filteredApps)
      {
        if (application.containsJob(job))
          recruiterApps.add(application);
      }
    }
    return recruiterApps;
  }

}
