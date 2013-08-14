package com.ladders.oc.argmatchers;

import java.util.Date;

import org.mockito.ArgumentMatcher;

import com.ladders.oc.applications.AppDateComparator;

public class OneDate extends ArgumentMatcher<Date>
{
  private final Date date;

  public OneDate(Date date)
  {
    this.date = date;
  }

  @Override
  public boolean matches(Object argument)
  {
    Date date = (Date)argument;
    return AppDateComparator.isEqual(date, this.date);
  }

}
