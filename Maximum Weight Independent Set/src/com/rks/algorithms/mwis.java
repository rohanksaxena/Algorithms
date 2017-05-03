package com.rks.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class mwis {

	private static int[] pathGraph;
	private static int nodeCount;
	private static long[] maxWeight;
	private static ArrayList<Integer> solution;
	
	public static void main(String[] args) {
		
		solution = new ArrayList<Integer>();
		
		readFile("mwis.txt");
		
		maxWeight[0] = 0;
		maxWeight[1] = pathGraph[0];
		
		for(int i=2;i<=nodeCount;i++){
			maxWeight[i] = Math.max(maxWeight[i-1], maxWeight[i-2] + pathGraph[i-1]);
		}
		
		System.out.println("Max weight independent set value: "+maxWeight[nodeCount]);
		
		int i = nodeCount;
		
		while(i>1){
			if (maxWeight[i-1]>=(maxWeight[i-2]) + pathGraph[i-1]){
				i--;
			}
			else{
				solution.add(pathGraph[i-1]);
				i-=2;
			}	
		}

		if(!solution.contains(pathGraph[1])){
			solution.add(pathGraph[0]);
		}
		
		System.out.println("Reconstructed solution:");
		
		for(int j: solution ){
			System.out.println(j);
		}
	}
	
	public static void readFile(String fileName){
		
		try {
			Scanner in = new Scanner(new File(fileName));
			nodeCount = in.nextInt();
			System.out.println("Number of nodes: " + nodeCount);
			pathGraph = new int[nodeCount];
			maxWeight = new long[nodeCount+1];
			int i = 0;
			while(in.hasNext()){
				pathGraph[i++] = in.nextInt();
			}
			in.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
