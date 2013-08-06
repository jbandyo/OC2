package com.ladders.oc.displayers;

import com.ladders.oc.displayables.DisplayableName;

public class ConsoleRecruiterDisplayer implements RecruiterDisplayer
{

  @Override
  public void displayRecruiter(DisplayableName name)
  {
    NameDisplayer nameDisplayer = new ConsoleNameDisplayer();
    name.displayTo(nameDisplayer);

  }

}
