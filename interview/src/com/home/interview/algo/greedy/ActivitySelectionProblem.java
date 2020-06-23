package com.home.interview.algo.greedy;

import java.util.Arrays;

public class ActivitySelectionProblem {
	
	/*
	 * Greedy is an algorithmic paradigm that builds up a solution piece by piece, always choosing the next piece that offers the most obvious and immediate benefit. 
	 * Greedy algorithms are used for optimization problems. 
	 * An optimization problem can be solved using Greedy if the problem has the following property: 
	 *  1. At every step, we can make a choice that looks best at the moment, and 
	 *  2. we get the optimal solution of the complete problem.
	 *
	 * You are given n activities with their start and finish times. 
	 * Select the maximum number of activities that can be performed by a single person, assuming that a person can only work on a single activity at a time.
	 * 
	 * The greedy choice is to always pick the next activity whose finish time is least among the remaining activities and the start time is more than or equal to the finish time of previously selected activity. We can sort the activities according to their finishing time so that we always consider the next activity as minimum finishing time activity.

		1) Sort the activities according to their finishing time
		2) Select the first activity from the sorted array and print it.
		3) Do following for remaining activities in the sorted array.
		 	a) If the start time of this activity is greater than or equal to the finish time of previously selected activity then select this activity and print it.
	 	Time Complexity : It takes O(n log n) time if input activities may not be sorted. It takes O(n) time when it is given that input activities are always sorted.
	 */
	
	 static class ItemValue{
    	 Integer start, end;
    	 public ItemValue(int start, int end) {
    		 this.start = start;
    		 this.end = end;
		}
    	@Override
     	public String toString() {
     		return "(" + start + ", " + end + ")";
     	}
     }
	 
	  public static void printMaxActivities(int[] wt, int[] val) {
		  ItemValue[] iVal = new ItemValue[wt.length]; 
	      for (int i=0; i < wt.length; i++) {
	    	  iVal[i] = new ItemValue(wt[i], val[i]);
	      }
	      Arrays.sort(iVal, (o1, o2) -> o1.end.compareTo(o2.end));
	      System.out.print(iVal[0]);
	      int i = 0;
	      
	      for (int j = 1; j < iVal.length; j++){
	    	  if(iVal[j].start >= iVal[i].end) {
	    		  System.out.print(iVal[j]);
	    		  i = j;
	    	  }
	      }
	  }
	 
	 public static void main(String[] args) {
		 
         int s[] =  {1, 3, 0, 5, 8, 5}; 
         int f[] =  {2, 4, 6, 7, 9, 9}; 
         
         printMaxActivities(s, f);
		
	}

}
