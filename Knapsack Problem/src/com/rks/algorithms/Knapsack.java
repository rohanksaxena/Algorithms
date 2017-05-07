package com.rks.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Knapsack {

	private static int[][] knapsack;
	private static int knapsackCapacity;
	private static int itemCount;
	private static Item[] items;
	
	public static void main(String[] args) {
		
		readFile("knapsack1.txt");
		
		System.out.println("Knapsack Size: "+knapsackCapacity);
		System.out.println("Number of items: "+itemCount);
		
		for(int i=0;i<=itemCount;i++){
			
			for(int j=0;j<=knapsackCapacity;j++){
				
				if(i==0){
					knapsack[i][j] = 0;
				}
				else{
					if(items[i].getSize()<=j){
						knapsack[i][j] = Math.max(knapsack[i-1][j],knapsack[i-1][j-items[i].getSize()] + items[i].getValue());
					}
				}				
			}		
		}
		System.out.println("Solution: " + knapsack[itemCount][knapsackCapacity]);
	}
		
	private static void readFile(String fileName){
		
		try {
			Scanner in = new Scanner(new File(fileName));
			knapsackCapacity = in.nextInt();
			itemCount = in.nextInt();
			knapsack = new int[itemCount+1][knapsackCapacity+1];
			items = new Item[itemCount+1];
			int i = 1;
			while(in.hasNextLine()){
				Item item = new Item();
				item.setValue(in.nextInt());
				item.setSize(in.nextInt());
				items[i++] = item;
			}
			Item item = new Item();
			item.setValue(0);
			item.setSize(0);
			items[0] = item;
			in.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}

