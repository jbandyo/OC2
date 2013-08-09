package com.ladders.oc.applications;

import com.ladders.oc.jobs.Job;
import com.ladders.oc.jobseekers.Jobseeker;
import com.ladders.oc.resumes.Resume;
import com.theladders.confident.Maybe;

public class ApplicationProcessor
{
  ApplicationRepository applicationRepository;

  public ApplicationProcessor(ApplicationRepository appRepo)
  {
    applicationRepository = appRepo;
  }

  public boolean apply(Jobseeker jobseeker,
                       Job job,
                       Maybe<Resume> resume)
  {
    if (!isValidApplication(jobseeker, job, resume))
      return false;
    
    Application app = new Application(job, jobseeker);
    applicationRepository.addApplication(app);

    return true;
  }
  
  private boolean isValidApplication(Jobseeker jobseeker,
                                     Job job,
                                     Maybe<Resume> maybeResume)
  {
    if (job.RequiresResume())
    {
      if (maybeResume.isNothing())
        return false;
  
      Resume resume = maybeResume.get();
      if (!resume.ownedBy(jobseeker))
          return false;
    }

    return true;
  }

}
