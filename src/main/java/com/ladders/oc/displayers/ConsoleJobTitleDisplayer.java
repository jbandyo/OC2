package com.ladders.oc.displayers;

public class ConsoleJobTitleDisplayer implements JobTitleDisplayer
{

  @Override
  public void displayJobTitle(String title)
  {
    System.out.print(title);
    
  }

}
