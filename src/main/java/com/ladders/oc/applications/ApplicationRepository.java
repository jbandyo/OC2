package com.ladders.oc.applications;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobseekers.Jobseeker;

public class ApplicationRepository
{
  private final Set<Application> appSet = Collections.synchronizedSet(new HashSet<Application>());
  
  public void addApplication(Application app)
  {
    appSet.add(app);
  }

  public Applications getApplicationsByJob(Job job)
  {
    ApplicationFilter filter = new ApplicationFilter();
    filter = filter.byJob(job);
    return getApplications(filter);
  }
  
  Applications getApplications(ApplicationFilter filter)
  {
    Applications apps = new Applications();
    synchronized (appSet)
    {
      Iterator<Application> iterator = appSet.iterator();
      while (iterator.hasNext())
      {
        Application app = iterator.next();
        if (!filter.pass(app))
          continue;
        apps.add(app);
      }
      return apps;
    }
  }

}
