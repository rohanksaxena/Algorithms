package com.rks.algorithms;

import java.io.File;
import java.util.Scanner;
 
public class QuickSort {
	static long comparisons; 
	private int[] data;
 
	public static void main(String[] args) {
		QuickSort ob = new QuickSort();
		ob.data = readFile("InputArray.txt");
		ob.quickSort(0,ob.data.length-1);
		for (int i=0;i<ob.data.length;i++){
			System.out.print(ob.data[i]);
			System.out.print("\t");
			}
	}
 
	public static int[] readFile(String file){
		int[] array = new int[10000];
		try{
			Scanner in = new Scanner(new File(file));
			for(int i=0;i<10000;i++)
				array[i] = in.nextInt();
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return array;
	}
 
	public void quickSort(int l, int r){
		if (l >= r)
			return;
		int medianIndex = (l+r)/2;
		if ((data[l]>data[medianIndex])&&(data[l]<data[r])){
			medianIndex = l;
		}
		else if ((data[r]>data[medianIndex])&&(data[r]<data[l])){
			medianIndex = r;
		}
		if (medianIndex!=l){
			int temp = this.data[l];
			this.data[l] = this.data[medianIndex];
			this.data[medianIndex] = temp;
		}
 		int index = partition(l,r);
		quickSort(l,index-1);
		quickSort(index+1, r);
	}
 
	public int partition(int l, int r){
		int pivot = this.data[l];
		int i = l+1;
		for(int j=l+1;((j<=r)&&(i<=r));j++){	
		 if(this.data[j]<pivot){
				int innerTemp = this.data[i];
				this.data[i] = this.data[j];
				this.data[j] = innerTemp;
				i++;
			}
		}
		int temp = this.data[l];
		this.data[l] = this.data[i-1];
		this.data[i-1] = temp;
		return i-1;
	}
 
}