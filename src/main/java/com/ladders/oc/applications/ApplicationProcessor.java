package com.ladders.oc.applications;

import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobseekers.Jobseeker;
import com.ladders.oc.resumes.Resume;

public class ApplicationProcessor
{

  public boolean apply(Jobseeker jobseeker,
                       Job job,
                       Resume resume)
  {
    if (job.RequiresResume())
    {
      if (resume == null)
        return false;
    }
    return true;
  }

}
