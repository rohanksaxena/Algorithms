package com.rks.algorithms;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class DijkstrasAlgo {

	private static Vector<Edge> edgeList;
	private static int[] distances;
	private static int size;
	private static Vector<Integer> coveredNodes;
	private static Vector<Integer> remainingNodes;
	
	public static void main(String args[]){
		edgeList = readFile("InputGraph.txt");
		size = edgeList.get(edgeList.size()-1).getStartNode();
		remainingNodes = new Vector<Integer>(size);
		coveredNodes = new Vector<Integer>(size);
		for(int i=2;i<=size;i++)
			remainingNodes.add(i);
		coveredNodes.add(1);
		distances = new int[size];		
		Arrays.fill(distances, 1000000);
		distances[0] = 0;
		Dijkstra();
		for(int i=0;i<distances.length;i++)
			System.out.println(distances[i]);
	}
	
	public static Vector<Edge> readFile(String fileName){
		Vector<Edge> g = new Vector<Edge>();
		int flag = 0;
		int startNode = 0;
		String line;
		try{
			Scanner in = new Scanner(new File(fileName));
			in.useDelimiter("\n");
			while(in.hasNext()){
				line = in.next().trim();
				Scanner lineIn = new Scanner(line);
				lineIn.useDelimiter(",| |\\t");
				while(lineIn.hasNextInt()){
					if (flag==0){
						startNode = Integer.parseInt(lineIn.next());
						flag = 1;
					}
					else{
						Edge e = new Edge();
						e.setStartNode(startNode);
						e.setEndNode(Integer.parseInt(lineIn.next()));
						e.setWeight(Integer.parseInt(lineIn.next()));
						g.add(e);
					}						
				}
				lineIn.close();
				flag = 0;
			}
		in.close();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return g;
	}	
	
	public static void Dijkstra(){
		int minLength = 10000000;
		Edge edge = new Edge();
		while(remainingNodes.size()>0){		
			for(Edge e: edgeList){
				if ((coveredNodes.contains(e.getStartNode()))&&(remainingNodes.contains(e.getEndNode()))&&(e.getWeight()+distances[e.getStartNode()-1]<minLength)){
					minLength = e.getWeight()+distances[e.getStartNode()-1];
					edge = e;
				}
			}
			coveredNodes.add(edge.getEndNode());
			remainingNodes.remove((Integer)edge.getEndNode());
			distances[edge.getEndNode()-1] = minLength; 
			minLength = 10000000;
		}
	}
}
