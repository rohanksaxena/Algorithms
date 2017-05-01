package com.rks.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman {

	private static PriorityQueue<Symbol> sortedSymbols;
	private static int symbolCount;
  	
	public static void main(String[] args) {
		readFile("huffman.txt");
		
		while(sortedSymbols.size()>1){
			
			Symbol s1 = sortedSymbols.remove();
			Symbol s2 = sortedSymbols.remove();
			Symbol s3 = new Symbol();
			
			s3.setKey(s1.getKey().concat("-").concat(s2.getKey()));
			s3.setWeight(s1.getWeight()+s2.getWeight());
			s3.setLevel(Math.max(s1.getLevel(),s2.getLevel())+1);
			
			sortedSymbols.add(s3);
		}
		System.out.println("Maximum encoding length: "+sortedSymbols.remove().getLevel());
		
	}
	
	private static void readFile(String fileName){
		try {
			Scanner in = new Scanner(new File(fileName));
			symbolCount = in.nextInt();
			sortedSymbols = new PriorityQueue<Symbol>(symbolCount, new SymbolComparator());
			System.out.println("Number of Symbols: " + symbolCount);
			int i = 1;
			while(in.hasNext()){
				Symbol symbol = new Symbol();
				symbol.setKey(Integer.toString(i++));
				symbol.setWeight(in.nextInt());
				symbol.setLevel(0);
				sortedSymbols.add(symbol);
			}
			in.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
