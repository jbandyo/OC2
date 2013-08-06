package com.ladders.oc;

import com.ladders.oc.displayables.DisplayableName;
import com.ladders.oc.displayers.NameDisplayer;

public class Name implements DisplayableName
{
  private final String name;
  
  public Name(String name)
  {
    this.name = name;
  }

  @Override
  public void displayTo(NameDisplayer displayer)
  {
    displayer.displayName(name);
    
  }


}
