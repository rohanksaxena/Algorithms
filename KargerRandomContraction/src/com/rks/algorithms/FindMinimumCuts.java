package com.rks.algorithms;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
 
public class FindMinimumCuts {
 
	public static void main(String[] args) {
		ArrayList<LinkedList<Integer>> data = new ArrayList<LinkedList<Integer>>();
		data = readFile("InputArray.txt");
		System.out.println("The number of minimum cuts is: " + KargerMinCuts(data));
	}
 
	public static ArrayList<LinkedList<Integer>> readFile(String file){
		ArrayList<LinkedList<Integer>> graph = new ArrayList<LinkedList<Integer>>();
		try{
			Scanner in = new Scanner(new File(file));
			int i = 0;
			String line;
			while(in.hasNextLine()){
				graph.add(new LinkedList<Integer>());
				line = in.nextLine().trim();
				Scanner lineIn = new Scanner(line);
				lineIn.useDelimiter("\t");
				while(lineIn.hasNext()){
					graph.get(i).add(lineIn.nextInt());
				}
				lineIn.close();
				i++;
			}
		in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return graph;
	}
 
	public static int KargerMinCuts(ArrayList<LinkedList<Integer>> a){
		while(a.size()>2){
			LinkedList<Integer> temp;
			int i;
			int node1index = (int)(Math.random()*a.size());
			int node1 = a.get(node1index).get(0);
			int node2index = (int)(1 + Math.random()*(a.get(node1index).size()-1));
			int node2 = a.get(node1index).get(node2index);
			for(i=0;i<a.size();i++)
				if (a.get(i).get(0)==node2)
					break;
			temp = a.get(i);
			a.get(node1index).addAll(temp);			
			temp = removeSelfLoops(a.get(node1index),node1,node2);
			a.set(node1index,temp);
			a.remove(i);
			for(LinkedList<Integer> list:a){
				for(int j=0;j<list.size();j++){
					if (list.get(j) == node2)
						list.set(j,node1);
				}
			}
		}
		return a.get(0).size()-1;
	}
 
	public static LinkedList<Integer> removeSelfLoops(LinkedList<Integer> list, int vertex1, int vertex2){
		for(int i=1;i<list.size();i++)
			if ((list.get(i)==vertex1)||(list.get(i)==vertex2))
				list.remove(i--);
		return list;
	}
}
 