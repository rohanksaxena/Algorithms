package com.rks.algorithms;

import java.util.Comparator;

public class JobComparator implements Comparator<Job> {

	@Override
	public int compare(Job j1, Job j2) {
		if (j1.getCost()>j2.getCost())
			return -1;
		return 1;
	}
}
