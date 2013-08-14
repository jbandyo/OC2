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
  public boolean equals(Object o)
  {
    // check the class
    Name otherName = (Name) o;
    return name.equals(otherName.name);
  }
  
  @Override
  public int hashCode()
  {
    return name.hashCode();
  }

  @Override
  public void displayTo(NameDisplayer displayer)
  {
    displayer.displayName(name);
    
  }


}
