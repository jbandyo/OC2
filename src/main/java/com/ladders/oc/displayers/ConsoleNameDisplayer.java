package com.ladders.oc.displayers;

public class ConsoleNameDisplayer implements NameDisplayer
{

  @Override
  public void displayName(String name)
  {
    System.out.print(name);

  }

}
