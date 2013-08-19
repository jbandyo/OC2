package com.ladders.oc.argmatchers;

import java.util.Set;

import org.mockito.ArgumentMatcher;

import com.ladders.oc.displayables.DisplayableJobPosting;

public class SetOfThreeJobPostings extends ArgumentMatcher<Set<DisplayableJobPosting>>
{
  DisplayableJobPosting posting1;
  DisplayableJobPosting posting2;
  DisplayableJobPosting posting3;

  public SetOfThreeJobPostings(DisplayableJobPosting posting1,
                               DisplayableJobPosting posting2,
                               DisplayableJobPosting posting3)
  {
    this.posting1 = posting1;
    this.posting2 = posting2;
    this.posting3 = posting3;    
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean matches(Object postings)
  {
    Set<DisplayableJobPosting> postingSet = (Set<DisplayableJobPosting>)postings;
    if ((postingSet.size() == 3) && (postingSet.contains(posting1)) && (postingSet.contains(posting2)) && (postingSet.contains(posting3)))
        return true;
    else
      return false;
  }

}
