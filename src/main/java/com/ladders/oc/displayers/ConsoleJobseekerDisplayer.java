package com.ladders.oc.displayers;

import com.ladders.oc.displayables.DisplayableName;

public class ConsoleJobseekerDisplayer implements JobseekerDisplayer
{

  @Override
  public void displayJobseeker(DisplayableName name)
  {
    NameDisplayer nameDisplayer = new ConsoleNameDisplayer();
    name.displayTo(nameDisplayer);
    
  }

}
