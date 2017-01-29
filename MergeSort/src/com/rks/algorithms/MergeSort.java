package com.rks.algorithms;

import java.io.File;
import java.util.Scanner;
 
public class MergeSort {
 
	public static void main(String args[]){
  		int data[] = readFile("InputArray.txt");
  		data = mergeSort(data);
  		for(int i: data)
  			System.out.print(i+"\t");
	}
 
	public static int[] readFile(String file){
		int[] array = new int[100000];
		try{
			Scanner in = new Scanner(new File(file));
			for(int i=0;i<100000;i++)
				array[i] = in.nextInt();
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return array;
	}
 
	public static int[] mergeSort(int array[]){
		if (array.length==1)
			return array;
		else if (array.length%2!=0)	{
			int a[] = new int[array.length/2];
			int b[] = new int[array.length/2+1];
			for(int i=0,j=(array.length)/2;i<array.length/2;i++,j++){
				a[i] = array[i];
				b[i] = array[j];
				b[i+1] = array[j+1];
			}
		a = mergeSort(a);
		b = mergeSort(b);
		array = merge(a,b);	
 
		}
		else{
			int a[] = new int[array.length/2];
			int b[] = new int[array.length/2];
			for(int i=0,j=(array.length)/2;i<array.length/2;i++,j++){
				a[i] = array[i];
				b[i] = array[j];
			}
		a = mergeSort(a);
		b = mergeSort(b);
		array = merge(a,b);
		}
		return array;
	}
 
	public static int[] merge(int a[],int b[]){
		int[] c = new int[a.length+b.length];
		int i = 0;
		int j = 0;
		for (int k=0;k<c.length;k++){
			if ((j==b.length)&&(i<a.length)){
				c[k] = a[i];
				i++;						
			}
			else if ((i==a.length)&&(j<b.length)){
				c[k] = b[j];
				j++;						
			}
			else if(a[i]<b[j]){
				c[k]=a[i];
				i++;
			}
			else if (b[j]<a[i]){
				c[k] = b[j];
				j++;
			}
		}
		return c;
 
	}
}