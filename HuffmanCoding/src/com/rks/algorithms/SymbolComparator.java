package com.rks.algorithms;

import java.util.Comparator;

public class SymbolComparator implements Comparator<Symbol> {

	@Override
	public int compare(Symbol s1, Symbol s2) {
		
		if (s1.getWeight()>s2.getWeight()){
			return 1;
		}
		else if(s1.getWeight()<s2.getWeight()){
			return -1;
		}
		return 0;
	}

}
