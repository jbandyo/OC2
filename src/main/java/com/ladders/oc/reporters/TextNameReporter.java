package com.ladders.oc.reporters;

import com.ladders.oc.displayers.NameDisplayer;

public class TextNameReporter implements NameDisplayer
{
  private String name;
  
  public String getName()
  {
    return name;
  }
  
  @Override
  public void displayName(String name)
  {
    this.name = name;
  }

}
