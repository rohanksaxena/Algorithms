package com.rks.algorithms;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
 
public class StronglyConnectedComponents {
 
	static Vector<Vector<Integer>> g;
	static Vector<Vector<Integer>> grev;
	static int nodeCount;
	static int[] explored;
	static int[] finishingTimes;
	static int source;
	static int time = 1;
	static int highestNode;
	static int[] sccLengths;
	static int sccLength;
	static int sccLengthsIndex;
 
 
	public static void main(String[] args){
		g = readFile("InputEdges.txt");
		g = graphMergeSort(g);
		highestNode = g.get(g.size()-1).get(0);
		sccLengths = new int[highestNode];
		explored = new int[highestNode];
		finishingTimes = new int[highestNode];		
		grev = new Vector<Vector<Integer>>(g.size());
		grev = graphReverse(g);
		grev = graphMergeSort(grev);
		dfsLoop(grev);
		for(int i = 0;i<explored.length;i++)
			explored[i] = 0;
		dfsLoopFinishingTimes(g);
		Arrays.sort(sccLengths);
		for(int i = 0;i<sccLengths.length;i++){
			if (sccLengths[i]!=0)
				System.out.println(sccLengths[i]);
		}
	}
 
	public static Vector<Vector<Integer>> readFile(String file){
		Vector<Vector<Integer>> graph = new Vector<Vector<Integer>>();
		try{
			Scanner in = new Scanner(new File(file));
			int i = 0;
			String line;
			while(in.hasNextLine()){
				graph.add(new Vector<Integer>());
				line = in.nextLine().trim();
				Scanner lineIn = new Scanner(line);
				lineIn.useDelimiter(" ");
				while(lineIn.hasNext()){
					graph.get(i).add(lineIn.nextInt());
					//System.out.println(graph.get(i));
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
 
	public static Vector<Vector<Integer>> graphReverse(Vector<Vector<Integer>> graph){
		Vector<Vector<Integer>> reverseGraph = new Vector<Vector<Integer>>(graph.size());
		for (Vector<Integer> vertices: graph){			
			Vector<Integer> templist = new Vector<Integer>(vertices);
			for(int i=0,j=vertices.size()-1;(i<j);i++,j--){
				int temp = vertices.get(i);
				templist.set(i, vertices.get(j));
				templist.set(j,temp);
			}
			reverseGraph.add(templist);
		}
		return reverseGraph;			
	}
 
	public static void dfsLoop(Vector<Vector<Integer>> graph){
		for (int i=highestNode;i>=1;i--){
			source = i;
			if (explored[i-1]==0){
				dfs(graph,source);
			}
		}
	}
 
	public static void dfs(Vector<Vector<Integer>> graph, int source){
		explored[source-1] = 1;
		for (Vector<Integer> vertices: graph){
			if (vertices.get(0)!=source)
				continue;
			else if((vertices.get(0)==source)&&((explored[vertices.get(1)-1])==0))
				dfs(graph,vertices.get(1));
		}
		finishingTimes[source-1] = time;
		time++;
	}
 
	public static void dfsLoopFinishingTimes(Vector<Vector<Integer>> graph){
		while(findMaxFinishingTime()!=0){
			sccLength=0;
			dfsFinishingTimes(graph,findMaxFinishingTime());
			sccLengths[sccLengthsIndex] = sccLength;
			sccLengthsIndex++;
		}
	}
 
	public static void dfsFinishingTimes(Vector<Vector<Integer>> graph, int source){
		explored[source-1] = 1;
		finishingTimes[source-1] = 0;
		for (Vector<Integer> vertices: graph){
			if (vertices.get(0)!=source)
				continue;
			else if((vertices.get(0)==source)&&((explored[vertices.get(1)-1])==0))
				dfsFinishingTimes(graph,vertices.get(1));				
		}
		sccLength++;
	}
 
 
	public static Vector<Vector<Integer>> graphMergeSort(Vector<Vector<Integer>> graph){
		if (graph.size()==1)
			return graph;
		else if (graph.size()%2!=0)	{
			Vector<Vector<Integer>> a = new Vector<Vector<Integer>>(graph.size()/2);
			Vector<Vector<Integer>> b = new Vector<Vector<Integer>>(graph.size()/2+1);
			for(int i=0,j=(graph.size())/2;i<graph.size()/2;i++,j++){
				a.add(graph.get(i));
				b.add(graph.get(j));
				if (j==graph.size()-2)
					b.add(graph.get(graph.size()-1));
			}
		a = graphMergeSort(a);
		b = graphMergeSort(b);
		graph = graphMerge(a,b,graph);	
		}
		else{
			Vector<Vector<Integer>> a = new Vector<Vector<Integer>>(graph.size()/2);
			Vector<Vector<Integer>> b = new Vector<Vector<Integer>>(graph.size()/2);
			for(int i=0,j=(graph.size())/2;i<graph.size()/2;i++,j++){
				a.add(graph.get(i));
				b.add(graph.get(j));
		}		
		a = graphMergeSort(a);
		b = graphMergeSort(b);
		graph = graphMerge(a,b,graph);
		}
		return graph;
	}
 
	public static Vector<Vector<Integer>> graphMerge(Vector<Vector<Integer>> a,Vector<Vector<Integer>> b,Vector<Vector<Integer>> graph){
		Vector<Vector<Integer>> c = new Vector<Vector<Integer>>(graph);
		int i = 0;
		int j = 0;
		int k;
		for (k=0;k<c.size();k++){
			if ((j==b.size())&&(i<a.size())){
				c.set(k,a.get(i));
				i++;						
			}
			else if ((i==a.size())&&(j<b.size())){
				c.set(k,b.get(j));
				j++;						
			}
			else if(a.get(i).get(0)<b.get(j).get(0)){
				c.set(k, a.get(i));
				i++;
			}
			else if (b.get(j).get(0)<a.get(i).get(0)){
				c.set(k, b.get(j));
				j++;
			}
			else if(a.get(i).get(0)==b.get(j).get(0)){
				if (a.get(i).get(1)<b.get(j).get(1)){
					c.set(k,a.get(i));
					i++;
				}
				else if (b.get(j).get(1)<a.get(i).get(1)){
					c.set(k,b.get(j));
					j++;
				}
			}
		}
		return c;
	}
 
	public static int findMaxFinishingTime(){
		int max = 0;
		int node = 0;
		for (int i=0;i<finishingTimes.length;i++){
			if(finishingTimes[i]>max){
				max = finishingTimes[i];
				node = i+1; 
			}
		}
	return node;
	}
 
}