package com.home.interview.algo.greedy;

import java.util.Arrays;

public class FractionalKnapsackProblem {
	/*
	 * Given weights and values of n items, we need to put these items in a knapsack
	 * of capacity W to get the maximum total value in the knapsack. An efficient
	 * solution is to use Greedy approach. The basic idea of the greedy approach is
	 * to calculate the ratio value/weight for each item and sort the item on basis
	 * of this ratio. Then take the item with the highest ratio and add them until
	 * we can’t add the next item as a whole and at the end add the next item as
	 * much as we can. Which will always be the optimal solution to this problem.
	 */

	static class ItemValue {
		double wt, val;
		Double cost;

		public ItemValue(double wt, double val) {
			this.wt = wt;
			this.val = val;
			this.cost = Double.valueOf(val / wt);
		}

	}

	public static double getMaxValue(int[] wt, int[] val, int capacity) {
		ItemValue[] iVal = new ItemValue[wt.length];
		for (int i = 0; i < wt.length; i++) {
			iVal[i] = new ItemValue(wt[i], val[i]);
		}
		Arrays.sort(iVal, (o1, o2) -> o2.cost.compareTo(o1.cost));
		double totalValue = 0d;
		for (ItemValue i : iVal) {
			int curWt = (int) i.wt;
			int curVal = (int) i.val;

			if (capacity - curWt >= 0) {
				totalValue += curVal;
				capacity -= curWt;
			} else {
				// item cant be picked whole
				double fraction = ((double) capacity / (double) curWt);
				totalValue += (curVal * fraction);
				capacity = (int) (capacity - (curWt * fraction));
				break;
			}
		}

		return totalValue;
	}

	// Time complexity O(n log n)
	public static void main(String[] args) {
		int[] wt = { 10, 40, 20, 30 };
		int[] val = { 60, 40, 100, 120 };
		int capacity = 50;

		double maxValue = getMaxValue(wt, val, capacity);
		System.out.println("Maximum value we can obtain = " + maxValue);

	}
}
